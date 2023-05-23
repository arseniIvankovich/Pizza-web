package by.fpmibsu.Servlet;


import by.fpmibsu.Entity.User;
import by.fpmibsu.Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        //resp.sendRedirect(req.getContextPath() + "/vacancy");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCorsHeaders(resp);
        UserService userService = new UserService();
        req.setCharacterEncoding("UTF-8");
        String value = req.getParameter("profileButton");
        mainServletLogger.debug("Enter to the personal account");
        User user = userService.findEntityById((Long) req.getSession().getAttribute("userId"));
        resp.getWriter().write(new ObjectMapper().writeValueAsString(user));
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