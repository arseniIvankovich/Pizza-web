package by.fpmibsu.Servlet;


import by.fpmibsu.Entity.User;
import by.fpmibsu.Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("")
public class MainServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        req.setCharacterEncoding("UTF-8");
        String value = req.getParameter("profileButton");
        if (value.equals("Войти"))
            resp.sendRedirect(req.getContextPath() + "/login");
        else {
            User user = userService.findEntityById((Long) req.getSession().getAttribute("userId"));

            if (user.getRole().getRole().equals("Администратор"))
                resp.sendRedirect(req.getContextPath() + "/admin");
            else if (user.getRole().getRole().equals("Курьер"))
                resp.sendRedirect(req.getContextPath() + "/courier");
            else
                resp.sendRedirect(req.getContextPath() + "/profile");
        }
    }

}
