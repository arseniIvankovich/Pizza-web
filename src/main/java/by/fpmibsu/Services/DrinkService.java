package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.DrinkDaoImpl;
import by.fpmibsu.Dao.DrinkDao;
import by.fpmibsu.Entity.Drink;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class DrinkService {
    private DrinkDao drinkDao;

    public DrinkService() {
        this.drinkDao = new DrinkDaoImpl();
    }

}
