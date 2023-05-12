package by.fpmibsu.Dao.DaoImpl;

import by.fpmibsu.Dao.AddressDao;
import by.fpmibsu.Dao.HikariCPDataSource;
import by.fpmibsu.Entity.Address;
import by.fpmibsu.Entity.Drink;
import by.fpmibsu.Services.Util;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl extends Util implements AddressDao  {
    private DataSource dataSource;
    public AddressDaoImpl () {
        this.dataSource = HikariCPDataSource.getDataSource();
    }
    public List<Address> findAllByStreet(String pattern) throws SQLException{
        final String SQL_SELECT_BY_STREET = "SELECT \"AddressID\", \"StreetName\", \"Entrance\", \"HouseNumber\", \"FlatNumber\" FROM public.\"Address\"\n" +
                "                WHERE \"StreetName\" = ?;";
        List<Address> addressList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_STREET)){
            preparedStatement.setString(1,pattern);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Address address = new Address();
                address.setAddressID(resultSet.getLong("AddressID"));
                address.setStreet(resultSet.getString("StreetName"));
                address.setEntrance(resultSet.getInt("Entrance"));
                address.setHouseNumber(resultSet.getInt("HouseNumber"));
                address.setFlatNumber(resultSet.getInt("FlatNumber"));

                addressList.add(address);
            }
        }
        return addressList;
    }

    @Override
    public Address findByStreetHouseEntranceFlat(String street, Integer house, Integer entrance, Integer flat) throws SQLException {
        Address address = new Address();
        final String SQL_SELECT_BY_STREET_HOUSE_ENTRANCE_FLAT = "SELECT \"AddressID\", \"StreetName\", \"HouseNumber\", \"Entrance\", \"FlatNumber\"\n" +
                "\tFROM public.\"Address\" WHERE \"StreetName\" = ? AND \"HouseNumber\" = ? \n" +
                "\tAND \"Entrance\" = ? AND \"FlatNumber\" = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_STREET_HOUSE_ENTRANCE_FLAT)){
            preparedStatement.setString(1,street);
            preparedStatement.setInt(2,house);
            preparedStatement.setInt(3,entrance);
            preparedStatement.setInt(4,flat);
            ResultSet resultSet= preparedStatement.executeQuery();

            while (resultSet.next()) {
                address.setAddressID(resultSet.getLong("AddressID"));
                address.setStreet(resultSet.getString("StreetName"));
                address.setEntrance(resultSet.getInt("Entrance"));
                address.setHouseNumber(resultSet.getInt("HouseNumber"));
                address.setFlatNumber(resultSet.getInt("FlatNumber"));
            }
        }
        return address;
    }

    @Override
    public boolean checkByStreetHouseEntranceFlat(String street, Integer house, Integer entrance, Integer flat) throws SQLException {

        Address address = new Address();
        final String SQL_SELECT_BY_STREET_HOUSE_ENTRANCE_FLAT = "SELECT \"AddressID\", \"StreetName\", \"HouseNumber\", \"Entrance\", \"FlatNumber\"\n" +
                "\tFROM public.\"Address\" WHERE \"StreetName\" = ? AND \"HouseNumber\" = ? \n" +
                "\tAND \"Entrance\" = ? AND \"FlatNumber\" = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_STREET_HOUSE_ENTRANCE_FLAT)) {
            preparedStatement.setString(1,street);
            preparedStatement.setInt(2,house);
            preparedStatement.setInt(3,entrance);
            preparedStatement.setInt(4,flat);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() == false)
                return false;
            else
                return true;
        }
    }


    @Override
    public Address findEntityById(Long id)  throws SQLException{
        final String SQL_SELECT_BY_ID = "SELECT  \"AddressID\", \"StreetName\", \"HouseNumber\", \"Entrance\", \"FlatNumber\"\n" +
                "\tFROM public.\"Address\" WHERE \"AddressID\" = ?;";

        Address address = new Address();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)){
            preparedStatement.setLong(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();

            while (resultSet.next()) {
                address.setId(resultSet.getLong("AddressID"));
                address.setStreet(resultSet.getString("StreetName"));
                address.setEntrance(resultSet.getInt("Entrance"));
                address.setHouseNumber(resultSet.getInt("HouseNumber"));
                address.setFlatNumber(resultSet.getInt("FlatNumber"));
            }
        }
        return address;
    }

    @Override
    public boolean delete(Address address) throws SQLException{
        final String SQL_DELETE_BY_STREET = "DELETE FROM public.\"Address\"\n" +
                "\tWHERE \"StreetName\" = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_STREET)){

            preparedStatement.setString(1,address.getStreet());

            preparedStatement.executeUpdate();
            return true;
        }
    }


    @Override
    public boolean delete(Long id) throws SQLException{
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"Address\"\n" +
                "\tWHERE \"AddressID\" = ?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)){

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            return true;
        }
    }

    @Override
    public Address create(Address address) throws SQLException{
        final String SQL_CREATE_ADDRESS = "INSERT INTO public.\"Address\"(\n" +
                "\t\"StreetName\", \"HouseNumber\", \"Entrance\", \"FlatNumber\")\n" +
                "\tVALUES (?, ?, ?, ?);";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_ADDRESS)){
            preparedStatement.setString(1,address.getStreet());
            preparedStatement.setInt(2,address.getHouseNumber());
            preparedStatement.setInt(3,address.getEntrance());
            preparedStatement.setInt(4,address.getFlatNumber());

            preparedStatement.executeUpdate();
            return address;
        }
    }

    @Override
    public void update(Address address) throws SQLException{
        final String SQL_UPDATE = "UPDATE public.\"Address\"\n" +
                "\tSET  \"StreetName\"=?, \"HouseNumber\"=?, \"Entrance\"=?, \"FlatNumber\"=?\n" +
                "\tWHERE \"AddressID\" = ?;";


        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)){


            preparedStatement.setString(1,address.getStreet());
            preparedStatement.setInt(2,address.getEntrance());
            preparedStatement.setInt(3,address.getFlatNumber());
            preparedStatement.setInt(4,address.getHouseNumber());
            preparedStatement.setLong(5,address.getAddressID());

            preparedStatement.executeUpdate();
        }
    }
}