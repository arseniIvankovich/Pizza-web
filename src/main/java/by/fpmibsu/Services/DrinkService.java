package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.DrinkDaoImpl;
import by.fpmibsu.Entity.Drink;

import java.sql.SQLException;
import java.util.List;

public class DrinkService {

    public List<Drink> findAll() throws SQLException {
        return new DrinkDaoImpl().findAll();
    }

    public Drink findEntityById(Long id) throws SQLException {
        return new DrinkDaoImpl().findEntityById(id);
    }

    public Drink findByNameCapacity(String name, Double capacity) throws SQLException {
        return new DrinkDaoImpl().findByNameCapacity(name,capacity);
    }
}
