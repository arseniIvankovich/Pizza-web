package by.fpmibsu.Dao.DaoImpl;

import by.fpmibsu.Services.Util;
import by.fpmibsu.Dao.RoleDao;
import by.fpmibsu.Entity.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl extends Util implements RoleDao {
    Connection connection = getConnection();
    @Override
    public List<Role> findAll() {
        final String SQL_SELECT_ALL = "SELECT \"RoleID\", \"Name\"\n" +
                "\tFROM public.\"Role\";";
        List<Role> roleList = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getLong("RoleID"));
                role.setRole(resultSet.getString("Name"));

                roleList.add(role);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close(statement);
            close(connection);
        }
        return roleList;
    }

    @Override
    public Role findEntityById(Long id) {
        PreparedStatement preparedStatement = null;
        Role role = new Role();
        final String SQL_SELECT_BY_ID = "SELECT \"RoleID\", \"Name\"\n" +
                "\tFROM public.\"Role\" WHERE \"RoleID\" = ?;";
        try {

            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setLong(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()) {
                role.setId(resultSet.getLong("RoleID"));
                role.setRole(resultSet.getString("Name"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
        return role;
    }

    @Override
    public boolean delete(Role role) {
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"Role\"\n" +
                "\tWHERE \"Name\" = ?;";
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID);
            preparedStatement.setString(1,role.getRole());

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
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"Role\"\n" +
                "\tWHERE \"RoleID\" = ?;";
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
    public boolean create(Role role) {
        final String SQL_CREATE_ADDRESS = "INSERT INTO public.\"Role\"(\n" +
                "\t\"Name\")\n" +
                "\tVALUES (?);";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_CREATE_ADDRESS);
            preparedStatement.setString(1,role.getRole());

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
    public void update(Role role) {
        final String SQL_UPDATE = "UPDATE public.\"Role\"\n" +
                "\tSET \"Name\"=?\n" +
                "\tWHERE \"RoleID\" = ?;";

        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(SQL_UPDATE);

            preparedStatement.setString(1,role.getRole());
            preparedStatement.setLong(2,role.getId());

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
}
