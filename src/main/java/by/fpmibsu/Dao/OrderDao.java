package by.fpmibsu.Dao;

import by.fpmibsu.Entity.Order;

import java.sql.SQLException;

public interface OrderDao extends BaseDao<Long, Order> {

    void addToMMDrink (Long orderId, Long drinkId, Integer numberOfDrinks) throws SQLException;

    void addToMMPizza (Long orderId, Long pizzaId, Integer numberOfPizzas) throws SQLException;
}
