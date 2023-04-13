package by.fpmibsu.Dao;

import by.fpmibsu.Entity.Drink;

public interface DrinkDao extends BaseDao<Long, Drink> {
    Drink findByNameCapacity (String name, Double capacity);
}

