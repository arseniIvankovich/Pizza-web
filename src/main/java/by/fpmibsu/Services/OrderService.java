package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.OrderDaoImpl;
import by.fpmibsu.Dao.DaoImpl.PizzaDaoImpl;
import by.fpmibsu.Dao.OrderDao;
import by.fpmibsu.Dao.PizzaDao;
import by.fpmibsu.Entity.Order;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class OrderService {
    private OrderDao orderDao;

    public OrderService() {
        this.orderDao = new OrderDaoImpl();
    }
    public Order createOrder (Order order) throws SQLException {
        order.setStatus(false);
        order.setDeliveryDate(new Timestamp(new java.util.Date().getTime() + 3600000));
        return new OrderDaoImpl().create(order);
    }

    public Order findEntityById(Long id) throws SQLException {
        return new OrderDaoImpl().findEntityById(id);
    }


    public void update(Order order) throws SQLException {
        new OrderDaoImpl().update(order);
    }


}