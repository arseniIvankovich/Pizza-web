package by.fpmibsu.Dao.DaoImpl;

import by.fpmibsu.Dao.HikariCPDataSource;
import by.fpmibsu.Dao.RoleDao;
import by.fpmibsu.Entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;

public class RoleDaoImpl  implements RoleDao {
    private final DataSource dataSource;
    public RoleDaoImpl () {
        this.dataSource = HikariCPDataSource.getDataSource();
    }
    static final Logger roleDaoLogger = LogManager.getLogger(RoleDaoImpl.class);
    static final Logger rootLogger = LogManager.getRootLogger();
    @Override
    public Role findEntityById(Long id) {
        Role role = new Role();
        final String SQL_SELECT_BY_ID = "SELECT \"RoleID\", \"Name\"\n" +
                "\tFROM public.\"Role\" WHERE \"RoleID\" = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)){
            roleDaoLogger.info("Got connection to the db");
            preparedStatement.setLong(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()) {
                role.setId(resultSet.getLong("RoleID"));
                role.setRole(resultSet.getString("Name"));
            }
        } catch (SQLException e) {
            rootLogger.error("Error: ",e);
        }

        return role;
    }

    @Override
    public boolean delete(Role role) {
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"Role\"\n" +
                "\tWHERE \"Name\" = ?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)){
            roleDaoLogger.info("Got connection to the db");
            preparedStatement.setString(1,role.getRole());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            rootLogger.error("Error: ",e);
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {

        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"Role\"\n" +
                "\tWHERE \"RoleID\" = ?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)){
            roleDaoLogger.info("Got connection to the db");
            preparedStatement.setLong(1,id);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            rootLogger.error("Error: ",e);
        }
    return false;
    }

    @Override
    public Role create(Role role) {

        final String SQL_CREATE_ADDRESS = "INSERT INTO public.\"Role\"(\n" +
                "\t\"Name\")\n" +
                "\tVALUES (?);";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_ADDRESS)){
            roleDaoLogger.info("Got connection to the db");
            preparedStatement.setString(1,role.getRole());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            rootLogger.error("Error: ",e);
        }
        return role;
    }

    @Override
    public void update(Role role) {

        final String SQL_UPDATE = "UPDATE public.\"Role\"\n" +
                "\tSET \"Name\"=?\n" +
                "\tWHERE \"RoleID\" = ?;";


        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)){
            roleDaoLogger.info("Got connection to the db");
            preparedStatement.setString(1,role.getRole());
            preparedStatement.setLong(2,role.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            rootLogger.error("Error: ",e);
        }


    }
}
