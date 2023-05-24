package by.fpmibsu.Servlet;

import by.fpmibsu.Entity.User;
import by.fpmibsu.Services.OrderService;
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

@WebServlet("/courier")
public class CourierServlet extends HttpServlet {
    static final Logger courierServletLogger = LogManager.getLogger(CourierServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        courierServletLogger.info("Enter the courier page");
        UserService userService = new UserService();
        List<User> users = (ArrayList<User>) userService.getUndeliveredOrdersForUsers();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/jsp/courier.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        courierServletLogger.debug("Change status on order");
        String email = req.getParameter("email");
        UserService userService = new UserService();
        OrderService orderService = new OrderService();
        User user = userService.findByEmail(email);
        user.getOrder().setStatus(true);
        orderService.update(user.getOrder());

        doGet(req, resp);
    }
}