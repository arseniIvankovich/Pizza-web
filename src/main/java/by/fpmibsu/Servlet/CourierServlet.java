package by.fpmibsu.Servlet;

import by.fpmibsu.Dao.DaoImpl.AddressDaoImpl;
import by.fpmibsu.Dao.DaoImpl.OrderDaoImpl;
import by.fpmibsu.Dao.DaoImpl.RoleDaoImpl;
import by.fpmibsu.Dao.DaoImpl.UserDaoImpl;
import by.fpmibsu.Entity.Order;
import by.fpmibsu.Entity.User;
import by.fpmibsu.Services.OrderService;
import by.fpmibsu.Services.UserService;

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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        List<User> users;
        try {
            users = (ArrayList<User>)userService.getUndeliveredOrdersForUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("users",users);
        req.getRequestDispatcher("/jsp/courier.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        UserService userService = new UserService();
        OrderService orderService = new OrderService();
        User user;
        try {
            user = userService.findByEmail(email);
            user.getOrder().setStatus(true);
            orderService.update(user.getOrder());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        doGet(req,resp);
    }
}
