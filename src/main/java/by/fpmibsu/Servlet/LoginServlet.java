package by.fpmibsu.Servlet;

import by.fpmibsu.Entity.User;
import by.fpmibsu.Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    final String path = "/jsp/login.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email").trim();
        if (email.equals("") || !userService.checkEmail(email)) {
            req.setAttribute("emailError",true);
            req.getRequestDispatcher(path).forward(req,resp);
            return;
        }
        String password = req.getParameter("password");
        User user = userService.findByEmail(email);
        if (password.equals("")) {
            req.setAttribute("passwordError",true);
            req.getRequestDispatcher(path).forward(req,resp);
            return;
        }
        if (!userService.checkLoginPassword(user.getEmail(),password)) {
            req.setAttribute("passwordError",true);
            req.getRequestDispatcher(path).forward(req,resp);
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("userId", user.getUserId());

        if (user.getRole().getRole().equals("Администратор"))
            resp.sendRedirect(req.getContextPath() + "/admin");
        else if (user.getRole().getRole().equals("Курьер"))
            resp.sendRedirect(req.getContextPath() + "/courier");
        else
            resp.sendRedirect(req.getContextPath() + "/profile");
    }
}
