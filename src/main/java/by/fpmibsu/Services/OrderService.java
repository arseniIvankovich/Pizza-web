package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.OrderDaoImpl;
import by.fpmibsu.Dao.OrderDao;
import by.fpmibsu.Entity.Order;

import java.sql.SQLException;
import java.sql.Timestamp;

public class OrderService {
    private OrderDao orderDao;

    public OrderService() {
        this.orderDao = new OrderDaoImpl();
    }

    public Order createOrder(Order order) {
        order.setStatus(false);
        order.setDeliveryDate(new Timestamp(new java.util.Date().getTime() + 3600000));
        return new OrderDaoImpl().create(order);
    }

    public Order findEntityById(Long id)  {
        return new OrderDaoImpl().findEntityById(id);
    }


    public void update(Order order)  {
        new OrderDaoImpl().update(order);
    }


}