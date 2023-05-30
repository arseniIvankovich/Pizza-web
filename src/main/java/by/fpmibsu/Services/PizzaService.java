package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.PizzaDaoImpl;
import by.fpmibsu.Dao.PizzaDao;

public class PizzaService {
    private PizzaDao pizzaDao;

    public PizzaService() {
        this.pizzaDao = new PizzaDaoImpl();
    }


}
