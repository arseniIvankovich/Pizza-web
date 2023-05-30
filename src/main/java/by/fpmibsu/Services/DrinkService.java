package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.DrinkDaoImpl;
import by.fpmibsu.Dao.DrinkDao;

public class DrinkService {
    private DrinkDao drinkDao;

    public DrinkService() {
        this.drinkDao = new DrinkDaoImpl();
    }

}
