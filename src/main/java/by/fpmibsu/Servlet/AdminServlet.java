package by.fpmibsu.Servlet;

import by.fpmibsu.Entity.User;
import by.fpmibsu.Services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    static final Logger adminServletLogger = LogManager.getLogger(AdminServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        List<User> users = (ArrayList<User>)userService.getAllNotdAmin();
        adminServletLogger.debug("Enter the admin page");
        req.setAttribute("users",users);
        req.getRequestDispatcher("/jsp/administrator.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        adminServletLogger.debug("Button action");
        String email = req.getParameter("email");
        UserService userService = new UserService();
        userService.delete(email);
        doGet(req,resp);
    }
}
