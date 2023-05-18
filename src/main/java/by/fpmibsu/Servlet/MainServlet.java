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

@WebServlet("")
public class MainServlet extends HttpServlet {
    static final Logger mainServletLogger = LogManager.getLogger(MainServlet.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        mainServletLogger.debug("Enter the main page");
       // req.getRequestDispatcher("/jsp/indexjsp.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserService userService = new UserService();
        req.setCharacterEncoding("UTF-8");
        String value = req.getParameter("profileButton");
        if (value.equals("Войти")){
            mainServletLogger.debug("Redirect to login form");
            resp.sendRedirect(req.getContextPath() + "/login");
        }
        else {
            User user = userService.findEntityById((Long) req.getSession().getAttribute("userId"));
            mainServletLogger.debug("Enter to the personal account");
            if (user.getRole().getRole().equals("Администратор"))
                resp.sendRedirect(req.getContextPath() + "/admin");
            else if (user.getRole().getRole().equals("Курьер"))
                resp.sendRedirect(req.getContextPath() + "/courier");
            else
                resp.sendRedirect(req.getContextPath() + "/profile");
        }
    }

}
