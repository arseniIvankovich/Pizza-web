package by.fpmibsu.Dao.DaoImpl;

import by.fpmibsu.Dao.AddressDao;
import by.fpmibsu.Dao.HikariCPDataSource;
import by.fpmibsu.Entity.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDaoImpl implements AddressDao {
    private DataSource dataSource;

    public AddressDaoImpl() {
        this.dataSource = HikariCPDataSource.getDataSource();
    }
    static final Logger addressDaoLogger = LogManager.getLogger(AddressDaoImpl.class);
    static final Logger rootLogger = LogManager.getRootLogger();
    @Override
    public Address findByStreetHouseEntranceFlat(String street, Integer house, Integer entrance, Integer flat) {
        Address address = new Address();
        final String SQL_SELECT_BY_STREET_HOUSE_ENTRANCE_FLAT = "SELECT \"AddressID\", \"StreetName\", \"HouseNumber\", \"Entrance\", \"FlatNumber\"\n" + "\tFROM public.\"Address\" WHERE \"StreetName\" = ? AND \"HouseNumber\" = ? \n" + "\tAND \"Entrance\" = ? AND \"FlatNumber\" = ? LIMIT 1;";
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_STREET_HOUSE_ENTRANCE_FLAT)) {
            addressDaoLogger.info("Got connection to the db");
            preparedStatement.setString(1, street);
            preparedStatement.setInt(2, house);
            preparedStatement.setInt(3, entrance);
            preparedStatement.setInt(4, flat);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                address.setAddressID(resultSet.getLong("AddressID"));
                address.setStreet(resultSet.getString("StreetName"));
                address.setEntrance(resultSet.getInt("Entrance"));
                address.setHouseNumber(resultSet.getInt("HouseNumber"));
                address.setFlatNumber(resultSet.getInt("FlatNumber"));
            }
        } catch (SQLException e) {
            rootLogger.error("Error: ", e);
        }

        return address;
    }

    @Override
    public boolean checkByStreetHouseEntranceFlat(String street, Integer house, Integer entrance, Integer flat) {

        Address address = new Address();
        final String SQL_SELECT_BY_STREET_HOUSE_ENTRANCE_FLAT = "SELECT \"AddressID\", \"StreetName\", \"HouseNumber\", \"Entrance\", \"FlatNumber\"\n" + "\tFROM public.\"Address\" WHERE \"StreetName\" = ? AND \"HouseNumber\" = ? \n" + "\tAND \"Entrance\" = ? AND \"FlatNumber\" = ?;";
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_STREET_HOUSE_ENTRANCE_FLAT)) {
            addressDaoLogger.info("Got connection to the db");
            preparedStatement.setString(1, street);
            preparedStatement.setInt(2, house);
            preparedStatement.setInt(3, entrance);
            preparedStatement.setInt(4, flat);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            rootLogger.error("Error: ", e);
        }
        return false;
    }


    @Override
    public Address findEntityById(Long id) {
        final String SQL_SELECT_BY_ID = "SELECT  \"AddressID\", \"StreetName\", \"HouseNumber\", \"Entrance\", \"FlatNumber\"\n" + "\tFROM public.\"Address\" WHERE \"AddressID\" = ?;";

        Address address = new Address();
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            addressDaoLogger.info("Got connection to the db");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                address.setId(resultSet.getLong("AddressID"));
                address.setStreet(resultSet.getString("StreetName"));
                address.setEntrance(resultSet.getInt("Entrance"));
                address.setHouseNumber(resultSet.getInt("HouseNumber"));
                address.setFlatNumber(resultSet.getInt("FlatNumber"));
            }
        } catch (SQLException e) {
            rootLogger.error("Error: ", e);        }

        return address;
    }

    @Override
    public boolean delete(Address address) {
        final String SQL_DELETE_BY_STREET = "DELETE FROM public.\"Address\"\n" + "\tWHERE \"StreetName\" = ?;";
        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_STREET)) {
            addressDaoLogger.info("Got connection to the db");
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            rootLogger.error("Error: ", e);
        }
        return false;
    }


    @Override
    public boolean delete(Long id) {
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"Address\"\n" + "\tWHERE \"AddressID\" = ?;";

        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            addressDaoLogger.info("Got connection to the db");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            rootLogger.error("Error: ", e);
        }
        return false;
    }

    @Override
    public Address create(Address address) {
        final String SQL_CREATE_ADDRESS = "INSERT INTO public.\"Address\"(\n" + "\t\"StreetName\", \"HouseNumber\", \"Entrance\", \"FlatNumber\")\n" + "\tVALUES (?, ?, ?, ?);";

        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_ADDRESS)) {
            addressDaoLogger.info("Got connection to the db");
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setInt(2, address.getHouseNumber());
            preparedStatement.setInt(3, address.getEntrance());
            preparedStatement.setInt(4, address.getFlatNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            rootLogger.error("Error: ", e);
        }
        return address;
    }

    @Override
    public void update(Address address) {
        final String SQL_UPDATE = "UPDATE public.\"Address\"\n" + "\tSET  \"StreetName\"=?, \"HouseNumber\"=?, \"Entrance\"=?, \"FlatNumber\"=?\n" + "\tWHERE \"AddressID\" = ?;";


        try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {
            addressDaoLogger.info("Got connection to the db");
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setInt(2, address.getEntrance());
            preparedStatement.setInt(3, address.getFlatNumber());
            preparedStatement.setInt(4, address.getHouseNumber());
            preparedStatement.setLong(5, address.getAddressID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            rootLogger.error("Error: ", e);
        }

    }
}