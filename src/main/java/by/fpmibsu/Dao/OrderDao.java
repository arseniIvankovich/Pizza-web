package by.fpmibsu.Dao;

import by.fpmibsu.Entity.Order;

public interface OrderDao extends BaseDao<Long, Order> {

    void addToMMDrink(Long orderId, Long drinkId, Integer numberOfDrinks);

    void addToMMPizza(Long orderId, Long pizzaId, Integer numberOfPizzas);

}
