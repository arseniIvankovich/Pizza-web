package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.DrinkDaoImpl;
import by.fpmibsu.Dao.DaoImpl.PizzaDaoImpl;
import by.fpmibsu.Dao.DrinkDao;
import by.fpmibsu.Dao.PizzaDao;
import by.fpmibsu.Entity.Pizza;

import java.sql.SQLException;
import java.util.List;

public class PizzaService {
    private PizzaDao pizzaDao;

    public PizzaService() {
        this.pizzaDao = new PizzaDaoImpl();
    }


    public Pizza findEntityById (Long id) throws SQLException {
        return new PizzaDaoImpl().findEntityById(id);
    }


}
