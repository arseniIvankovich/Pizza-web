package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.OrderDaoImpl;

public class OrderService {
    final OrderDaoImpl orderDao;

    public OrderService(OrderDaoImpl orderDao) {
        this.orderDao = orderDao;
    }

}
