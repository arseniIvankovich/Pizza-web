package by.fpmibsu.Dao.DaoImpl;

import by.fpmibsu.Dao.HikariCPDataSource;
import by.fpmibsu.Dao.UserDao;
import by.fpmibsu.Entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import javax.sql.DataSource;
import java.util.List;

public class UserDaoImpl  implements UserDao {
    private DataSource dataSource;
    public UserDaoImpl () {
        this.dataSource = HikariCPDataSource.getDataSource();
    }
    static final Logger userDaoLogger = LogManager.getLogger(UserDaoImpl.class);
    static final Logger rootLogger = LogManager.getRootLogger();

    @Override
    public User findEntityById(Long id) {
        User user = new User();
        final String SQL_SELECT_BY_ID = "SELECT \"UserID\", \"Role_id\", \"First_SecondName\", \"Password\", \"Email\", \"Phone_number\", \"Address_id\"\n" +
                "\tFROM public.\"User\" WHERE \"UserID\" = ?;";
        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)){
            userDaoLogger.info("Got connection to the db");
            preparedStatement.setLong(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();

            while (resultSet.next()) {
                user.setUserId(resultSet.getLong("UserID"));
                user.setRole(new RoleDaoImpl().findEntityById(resultSet.getLong("Role_id")));
                user.setFirstName_lastName(resultSet.getString("First_SecondName"));
                user.setPassword(resultSet.getString("Password"));
                user.setEmail(resultSet.getString("Email"));
                user.setTelephone(resultSet.getString("Phone_number"));
                user.setAddresses(new AddressDaoImpl().findEntityById(resultSet.getLong("Address_id")));
            }
        } catch (SQLException e) {
            rootLogger.error("Error: ", e);
        }

        return user;
    }

    @Override
    public boolean delete(User user) {
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"User\"\n" +
                "\tWHERE \"Email\" = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)){
            userDaoLogger.info("Got connection to the db");
            preparedStatement.setString(1,user.getEmail());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            rootLogger.error("Error: ", e);
        }
    return false;
    }

    @Override
    public boolean delete(Long id) {
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"User\"\n" +
                "\tWHERE \"UserID\" = ?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)){
            userDaoLogger.info("Got connection to the db");
            preparedStatement.setLong(1,id);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            rootLogger.error("Error: ", e);
        }
    return false;
    }

    @Override
    public User create(User user) {
        final String SQL_CREATE_USER = "INSERT INTO public.\"User\"(\n" +
                "\t\"Role_id\", \"First_SecondName\", \"Password\", \"Email\", \"Phone_number\", \"Address_id\")\n" +
                "\tVALUES (?, ?, ?, ?, ?, ?);";


        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_USER)){
            userDaoLogger.info("Got connection to the db");
            preparedStatement.setLong(1,user.getRole().getId());
            preparedStatement.setString(2,user.getFirstName_lastName());
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.setString(4,user.getEmail());
            preparedStatement.setString(5,user.getTelephone());
            preparedStatement.setLong(6,user.getAddresses().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            rootLogger.error("Error: ", e);
        }
        return user;

    }

    @Override
    public void update(User user) {
        final String SQL_UPDATE = "UPDATE public.\"User\"\n" +
                "\tSET  \"First_SecondName\"=?, \"Email\"=?, \"Phone_number\"=?, \"Address_id\"=?\n" +
                "\tWHERE \"UserID\" = ?;";


        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)){
            userDaoLogger.info("Got connection to the db");
            preparedStatement.setString(1,user.getFirstName_lastName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getTelephone());
            preparedStatement.setLong(4,user.getAddresses().getId());
            preparedStatement.setLong(5,user.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            rootLogger.error("Error: ", e);
        }

    }
    @Override
    public void updateOrder(User user) {
        final String SQL_UPDATE = "UPDATE public.\"User\"\n" +
                "\tSET  \"Order_id\"=?\n" +
                "\tWHERE \"UserID\"=?";


        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)){
            userDaoLogger.info("Got connection to the db");
            preparedStatement.setLong(1,user.getOrder().getId());
            preparedStatement.setLong(2,user.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            rootLogger.error("Error: ", e);
        }

    }

    @Override
    public List<User> getOrderedUsers()  {
        final String SQL_SELECT_ALL = "SELECT \"UserID\", \"Role_id\", \"First_SecondName\", \"Password\", \"Phone_number\", \"Address_id\", \"Order_id\", \"Email\"\n" +
                "\tFROM public.\"User\" WHERE \"Order_id\" IS NOT NULL;";

        List<User> users = new ArrayList<>();


        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()){
            userDaoLogger.info("Got connection to the db");
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getLong("UserID"));
                user.setRole(new RoleDaoImpl().findEntityById(resultSet.getLong("Role_id")));
                user.setFirstName_lastName(resultSet.getString("First_SecondName"));
                user.setEmail(resultSet.getString("Email"));
                user.setTelephone(resultSet.getString("Phone_number"));
                user.setAddresses(new AddressDaoImpl().findEntityById(resultSet.getLong("Address_id")));
                user.setOrder(new OrderDaoImpl().findEntityById(resultSet.getLong("Order_id")));
                users.add(user);
            }
        } catch (SQLException e) {
            rootLogger.error("Error: ", e);
        }

        return users;
    }

    @Override
    public List<User> getAllNotAdmin() {
        final String SQL_SELECT_ALL = "SELECT \"UserID\", \"Role_id\", \"First_SecondName\", \"Password\", \"Phone_number\", \"Address_id\", \"Order_id\", \"Email\"\n" +
                "\tFROM public.\"User\" WHERE \"Role_id\" != 1;";

        List<User> users = new ArrayList<>();


        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            userDaoLogger.info("Got connection to the db");
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getLong("UserID"));
                user.setRole(new RoleDaoImpl().findEntityById(resultSet.getLong("Role_id")));
                user.setFirstName_lastName(resultSet.getString("First_SecondName"));
                user.setEmail(resultSet.getString("Email"));
                user.setTelephone(resultSet.getString("Phone_number"));
                user.setAddresses(new AddressDaoImpl().findEntityById(resultSet.getLong("Address_id")));
                user.setOrder(new OrderDaoImpl().findEntityById(resultSet.getLong("Order_id")));
                users.add(user);
            }
        } catch (SQLException e) {
            rootLogger.error("Error: ", e);
        }

        return users;
    }



    @Override
    public User findByEmail(String email)  {
        User user = new User();
        final String SQL_SELECT_BY_ID = "SELECT \"UserID\", \"Role_id\", \"First_SecondName\", \"Password\", \"Email\", \"Phone_number\", \"Address_id\", \"Order_id\"\n" +
                "\tFROM public.\"User\" WHERE \"Email\" = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)){
            userDaoLogger.info("Got connection to the db");
            preparedStatement.setString(1,email);
            ResultSet resultSet= preparedStatement.executeQuery();

            while (resultSet.next()) {
                user.setUserId(resultSet.getLong("UserID"));
                user.setRole(new RoleDaoImpl().findEntityById(resultSet.getLong("Role_id")));
                user.setFirstName_lastName(resultSet.getString("First_SecondName"));
                user.setPassword(resultSet.getString("Password"));
                user.setEmail(resultSet.getString("Email"));
                user.setTelephone(resultSet.getString("Phone_number"));
                user.setAddresses(new AddressDaoImpl().findEntityById(resultSet.getLong("Address_id")));
                user.setOrder(new OrderDaoImpl().findEntityById(resultSet.getLong("Order_id")));
            }
        } catch (SQLException e) {
            rootLogger.error("Error: ", e);
        }


        return user;
    }

    @Override
    public Boolean checkUserByEmail(String email)  {

        User user = new User();
        final String SQL_SELECT_BY_ID = "SELECT \"UserID\", \"Role_id\", \"First_SecondName\", \"Password\", \"Email\", \"Phone_number\", \"Address_id\", \"Order_id\"\n" +
                "\tFROM public.\"User\" WHERE \"Email\" = ?;";
        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)){
            userDaoLogger.info("Got connection to the db");
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            rootLogger.error("Error: ", e);
        }
    return false;
    }

}