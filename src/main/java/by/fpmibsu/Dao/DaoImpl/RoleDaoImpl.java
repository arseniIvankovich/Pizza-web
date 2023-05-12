package by.fpmibsu.Dao.DaoImpl;

import by.fpmibsu.Dao.HikariCPDataSource;
import by.fpmibsu.Services.Util;
import by.fpmibsu.Dao.RoleDao;
import by.fpmibsu.Entity.Role;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl extends Util implements RoleDao {
    private final DataSource dataSource;
    public RoleDaoImpl () {
        this.dataSource = HikariCPDataSource.getDataSource();
    }

    @Override
    public Role findEntityById(Long id) throws SQLException{
        Role role = new Role();
        final String SQL_SELECT_BY_ID = "SELECT \"RoleID\", \"Name\"\n" +
                "\tFROM public.\"Role\" WHERE \"RoleID\" = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()) {
                role.setId(resultSet.getLong("RoleID"));
                role.setRole(resultSet.getString("Name"));
            }
        }
        return role;
    }

    @Override
    public boolean delete(Role role) throws SQLException{
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"Role\"\n" +
                "\tWHERE \"Name\" = ?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)){
            preparedStatement.setString(1,role.getRole());

            preparedStatement.executeUpdate();
            return true;
        }
    }

    @Override
    public boolean delete(Long id) throws SQLException{

        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"Role\"\n" +
                "\tWHERE \"RoleID\" = ?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)){
            preparedStatement.setLong(1,id);

            preparedStatement.executeUpdate();
            return true;
        }
    }

    @Override
    public Role create(Role role) throws SQLException{

        final String SQL_CREATE_ADDRESS = "INSERT INTO public.\"Role\"(\n" +
                "\t\"Name\")\n" +
                "\tVALUES (?);";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_ADDRESS)){
            preparedStatement.setString(1,role.getRole());

            preparedStatement.executeUpdate();
            return role;
        }
    }

    @Override
    public void update(Role role) throws SQLException{

        final String SQL_UPDATE = "UPDATE public.\"Role\"\n" +
                "\tSET \"Name\"=?\n" +
                "\tWHERE \"RoleID\" = ?;";


        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)){
            preparedStatement.setString(1,role.getRole());
            preparedStatement.setLong(2,role.getId());

            preparedStatement.executeUpdate();
        }

    }
}
