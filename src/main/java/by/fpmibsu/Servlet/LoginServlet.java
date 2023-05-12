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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = userService.checkLogin(email, password);

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
