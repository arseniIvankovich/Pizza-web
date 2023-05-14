package by.fpmibsu.Dao;

import by.fpmibsu.Entity.Drink;
import by.fpmibsu.Entity.Order;
import by.fpmibsu.Entity.Pizza;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface OrderDao extends BaseDao<Long, Order> {

    void addToMMDrink (Long orderId, Long drinkId, Integer numberOfDrinks);

    void addToMMPizza (Long orderId, Long pizzaId, Integer numberOfPizzas) ;

}
