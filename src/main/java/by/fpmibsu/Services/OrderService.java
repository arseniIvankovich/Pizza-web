package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.OrderDaoImpl;
import by.fpmibsu.Dao.OrderDao;
import by.fpmibsu.Entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Timestamp;

public class OrderService {
    private OrderDao orderDao;

    public OrderService() {
        this.orderDao = new OrderDaoImpl();
    }
    static final Logger orderServiceLogger = LogManager.getLogger(OrderService.class);

    public Order createOrder(Order order) {
        orderServiceLogger.debug("Create new order");
        order.setStatus(false);
        order.setDeliveryDate(new Timestamp(new java.util.Date().getTime() + 3600000));
        return new OrderDaoImpl().create(order);
    }

    public Order findEntityById(Long id)  {
        orderServiceLogger.debug("Find order by id");
        return new OrderDaoImpl().findEntityById(id);
    }


    public void update(Order order)  {
        orderServiceLogger.debug("Update order id");
        new OrderDaoImpl().update(order);
    }


}