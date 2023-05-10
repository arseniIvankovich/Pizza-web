package by.fpmibsu.Servlet;

import by.fpmibsu.Dao.DaoImpl.AddressDaoImpl;
import by.fpmibsu.Dao.DaoImpl.OrderDaoImpl;
import by.fpmibsu.Dao.DaoImpl.RoleDaoImpl;
import by.fpmibsu.Dao.DaoImpl.UserDaoImpl;
import by.fpmibsu.Entity.Drink;
import by.fpmibsu.Entity.Order;
import by.fpmibsu.Entity.Pizza;
import by.fpmibsu.Entity.User;
import by.fpmibsu.Services.AddressService;
import by.fpmibsu.Services.OrderService;
import by.fpmibsu.Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.*;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService(new UserDaoImpl(),new OrderDaoImpl(), new AddressDaoImpl(), new RoleDaoImpl());
        Long id = (Long) req.getSession().getAttribute("userId");
        User user;
        try {
            user = userService.findEntityById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("user",user);
        req.getRequestDispatcher("/jsp/order.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService(new UserDaoImpl(),new OrderDaoImpl(), new AddressDaoImpl(), new RoleDaoImpl());
        OrderService orderService = new OrderService(new OrderDaoImpl());
        req.setCharacterEncoding("UTF-8");
        String pizza = req.getParameter("pizza");
        String drink = req.getParameter("drinks");
        String paymentMethod = req.getParameter("select");
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        List<Pizza> pizzas = objectMapper.readValue(pizza, typeFactory.constructCollectionType(List.class, Pizza.class));
        List<Drink> drinks = objectMapper.readValue(drink, typeFactory.constructCollectionType(List.class, Drink.class));
        User user;
        try {
            user = userService.findEntityById((Long) req.getSession().getAttribute("userId"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Order order = new Order(drinks,pizzas,"Наличные");
        try {
            orderService.createOrder(order);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        user.setOrder(order);
        try {
            userService.editOrder(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
