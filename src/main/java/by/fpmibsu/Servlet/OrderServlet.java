package by.fpmibsu.Servlet;

import by.fpmibsu.Entity.Drink;
import by.fpmibsu.Entity.Order;
import by.fpmibsu.Entity.Pizza;
import by.fpmibsu.Entity.User;
import by.fpmibsu.Services.OrderService;
import by.fpmibsu.Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        Long id = (Long) req.getSession().getAttribute("userId");
        User user = userService.findEntityById(id);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/jsp/order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        OrderService orderService = new OrderService();
        req.setCharacterEncoding("UTF-8");
        String pizza = req.getParameter("pizza");
        String drink = req.getParameter("drinks");
        String paymentMethod = req.getParameter("select");
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        List<Pizza> pizzas = objectMapper.readValue(pizza, typeFactory.constructCollectionType(List.class, Pizza.class));
        List<Drink> drinks = objectMapper.readValue(drink, typeFactory.constructCollectionType(List.class, Drink.class));
        User user = userService.findEntityById((Long) req.getSession().getAttribute("userId"));

        Order order = new Order(drinks, pizzas, paymentMethod);
        orderService.createOrder(order);
        user.setOrder(order);
        userService.editOrder(user);

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
