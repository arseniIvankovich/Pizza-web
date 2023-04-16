package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.DrinkDaoImpl;
import by.fpmibsu.Entity.Drink;

import java.sql.SQLException;
import java.util.List;

public class DrinkService {
    DrinkDaoImpl drinkDao;

    public DrinkService(DrinkDaoImpl drinkDao) {
        this.drinkDao = drinkDao;
    }

    public List<Drink> findAll() throws SQLException {
        return drinkDao.findAll();
    }

    public Drink findEntityById(Long id) throws SQLException {
        return drinkDao.findEntityById(id);
    }

    public Drink findByNameCapacity(String name, Double capacity) throws SQLException {
        return drinkDao.findByNameCapacity(name,capacity);
    }
}
