package by.fpmibsu.Dao;

import by.fpmibsu.Entity.Pizza;

import java.sql.SQLException;
import java.util.List;

public interface PizzaDao extends BaseDao<Long, Pizza>{
    List<Pizza> findInRange (Double lowerBound, Double upperBound) throws SQLException;

    Pizza findByNameTypeDroughSize (String name, Boolean typeDrough, Boolean size) throws SQLException;
}
