package by.fpmibsu.Servlet;

import by.fpmibsu.Entity.Drink;
import by.fpmibsu.Entity.Order;
import by.fpmibsu.Entity.Pizza;
import by.fpmibsu.Entity.User;
import by.fpmibsu.Services.OrderService;
import by.fpmibsu.Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    static final Logger orderServletLogger = LogManager.getLogger(OrderServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        orderServletLogger.info("Enter the order page");
        Long id = (Long) req.getSession().getAttribute("userId");
        User user = userService.findEntityById(id);
        req.setAttribute("user", user);
        req.getRequestDispatcher("/jsp/order.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        OrderService orderService = new OrderService();
        orderServletLogger.debug("Make order in this account");
        String pizza = req.getParameter("pizza");
        String drink = req.getParameter("drinks");
        if ((pizza== null && drink == null) || (pizza.equals("[]") && drink.equals("[]"))) {
            orderServletLogger.warn("Empty order");
            req.setAttribute("EmptyOrder",true);
            req.getRequestDispatcher("/jsp/order.jsp").forward(req,resp);
            return;
        }
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