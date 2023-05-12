package by.fpmibsu.Dao.DaoImpl;

import by.fpmibsu.Dao.HikariCPDataSource;
import by.fpmibsu.Services.Util;
import by.fpmibsu.Dao.UserDao;
import by.fpmibsu.Entity.*;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import javax.sql.DataSource;
import java.util.List;

public class UserDaoImpl extends Util implements UserDao {
    private DataSource dataSource;
    public UserDaoImpl () {
        this.dataSource = HikariCPDataSource.getDataSource();
    }

    @Override
    public User findEntityById(Long id) throws SQLException{User user = new User();
        final String SQL_SELECT_BY_ID = "SELECT \"UserID\", \"Role_id\", \"First_SecondName\", \"Password\", \"Email\", \"Phone_number\", \"Address_id\"\n" +
                "\tFROM public.\"User\" WHERE \"UserID\" = ?;";
        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)){

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
        }
        return user;
    }

    @Override
    public boolean delete(User user) throws SQLException{
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"User\"\n" +
                "\tWHERE \"Email\" = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)){

            preparedStatement.setString(1,user.getEmail());

            preparedStatement.executeUpdate();
            return true;
        }
    }

    @Override
    public boolean delete(Long id) throws SQLException{
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"User\"\n" +
                "\tWHERE \"UserID\" = ?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)){
            preparedStatement.setLong(1,id);

            preparedStatement.executeUpdate();
            return true;
        }
    }

    @Override
    public User create(User user) throws SQLException{
        final String SQL_CREATE_USER = "INSERT INTO public.\"User\"(\n" +
                "\t\"Role_id\", \"First_SecondName\", \"Password\", \"Email\", \"Phone_number\", \"Address_id\")\n" +
                "\tVALUES (?, ?, ?, ?, ?, ?);";


        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_USER)){
            preparedStatement.setLong(1,user.getRole().getId());
            preparedStatement.setString(2,user.getFirstName_lastName());
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.setString(4,user.getEmail());
            preparedStatement.setString(5,user.getTelephone());
            preparedStatement.setLong(6,user.getAddresses().getId());
            preparedStatement.executeUpdate();
            return user;
        }

    }

    @Override
    public void update(User user) throws SQLException{
        final String SQL_UPDATE = "UPDATE public.\"User\"\n" +
                "\tSET  \"First_SecondName\"=?, \"Email\"=?, \"Phone_number\"=?, \"Address_id\"=?\n" +
                "\tWHERE \"UserID\" = ?;";


        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)){

            preparedStatement.setString(1,user.getFirstName_lastName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getTelephone());
            preparedStatement.setLong(4,user.getAddresses().getId());
            preparedStatement.setLong(5,user.getUserId());
            preparedStatement.executeUpdate();
        }
    }
    @Override
    public void updateOrder(User user) throws SQLException{
        final String SQL_UPDATE = "UPDATE public.\"User\"\n" +
                "\tSET  \"Order_id\"=?\n" +
                "\tWHERE \"UserID\"=?";


        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)){
            preparedStatement.setLong(1,user.getOrder().getId());
            preparedStatement.setLong(2,user.getUserId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<User> getOrderedUsers() throws SQLException {
        final String SQL_SELECT_ALL = "SELECT \"UserID\", \"Role_id\", \"First_SecondName\", \"Password\", \"Phone_number\", \"Address_id\", \"Order_id\", \"Email\"\n" +
                "\tFROM public.\"User\" WHERE \"Order_id\" IS NOT NULL;";

        List<User> users = new ArrayList<>();


        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()){

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
        }
        return users;
    }

    @Override
    public List<User> getAllNotAdmin() throws SQLException {
        final String SQL_SELECT_ALL = "SELECT \"UserID\", \"Role_id\", \"First_SecondName\", \"Password\", \"Phone_number\", \"Address_id\", \"Order_id\", \"Email\"\n" +
                "\tFROM public.\"User\" WHERE \"Role_id\" != 1;";

        List<User> users = new ArrayList<>();


        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

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
        }
        return users;
    }



    @Override
    public User findByEmail(String email) throws SQLException {
        User user = new User();
        final String SQL_SELECT_BY_ID = "SELECT \"UserID\", \"Role_id\", \"First_SecondName\", \"Password\", \"Email\", \"Phone_number\", \"Address_id\", \"Order_id\"\n" +
                "\tFROM public.\"User\" WHERE \"Email\" = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)){
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
        }

        return user;
    }

    @Override
    public Boolean checkUserByEmail(String email) throws SQLException {

        User user = new User();
        final String SQL_SELECT_BY_ID = "SELECT \"UserID\", \"Role_id\", \"First_SecondName\", \"Password\", \"Email\", \"Phone_number\", \"Address_id\", \"Order_id\"\n" +
                "\tFROM public.\"User\" WHERE \"Email\" = ?;";
        try (Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)){
            preparedStatement.setString(1,email);
            ResultSet resultSet= preparedStatement.executeQuery();

            if (resultSet.next() == false)
                return false;
            else
                return true;
        }

    }


    @Override
    public User checkLogin(String email, String password) throws SQLException{
        User user = new User();
        final String SQL_SELECT_BY_ID = "SELECT \"UserID\", \"Role_id\", \"First_SecondName\", \"Password\", \"Phone_number\", \"Address_id\", \"Order_id\", \"Email\"\n" +
                "\tFROM public.\"User\" WHERE \"Email\" = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)){
            preparedStatement.setString(1,email);
            ResultSet resultSet= preparedStatement.executeQuery();

            while (resultSet.next()) {
                String hashedPassword = resultSet.getString("Password");
                if (BCrypt.checkpw(password, hashedPassword)) {
                user.setUserId(resultSet.getLong("UserID"));
                user.setRole(new RoleDaoImpl().findEntityById(resultSet.getLong("Role_id")));
                user.setFirstName_lastName(resultSet.getString("First_SecondName"));
                user.setEmail(resultSet.getString("Email"));
                }
                else
                    throw new SQLException();
            }
        }
        return user;
    }
}