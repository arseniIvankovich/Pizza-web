package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.PizzaDaoImpl;
import by.fpmibsu.Entity.Pizza;

import java.sql.SQLException;
import java.util.List;

public class PizzaService {
    final PizzaDaoImpl pizzaDao;

    public PizzaService(PizzaDaoImpl pizzaDao) {
        this.pizzaDao = pizzaDao;
    }

    public Pizza findByNameTypeDroughSize (String name, Boolean typeDrough, Boolean size) throws SQLException {
        return pizzaDao.findByNameTypeDroughSize(name,typeDrough,size);
    }

    public Pizza findEntityById (Long id) throws SQLException {
        return pizzaDao.findEntityById(id);
    }

    public List<Pizza> findAll() throws SQLException {
        return pizzaDao.findAll();
    }

}
