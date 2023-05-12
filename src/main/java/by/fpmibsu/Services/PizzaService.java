package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.PizzaDaoImpl;
import by.fpmibsu.Entity.Pizza;

import java.sql.SQLException;
import java.util.List;

public class PizzaService {


    public Pizza findByNameTypeDroughSize (String name, Boolean typeDrough, Boolean size) throws SQLException {
        return new PizzaDaoImpl().findByNameTypeDroughSize(name,typeDrough,size);
    }

    public Pizza findEntityById (Long id) throws SQLException {
        return new PizzaDaoImpl().findEntityById(id);
    }

    public List<Pizza> findAll() throws SQLException {
        return new PizzaDaoImpl().findAll();
    }

}
