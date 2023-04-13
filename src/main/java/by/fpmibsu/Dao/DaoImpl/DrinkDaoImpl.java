package by.fpmibsu.Dao.DaoImpl;

import by.fpmibsu.Dao.DrinkDao;
import by.fpmibsu.Entity.Drink;
import by.fpmibsu.Services.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrinkDaoImpl extends Util implements DrinkDao {
    Connection connection = getConnection();
    @Override
    public List<Drink> findAll() {
        final String SQL_SELECT_ALL = "SELECT \"DrinkID\", \"Name\", \"Capacity\", \"Price\"\n" +
                "\tFROM public.\"Drink\";";
        List<Drink> drinksList = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

            while (resultSet.next()) {
                Drink drink = new Drink();
                drink.setDrinkID(resultSet.getLong("DrinkID"));
                drink.setName(resultSet.getString("Name"));
                drink.setCapacity(resultSet.getDouble("Capacity"));
                drink.setPrice(resultSet.getDouble("Price"));

                drinksList.add(drink);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close(statement);
            close(connection);
        }
        return drinksList;
    }

    @Override
    public Drink findEntityById(Long id) {
        PreparedStatement preparedStatement = null;
        Drink drink = new Drink();
        final String SQL_SELECT_BY_ID = "SELECT \"DrinkID\", \"Name\", \"Capacity\", \"Price\"\n" +
                "\tFROM public.\"Drink\" WHERE \"DrinkID\" = ?;";
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setLong(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();

            while (resultSet.next()) {
                drink.setId(resultSet.getLong("DrinkID"));
                drink.setName(resultSet.getString("Name"));
                drink.setCapacity(resultSet.getDouble("Capacity"));
                drink.setPrice(resultSet.getDouble("Price"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
        return drink;
    }

    @Override
    public boolean delete(Drink drink) {
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"Drink\"\n" +
                "\tWHERE \"Name\" = ?;";
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID);
            preparedStatement.setString(1,drink.getName());

            preparedStatement.executeUpdate();
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"Drink\"\n" +
                "\tWHERE \"DrinkID\" = ?;";
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID);
            preparedStatement.setLong(1,id);

            preparedStatement.executeUpdate();
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
        return false;
    }

    @Override
    public boolean create(Drink drink) {
        final String SQL_CREATE_ADDRESS = "INSERT INTO public.\"Drink\"(\n" +
                "\t\"Name\", \"Capacity\", \"Price\")\n" +
                "\tVALUES (?, ?, ?);";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_CREATE_ADDRESS);
            preparedStatement.setString(1,drink.getName());
            preparedStatement.setDouble(2,drink.getCapacity());
            preparedStatement.setDouble(3,drink.getPrice());

            preparedStatement.executeUpdate();
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
        return false;
    }

    @Override
    public void update(Drink drink) {
        final String SQL_UPDATE = "UPDATE public.\"Drink\"\n" +
                "\tSET  \"Name\"=?, \"Capacity\"=?, \"Price\"=?\n" +
                "\tWHERE \"DrinkID\" = ?;";

        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(SQL_UPDATE);

            preparedStatement.setString(1,drink.getName());
            preparedStatement.setDouble(2,drink.getCapacity());
            preparedStatement.setDouble(3,drink.getPrice());
            preparedStatement.setLong(4,drink.getDrinkID());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public Drink findByNameCapacity(String name, Double capacity) {
        PreparedStatement preparedStatement = null;
        Drink drink = new Drink();
        final String SQL_SELECT_BY_NAME_CAPACITY = "SELECT \"DrinkID\", \"Name\", \"Capacity\", \"Price\"\n" +
                "\tFROM public.\"Drink\" WHERE \"Name\" = ? AND \"Capacity\" = ?;";
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_NAME_CAPACITY);
            preparedStatement.setString(1,name);
            preparedStatement.setDouble(2,capacity);
            ResultSet resultSet= preparedStatement.executeQuery();

            while (resultSet.next()) {
                drink.setId(resultSet.getLong("DrinkID"));
                drink.setName(resultSet.getString("Name"));
                drink.setCapacity(resultSet.getDouble("Capacity"));
                drink.setPrice(resultSet.getDouble("Price"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
        return drink;
    }
}
