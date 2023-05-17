package by.fpmibsu.Services;

import by.fpmibsu.Dao.BaseAddressDao;
import by.fpmibsu.Dao.DaoImpl.DrinkDaoImpl;
import by.fpmibsu.Dao.DaoImpl.PizzaDaoImpl;
import by.fpmibsu.Dao.PizzaDao;
import by.fpmibsu.Entity.Pizza;

public class PizzaService {
    private  PizzaDao pizzaDao;

    public PizzaService() {
        this.pizzaDao = new PizzaDaoImpl();
    }



}
