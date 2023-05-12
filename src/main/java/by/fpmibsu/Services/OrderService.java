package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.OrderDaoImpl;
import by.fpmibsu.Entity.Order;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class OrderService {

    public Order createOrder (Order order) throws SQLException {
        order.setStatus(false);
        order.setDeliveryDate(new Timestamp(new java.util.Date().getTime() + 3600000));
        return new OrderDaoImpl().create(order);
    }

    public List<Order> findAll() throws SQLException {
        return new OrderDaoImpl().findAll();
    }

    public Order findEntityById(Long id) throws SQLException {
        return new OrderDaoImpl().findEntityById(id);
    }

    public void addToMMDrink(Long orderId, Long drinkId, Integer numberOfDrinks) throws SQLException {
        new OrderDaoImpl().addToMMDrink(orderId,drinkId,numberOfDrinks);
    }

    public void addToMMPizza(Long orderId, Long pizzaId, Integer numberOfPizzas) throws SQLException {
        new OrderDaoImpl().addToMMPizza(orderId,pizzaId,numberOfPizzas);
    }

    public void update(Order order) throws SQLException {
        new OrderDaoImpl().update(order);
    }


}