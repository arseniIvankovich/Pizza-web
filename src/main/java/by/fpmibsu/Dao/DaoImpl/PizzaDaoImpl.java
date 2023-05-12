package by.fpmibsu.Dao.DaoImpl;

import by.fpmibsu.Dao.HikariCPDataSource;
import by.fpmibsu.Dao.PizzaDao;
import by.fpmibsu.Entity.Pizza;

import javax.sql.DataSource;
import java.sql.*;

public class PizzaDaoImpl  implements PizzaDao {
    private final DataSource dataSource;
    public PizzaDaoImpl () {
        this.dataSource = HikariCPDataSource.getDataSource();
    }

    @Override
    public Pizza findEntityById(Long id)  {
        Pizza pizza = new Pizza();
        final String SQL_SELECT_BY_ID = "SELECT \"PizzaID\", \"Name\", \"Ingredients\", \"TypeDrough\", \"BasicWeight\", \"Price\", \"Size\"\n" +
                "\tFROM public.\"Pizza\" WHERE \"PizzaID\" = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return pizza;
    }

    @Override
    public boolean delete(Pizza pizza) {
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"Pizza\"\n" +
                "\tWHERE \"Name\" = ?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)){
            preparedStatement.setString(1,pizza.getName());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return false;
    }

    @Override
    public boolean delete(Long id) {
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"Pizza\"\n" +
                "\tWHERE \"PizzaID\" = ?;";


        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)){
            preparedStatement.setLong(1,id);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    return false;
    }

    @Override
    public Pizza create(Pizza pizza) {
        final String SQL_CREATE_ADDRESS = "INSERT INTO public.\"Pizza\"(\n" +
                "\t\"Name\", \"Ingredients\", \"TypeDrough\", \"BasicWeight\", \"Price\", \"Size\")\n" +
                "\tVALUES (?, ?, ?, ?, ?, ?);";


        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_ADDRESS)){
            preparedStatement.setString(1,pizza.getName());
            preparedStatement.setString(2,pizza.getIngredients());
            preparedStatement.setBoolean(3,pizza.getDoughType());
            preparedStatement.setDouble(4,pizza.getWeight());
            preparedStatement.setDouble(5,pizza.getPrice());
            preparedStatement.setBoolean(6,pizza.getDoughType());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pizza;
    }

    @Override
    public void update(Pizza pizza) {
        final String SQL_UPDATE = "UPDATE public.\"Pizza\"\n" +
                "\tSET  \"Name\"=?, \"Ingredients\"=?, \"TypeDrough\"=?, \"BasicWeight\"=?, \"Price\"=?, \"Size\"=?\n" +
                "\tWHERE \"PizzaID\"=?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)){
            preparedStatement.setString(1,pizza.getName());
            preparedStatement.setString(2,pizza.getIngredients());
            preparedStatement.setBoolean(3,pizza.getDoughType());
            preparedStatement.setDouble(4,pizza.getWeight());
            preparedStatement.setDouble(5,pizza.getPrice());
            preparedStatement.setBoolean(6,pizza.getSize());
            preparedStatement.setLong(7,pizza.getPizzaId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}