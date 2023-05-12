package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.PizzaDaoImpl;
import by.fpmibsu.Dao.PizzaDao;
import by.fpmibsu.Entity.Pizza;

import java.sql.SQLException;

public class PizzaService {
    private PizzaDao pizzaDao;

    public PizzaService() {
        this.pizzaDao = new PizzaDaoImpl();
    }


    public Pizza findEntityById (Long id)  {
        return new PizzaDaoImpl().findEntityById(id);
    }


}
