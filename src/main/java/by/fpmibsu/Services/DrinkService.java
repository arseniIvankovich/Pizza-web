package by.fpmibsu.Services;

import by.fpmibsu.Dao.AddressDao;
import by.fpmibsu.Dao.DaoImpl.AddressDaoImpl;
import by.fpmibsu.Dao.DaoImpl.DrinkDaoImpl;
import by.fpmibsu.Dao.DrinkDao;
import by.fpmibsu.Entity.Drink;

import java.sql.SQLException;
import java.util.List;

public class DrinkService {
    private DrinkDao drinkDao;

    public DrinkService() {
        this.drinkDao = new DrinkDaoImpl();
    }

    public Drink findEntityById(Long id) throws SQLException {
        return new DrinkDaoImpl().findEntityById(id);
    }

    public Drink findByNameCapacity(String name, Double capacity) throws SQLException {
        return new DrinkDaoImpl().findByNameCapacity(name,capacity);
    }
}
