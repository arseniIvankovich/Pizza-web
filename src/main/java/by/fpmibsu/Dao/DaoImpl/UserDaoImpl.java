package by.fpmibsu.Dao.DaoImpl;


import by.fpmibsu.Services.Util;
import by.fpmibsu.Dao.UserDao;
import by.fpmibsu.Entity.*;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends Util implements UserDao {
    Connection connection = getConnection();
    @Override
    public List<User> findAll() throws SQLException{
        final String SQL_SELECT_ALL = "SELECT \"UserID\", \"Role_id\", \"First_SecondName\", \"Password\", \"Email\", \"Phone_number\", \"Address_id\", \"Order_id\"\n" +
                "\tFROM public.\"User\";";

        List<User> users = new ArrayList<>();

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

            while (resultSet.next()) {
                User user = new User();
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
        finally {
            close(statement);
            close(connection);
        }
        return users;
    }

    @Override
    public User findEntityById(Long id) throws SQLException{
        PreparedStatement preparedStatement = null;
        User user = new User();
        final String SQL_SELECT_BY_ID = "SELECT \"UserID\", \"Role_id\", \"First_SecondName\", \"Password\", \"Email\", \"Phone_number\", \"Address_id\", \"Order_id\"\n" +
                "\tFROM public.\"User\" WHERE \"UserID\" = ?;";
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
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
                user.setOrder(new OrderDaoImpl().findEntityById(resultSet.getLong("Order_id")));
            }
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
        return user;
    }

    @Override
    public boolean delete(User user) throws SQLException{
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"User\"\n" +
                "\tWHERE \"First_SecondName\" = ?;";
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID);
            preparedStatement.setString(1,user.getFirstName_lastName());

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
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"User\"\n" +
                "\tWHERE \"UserID\" = ?;";
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
    public boolean create(User user) throws SQLException{
        final String SQL_CREATE_USER = "INSERT INTO public.\"User\"(\n" +
                "\t\"Role_id\", \"First_SecondName\", \"Password\", \"Email\", \"Phone_number\", \"Address_id\")\n" +
                "\tVALUES (?, ?, ?, ?, ?, ?);";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_CREATE_USER);
            preparedStatement.setLong(1,user.getRole().getId());
            preparedStatement.setString(2,user.getFirstName_lastName());
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.setString(4,user.getEmail());
            preparedStatement.setString(5,user.getTelephone());
            preparedStatement.setLong(6,user.getAddresses().getId());

            preparedStatement.executeUpdate();
            return true;
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public void update(User user) throws SQLException{
        final String SQL_UPDATE = "UPDATE public.\"User\"\n" +
                "\tSET \"Role_id\"=?, \"First_SecondName\"=?, \"Password\"=?, \"Email\"=?, \"Phone_number\"=?, \"Address_id\"=?, \"Order_id\"=?\n" +
                "\tWHERE \"UserID\" = ?;";

        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(SQL_UPDATE);

            preparedStatement.setLong(1,user.getRole().getId());
            preparedStatement.setString(2,user.getFirstName_lastName());
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.setString(4,user.getEmail());
            preparedStatement.setString(5,user.getTelephone());
            preparedStatement.setLong(6,user.getAddresses().getId());
            preparedStatement.setLong(7,user.getOrder().getId());
            preparedStatement.setLong(8,user.getUserId());
            preparedStatement.executeUpdate();
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public User findByName(String string) throws SQLException{
        PreparedStatement preparedStatement = null;
        User user = new User();
        final String SQL_SELECT_BY_ID = "SELECT \"UserID\", \"Role_id\", \"First_SecondName\", \"Password\", \"Email\", \"Phone_number\", \"Address_id\", \"Order_id\"\n" +
                "\tFROM public.\"User\" WHERE \"First_SecondName\" = ?;";
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setString(1,string);
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
        finally {
            close(preparedStatement);
            close(connection);
        }
        return user;
    }

    @Override
    public User findByEmail(String email) throws SQLException {
        PreparedStatement preparedStatement = null;
        User user = new User();
        final String SQL_SELECT_BY_ID = "SELECT \"UserID\", \"Role_id\", \"First_SecondName\", \"Password\", \"Email\", \"Phone_number\", \"Address_id\", \"Order_id\"\n" +
                "\tFROM public.\"User\" WHERE \"Email\" = ?;";
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
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
        finally {
            close(preparedStatement);
            close(connection);
        }
        return user;
    }

    @Override
    public Boolean checkUserByEmail(String email) throws SQLException {
        PreparedStatement preparedStatement = null;
        User user = new User();
        final String SQL_SELECT_BY_ID = "SELECT \"UserID\", \"Role_id\", \"First_SecondName\", \"Password\", \"Email\", \"Phone_number\", \"Address_id\", \"Order_id\"\n" +
                "\tFROM public.\"User\" WHERE \"Email\" = ?;";
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setString(1,email);
            ResultSet resultSet= preparedStatement.executeQuery();

            if (resultSet.next() == false)
                return false;
            else
                return true;
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
    }


    @Override
    public User checkLogin(String email, String password) throws SQLException{
        PreparedStatement preparedStatement = null;
        User user = new User();
        final String SQL_SELECT_BY_ID = "SELECT \"UserID\", \"Role_id\", \"First_SecondName\", \"Password\", \"Phone_number\", \"Address_id\", \"Order_id\", \"Email\"\n" +
                "\tFROM public.\"User\" WHERE \"Email\" = ?;";
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
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
        finally {
            close(preparedStatement);
          //  close(connection);
        }
        return user;
    }
}