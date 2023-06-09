package by.fpmibsu.Dao.DaoImpl;

import by.fpmibsu.Dao.HikariCPDataSource;
import by.fpmibsu.Dao.VacancyDao;
import by.fpmibsu.Entity.User;
import by.fpmibsu.Entity.Vacancy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

public class VacancyDaoImpl implements VacancyDao {
    private final DataSource dataSource;

    public VacancyDaoImpl() {
        this.dataSource = HikariCPDataSource.getDataSource();
    }

    static final Logger vacancyDaoLogger = LogManager.getLogger(VacancyDaoImpl.class);
    static final Logger rootLogger = LogManager.getRootLogger();

    @Override
    public Vacancy findEntityById(Long id) {
        Vacancy vacancy = new Vacancy();
        final String SQL_SELECT_BY_ID = "SELECT \"VacancyID\", \"Salary\", \"Trial\", \"Name\"\n" +
                "\tFROM public.\"Vacancy\" WHERE = ?;";

        final String SQL_INNER = "SELECT \"UserID\"\n" +
                "\tFROM public.\"User_Vacancy\" WHERE \"VacancyID\" = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
             PreparedStatement preparedStatement1 = connection.prepareStatement(SQL_INNER)) {
            vacancyDaoLogger.info("Got Connection to the db");
            preparedStatement.setLong(1, id);
            preparedStatement1.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSet resultSet1 = preparedStatement1.executeQuery();

            while (resultSet.next()) {
                vacancy.setId(resultSet.getLong("VacancyID"));
                vacancy.setSalary(resultSet.getDouble("Salary"));
                vacancy.setTrial(resultSet.getInt("Trial"));
                vacancy.setName(resultSet.getString("Name"));

                HashSet<User> users = new HashSet<>();

                while (resultSet1.next())
                    users.add(new UserDaoImpl().findEntityById(resultSet1.getLong("UserID")));

                vacancy.setUser(users);
            }
        } catch (SQLException e) {
            rootLogger.error("Error: ", e);
        }

        return vacancy;
    }

    @Override
    public boolean delete(Vacancy vacancy) {
        final String SQL_DELETE_BY_NAME = "DELETE FROM public.\"Vacancy\"\n" +
                "\tWHERE \"Name\" = ?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_NAME)) {
            vacancyDaoLogger.info("Got Connection to the db");
            preparedStatement.setString(1, vacancy.getName());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            rootLogger.error("Error: ", e);
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"Vacancy\"\n" +
                "\tWHERE \"VacancyID\" = ?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            vacancyDaoLogger.info("Got Connection to the db");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            rootLogger.error("Error: ", e);
        }
        return false;
    }

    @Override
    public Vacancy create(Vacancy vacancy) {

        final String SQL_CREATE_ADDRESS = "INSERT INTO public.\"Vacancy\"(\n" +
                "\t\"Salary\", \"Trial\", \"Name\")\n" +
                "\tVALUES (?, ?, ?);";

        final String SQL_INNER = "INSERT INTO public.\"User_Vacancy\"(\n" +
                "\t\"VacancyID\", \"UserID\")\n" +
                "\tVALUES (?, ?);";


        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_ADDRESS)) {
            vacancyDaoLogger.info("Got Connection to the db");
            preparedStatement.setDouble(1, vacancy.getSalary());
            preparedStatement.setInt(2, vacancy.getTrial());
            preparedStatement.setString(3, vacancy.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            rootLogger.error("Error: ", e);
        }

        return vacancy;
    }

    @Override
    public void update(Vacancy vacancy) {

        final String SQL_UPDATE = "UPDATE public.\"Vacancy\"\n" +
                "\tSET \"Salary\"=?, \"Trial\"=?, \"Name\"=?\n" +
                "\tWHERE \"VacancyID\" = ?;";


        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            vacancyDaoLogger.info("Got Connection to the db");
            preparedStatement.setDouble(1, vacancy.getSalary());
            preparedStatement.setInt(2, vacancy.getTrial());
            preparedStatement.setString(3, vacancy.getName());
            preparedStatement.setLong(4, vacancy.getVacancyID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            rootLogger.error("Error: ", e);
        }


    }

    @Override
    public Vacancy findByName(String pattern) {

        Vacancy vacancy = new Vacancy();
        final String SQL_SELECT_BY_NAME_TYPE_SIZE = "SELECT \"VacancyID\", \"Salary\", \"Trial\", \"Name\"\n" +
                "\tFROM public.\"Vacancy\" WHERE \"Name\" = ?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_NAME_TYPE_SIZE)) {
            vacancyDaoLogger.info("Got Connection to the db");
            preparedStatement.setString(1, pattern);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                vacancy.setVacancyID(resultSet.getLong("VacancyID"));
                vacancy.setSalary(resultSet.getDouble("Salary"));
                vacancy.setTrial(resultSet.getInt("Trial"));
                vacancy.setName(resultSet.getString("Name"));
            }
        } catch (SQLException e) {
            rootLogger.error("Error: ", e);
        }


        return vacancy;
    }

    @Override
    public boolean addToUserVacancy(Long userId, Long vacancyId) {
        final String SQL_LAST_ID = "INSERT INTO public.\"User_Vacancy\"(\n" +
                "\t\"VacancyID\", \"UserID\")\n" +
                "\tVALUES (?, ?);";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_LAST_ID)) {
            vacancyDaoLogger.info("Got Connection to the db");
            preparedStatement.setLong(1, vacancyId);
            preparedStatement.setLong(2, userId);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            rootLogger.error("Error: ", e);
        }

        return false;
    }
}
