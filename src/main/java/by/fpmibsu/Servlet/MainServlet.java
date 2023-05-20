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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("")
public class MainServlet extends HttpServlet {
    static final Logger mainServletLogger = LogManager.getLogger(MainServlet.class);


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        mainServletLogger.debug("Enter the main page");
        setCorsHeaders(resp);
        //resp.addHeader("Access-Control-Allow-Origin", "*");

        //req.getRequestDispatcher("/html/profile.html").forward(req, resp);

        resp.getWriter().print("Hello Arsenii))))))))))))))))))))))))))))))");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCorsHeaders(resp);
        System.out.println(req.getParameter("id"));
        System.out.println(req.getParameter("object"));


//        UserService userService = new UserService();
//        req.setCharacterEncoding("UTF-8");
//        String value = req.getParameter("profileButton");
//        if (value.equals("Войти")){
//            mainServletLogger.debug("Redirect to login form");
//            resp.sendRedirect(req.getContextPath() + "/login");
//        }
//        else {
//            User user = userService.findEntityById((Long) req.getSession().getAttribute("userId"));
//            mainServletLogger.debug("Enter to the personal account");
//            if (user.getRole().getRole().equals("Администратор"))
//                resp.sendRedirect(req.getContextPath() + "/admin");
//            else if (user.getRole().getRole().equals("Курьер"))
//                resp.sendRedirect(req.getContextPath() + "/courier");
//            else
//                resp.sendRedirect(req.getContextPath() + "/profile");
//        }
    }
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCorsHeaders(resp);
    }

    private void setCorsHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*"); // Replace "*" with the specific allowed origin if needed
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "3600");
    }
}