package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.DrinkDaoImpl;
import by.fpmibsu.Dao.DrinkDao;
import by.fpmibsu.Entity.Drink;

import java.sql.SQLException;

public class DrinkService {
    private DrinkDao drinkDao;

    public DrinkService() {
        this.drinkDao = new DrinkDaoImpl();
    }

    public Drink findEntityById(Long id)  {
        return new DrinkDaoImpl().findEntityById(id);
    }

    public Drink findByNameCapacity(String name, Double capacity)  {
        return new DrinkDaoImpl().findByNameCapacity(name,capacity);
    }
}
