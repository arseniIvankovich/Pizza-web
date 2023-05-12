package by.fpmibsu.Dao.DaoImpl;

import by.fpmibsu.Dao.DrinkDao;
import by.fpmibsu.Dao.HikariCPDataSource;
import by.fpmibsu.Entity.Drink;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DrinkDaoImpl implements DrinkDao {
    private final DataSource dataSource;

    public DrinkDaoImpl() {
        this.dataSource = HikariCPDataSource.getDataSource();
    }

    @Override
    public Drink findEntityById(Long id)  {
        Drink drink = new Drink();
        final String SQL_SELECT_BY_ID = "SELECT \"DrinkID\", \"Name\", \"Capacity\", \"Price\"\n" +
                "\tFROM public.\"Drink\" WHERE \"DrinkID\" = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                drink.setId(resultSet.getLong("DrinkID"));
                drink.setName(resultSet.getString("Name"));
                drink.setCapacity(resultSet.getDouble("Capacity"));
                drink.setPrice(resultSet.getDouble("Price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drink;
    }

    @Override
    public boolean delete(Drink drink)  {
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"Drink\"\n" +
                "\tWHERE \"Name\" = ?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            preparedStatement.setString(1, drink.getName());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean delete(Long id)  {
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"Drink\"\n" +
                "\tWHERE \"DrinkID\" = ?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public Drink create(Drink drink)  {
        final String SQL_CREATE_ADDRESS = "INSERT INTO public.\"Drink\"(\n" +
                "\t\"Name\", \"Capacity\", \"Price\")\n" +
                "\tVALUES (?, ?, ?);";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_ADDRESS)) {
            preparedStatement.setString(1, drink.getName());
            preparedStatement.setDouble(2, drink.getCapacity());
            preparedStatement.setDouble(3, drink.getPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drink;
    }

    @Override
    public void update(Drink drink)  {
        final String SQL_UPDATE = "UPDATE public.\"Drink\"\n" +
                "\tSET  \"Name\"=?, \"Capacity\"=?, \"Price\"=?\n" +
                "\tWHERE \"DrinkID\" = ?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            preparedStatement.setString(1, drink.getName());
            preparedStatement.setDouble(2, drink.getCapacity());
            preparedStatement.setDouble(3, drink.getPrice());
            preparedStatement.setLong(4, drink.getDrinkID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Drink findByNameCapacity(String name, Double capacity)  {
        Drink drink = new Drink();
        final String SQL_SELECT_BY_NAME_CAPACITY = "SELECT \"DrinkID\", \"Name\", \"Capacity\", \"Price\"\n" +
                "\tFROM public.\"Drink\" WHERE \"Name\" = ? AND \"Capacity\" = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_NAME_CAPACITY)) {
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, capacity);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                drink.setId(resultSet.getLong("DrinkID"));
                drink.setName(resultSet.getString("Name"));
                drink.setCapacity(resultSet.getDouble("Capacity"));
                drink.setPrice(resultSet.getDouble("Price"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return drink;
    }
}
