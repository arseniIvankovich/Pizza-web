package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.OrderDaoImpl;
import by.fpmibsu.Entity.Order;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class OrderService {
    final OrderDaoImpl orderDao;

    public OrderService(OrderDaoImpl orderDao) {
        this.orderDao = orderDao;
    }

    public Order createOrder (Order order) throws SQLException {
        order.setStatus(false);
        order.setDeliveryDate(new Timestamp(new java.util.Date().getTime() + 3600000));
        return orderDao.create(order);
    }

    public List<Order> findAll() throws SQLException {
        return orderDao.findAll();
    }

    public Order findEntityById(Long id) throws SQLException {
        return orderDao.findEntityById(id);
    }

    public void addToMMDrink(Long orderId, Long drinkId, Integer numberOfDrinks) throws SQLException {
        orderDao.addToMMDrink(orderId,drinkId,numberOfDrinks);
    }

    public void addToMMPizza(Long orderId, Long pizzaId, Integer numberOfPizzas) throws SQLException {
        orderDao.addToMMPizza(orderId,pizzaId,numberOfPizzas);
    }



}