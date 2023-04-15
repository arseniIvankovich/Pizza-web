package by.fpmibsu.Dao.DaoImpl;

import by.fpmibsu.Services.Util;
import by.fpmibsu.Dao.PizzaDao;
import by.fpmibsu.Entity.Pizza;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PizzaDaoImpl extends Util implements PizzaDao {
    Connection connection = getConnection();
    @Override
    public List<Pizza> findAll() throws SQLException{
        final String SQL_SELECT_ALL = "SELECT \"PizzaID\", \"Name\", \"Ingredients\", \"TypeDrough\", \"BasicWeight\", \"Price\", \"Size\"\n" +
                "\tFROM public.\"Pizza\"";
        List<Pizza> pizzaList = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

            while (resultSet.next()) {
                Pizza pizza = new Pizza();
                pizza.setPizzaId(resultSet.getLong("PizzaId"));
                pizza.setName(resultSet.getString("Name"));
                pizza.setIngredients(resultSet.getString("Ingredients"));
                pizza.setDoughType(resultSet.getBoolean("TypeDrough"));
                pizza.setSize(resultSet.getBoolean("Size"));
                pizza.setWeight(resultSet.getDouble("BasicWeight"));
                pizza.setWeight(resultSet.getDouble("Price"));

                pizzaList.add(pizza);
            }
        }
        finally {
            close(statement);
            close(connection);
        }
        return pizzaList;
    }

    @Override
    public Pizza findEntityById(Long id) throws SQLException {
        PreparedStatement preparedStatement = null;
        Pizza pizza = new Pizza();
        final String SQL_SELECT_BY_ID = "SELECT \"PizzaID\", \"Name\", \"Ingredients\", \"TypeDrough\", \"BasicWeight\", \"Price\", \"Size\"\n" +
                "\tFROM public.\"Pizza\" WHERE \"PizzaID\" = ?;";
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setLong(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()) {
                pizza.setId(resultSet.getLong("PizzaID"));
                pizza.setName(resultSet.getString("Name"));
                pizza.setIngredients(resultSet.getString("Ingredients"));
                pizza.setDoughType(resultSet.getBoolean("TypeDrough"));
                pizza.setWeight(resultSet.getDouble("BasicWeight"));
                pizza.setWeight(resultSet.getDouble("Price"));
                pizza.setSize(resultSet.getBoolean("Size"));
            }
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
        return pizza;
    }

    @Override
    public boolean delete(Pizza pizza) throws SQLException{
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"Pizza\"\n" +
                "\tWHERE \"Name\" = ?;";
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID);
            preparedStatement.setString(1,pizza.getName());

            preparedStatement.executeUpdate();
            return true;
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public boolean delete(Long id) throws SQLException{
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"Pizza\"\n" +
                "\tWHERE \"PizzaID\" = ?;";
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID);
            preparedStatement.setLong(1,id);

            preparedStatement.executeUpdate();
            return true;
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public boolean create(Pizza pizza) throws SQLException{
        final String SQL_CREATE_ADDRESS = "INSERT INTO public.\"Pizza\"(\n" +
                "\t\"Name\", \"Ingredients\", \"TypeDrough\", \"BasicWeight\", \"Price\", \"Size\")\n" +
                "\tVALUES (?, ?, ?, ?, ?, ?);";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_CREATE_ADDRESS);
            preparedStatement.setString(1,pizza.getName());
            preparedStatement.setString(2,pizza.getIngredients());
            preparedStatement.setBoolean(3,pizza.getDoughType());
            preparedStatement.setDouble(4,pizza.getWeight());
            preparedStatement.setDouble(5,pizza.getPrice());
            preparedStatement.setBoolean(6,pizza.getDoughType());

            preparedStatement.executeUpdate();
            return true;
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public void update(Pizza pizza) throws SQLException{
        final String SQL_UPDATE = "UPDATE public.\"Pizza\"\n" +
                "\tSET  \"Name\"=?, \"Ingredients\"=?, \"TypeDrough\"=?, \"BasicWeight\"=?, \"Price\"=?, \"Size\"=?\n" +
                "\tWHERE \"PizzaID\"=?;";

        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(SQL_UPDATE);

            preparedStatement.setString(1,pizza.getName());
            preparedStatement.setString(2,pizza.getIngredients());
            preparedStatement.setBoolean(3,pizza.getDoughType());
            preparedStatement.setDouble(4,pizza.getWeight());
            preparedStatement.setDouble(5,pizza.getPrice());
            preparedStatement.setBoolean(6,pizza.getSize());
            preparedStatement.setLong(7,pizza.getPizzaId());

            preparedStatement.executeUpdate();
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public List<Pizza> findInRange(Double lowerBound, Double upperBound) throws SQLException{
        return null;
    }

    @Override
    public Pizza findByNameTypeDroughSize(String name, Boolean typeDrough, Boolean size) throws SQLException{
        PreparedStatement preparedStatement = null;
        Pizza pizza = new Pizza();
        final String SQL_SELECT_BY_NAME_TYPE_SIZE = "SELECT \"PizzaID\", \"Name\", \"Ingredients\", \"TypeDrough\", \"BasicWeight\", \"Price\", \"Size\"\n" +
                "\tFROM public.\"Pizza\" WHERE \"Name\" = ? AND \"TypeDrough\" = ? AND \"Size\" = ?;";

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_NAME_TYPE_SIZE);
            preparedStatement.setString(1,name);
            preparedStatement.setBoolean(2,typeDrough);
            preparedStatement.setBoolean(3,size);

            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()) {
                pizza.setId(resultSet.getLong("PizzaID"));
                pizza.setName(resultSet.getString("Name"));
                pizza.setIngredients(resultSet.getString("Ingredients"));
                pizza.setDoughType(resultSet.getBoolean("TypeDrough"));
                pizza.setWeight(resultSet.getDouble("BasicWeight"));
                pizza.setWeight(resultSet.getDouble("Price"));
                pizza.setSize(resultSet.getBoolean("Size"));
            }
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
        return pizza;
    }
}