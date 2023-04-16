package by.fpmibsu.Dao.DaoImpl;

import by.fpmibsu.Services.Util;
import by.fpmibsu.Dao.VacancyDao;
import by.fpmibsu.Entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class VacancyDaoImpl extends Util implements VacancyDao {
    Connection connection = getConnection();
    @Override
    public List<Vacancy> findAll() throws SQLException{
        final String SQL_SELECT_ALL = "SELECT \"VacancyID\", \"Salary\", \"Trial\", \"Name\"\n" +
                "\tFROM public.\"Vacancy\";";
        final String SQL_INNER = "SELECT \"UserID\"\n" +
                "\tFROM public.\"User_Vacancy\" WHERE \"VacancyID\" = ?;";
        List<Vacancy> vacancies = new ArrayList<>();


        Statement statement = null;
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

            while (resultSet.next()) {
                Vacancy vacancy = new Vacancy();
                vacancy.setVacancyID(resultSet.getLong("VacancyID"));
                vacancy.setSalary(resultSet.getDouble("Salary"));
                vacancy.setTrial(resultSet.getInt("Trial"));
                vacancy.setName(resultSet.getString("Name"));
                Statement statement1 = null;
                try {
                    statement1 = connection.createStatement();

                    ResultSet resultSet1 = statement1.executeQuery(SQL_INNER);
                    HashSet<User> users = new HashSet<>();
                    while (resultSet1.next()) {
                        users.add(new UserDaoImpl().findEntityById(resultSet1.getLong("UserID")));
                    }
                    vacancy.setUser(users);
                }
                finally {
                    close(statement1);
                }
                vacancies.add(vacancy);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close(statement);
            close(connection);
        }
        return vacancies;
    }

    @Override
    public Vacancy findEntityById(Long id) throws SQLException{
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        Vacancy vacancy = new Vacancy();
        final String SQL_SELECT_BY_ID = "SELECT \"VacancyID\", \"Salary\", \"Trial\", \"Name\"\n" +
                "\tFROM public.\"Vacancy\" WHERE = ?;";

        final String SQL_INNER = "SELECT \"UserID\"\n" +
                "\tFROM public.\"User_Vacancy\" WHERE \"VacancyID\" = ?;";
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setLong(1, id);
            preparedStatement1 = connection.prepareStatement(SQL_INNER);
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
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
        return vacancy;
    }

    @Override
    public boolean delete(Vacancy vacancy) throws SQLException{
        final String SQL_DELETE_BY_NAME = "DELETE FROM public.\"Vacancy\"\n" +
                "\tWHERE \"Name\" = ?;";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_BY_NAME);
            preparedStatement.setString(1,vacancy.getName());

            preparedStatement.executeUpdate();
            return true;
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public boolean delete(Long id) throws SQLException{
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"Vacancy\"\n" +
                "\tWHERE \"VacancyID\" = ?;";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            return true;
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public boolean create(Vacancy vacancy) throws SQLException{

        final String SQL_CREATE_ADDRESS = "INSERT INTO public.\"Vacancy\"(\n" +
                "\t\"Salary\", \"Trial\", \"Name\")\n" +
                "\tVALUES (?, ?, ?);";

        final String SQL_INNER = "INSERT INTO public.\"User_Vacancy\"(\n" +
                "\t\"VacancyID\", \"UserID\")\n" +
                "\tVALUES (?, ?);";

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_CREATE_ADDRESS);

            preparedStatement.setDouble(1,vacancy.getSalary());
            preparedStatement.setInt(2,vacancy.getTrial());
            preparedStatement.setString(3,vacancy.getName());

            preparedStatement.executeUpdate();

            return true;
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public void update(Vacancy vacancy) throws SQLException {

        final String SQL_UPDATE = "UPDATE public.\"Vacancy\"\n" +
                "\tSET \"Salary\"=?, \"Trial\"=?, \"Name\"=?\n" +
                "\tWHERE \"VacancyID\" = ?;";

        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(SQL_UPDATE);

            preparedStatement.setDouble(1,vacancy.getSalary());
            preparedStatement.setInt(2,vacancy.getTrial());
            preparedStatement.setString(3,vacancy.getName());
            preparedStatement.setLong(4,vacancy.getVacancyID());

            preparedStatement.executeUpdate();
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public Vacancy findByName(String pattern) throws SQLException {
        PreparedStatement preparedStatement = null;
        Vacancy vacancy = new Vacancy();
        final String SQL_SELECT_BY_NAME_TYPE_SIZE = "SELECT \"VacancyID\", \"Salary\", \"Trial\", \"Name\"\n" +
                "\tFROM public.\"Vacancy\" WHERE \"Name\" = ?;";

        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_NAME_TYPE_SIZE);
            preparedStatement.setString(1,pattern);
            ResultSet resultSet= preparedStatement.executeQuery();

            while (resultSet.next()) {
                vacancy.setVacancyID(resultSet.getLong("VacancyID"));
                vacancy.setSalary(resultSet.getDouble("Salary"));
                vacancy.setTrial(resultSet.getInt("Trial"));
                vacancy.setName(resultSet.getString("Name"));
            }
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
        return vacancy;
    }

    @Override
    public void addToMMUserVacancy(Long userId, Long vacancyId) throws SQLException {
        final String SQL_LAST_ID = "INSERT INTO public.\"User_Vacancy\"(\n" +
                "\t\"VacancyID\", \"UserID\")\n" +
                "\tVALUES (?, ?);";
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(SQL_LAST_ID);
            preparedStatement.setLong(1,vacancyId);
            preparedStatement.setLong(2,userId);

            preparedStatement.executeUpdate();

        }
        finally {
            close(preparedStatement);
            close(connection);
        }
    }

    private Long getLastID () throws SQLException{

        final String SQL_LAST_ID = "SELECT \"UserID\"\n" +
                "\tFROM public.\"User\" ORDER BY \"UserID\" DESC LIMIT 1;";
        PreparedStatement preparedStatement = null;
        Long index = 0L;
        try{
            preparedStatement = connection.prepareStatement(SQL_LAST_ID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                index = resultSet.getLong("OrderID");
            }
        }
        finally {
            close(preparedStatement);
        }
        return index;
    }


}
