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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    static final Logger loginServletLogger = LogManager.getLogger(LoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCorsHeaders(resp);
     //  loginServletLogger.debug("Enter login form");
       // req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCorsHeaders(resp);
        UserService userService = new UserService();
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        if (!userService.checkEmail(email)) {
            loginServletLogger.debug("Invalid email");
            resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            resp.getWriter().write("error");
         //  req.getRequestDispatcher(path).forward(req,resp);
            return;
        }
        String password = req.getParameter("password");
        User user = userService.findByEmail(email);
        if (!userService.checkLoginPassword(user.getEmail(),password)) {
            loginServletLogger.debug("Invalid login or password");
            resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
          //  req.getRequestDispatcher(path).forward(req,resp);
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("userId", user.getUserId());
        resp.getWriter().write(new ObjectMapper().writeValueAsString(user));

    /*    if (user.getRole().getRole().equals("Администратор"))
            resp.sendRedirect(req.getContextPath() + "/admin");
        else if (user.getRole().getRole().equals("Курьер"))
            resp.sendRedirect(req.getContextPath() + "/courier");
        else
            resp.sendRedirect(req.getContextPath() + "/profile");*/
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
