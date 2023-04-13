package by.fpmibsu.Dao.DaoImpl;

import by.fpmibsu.Dao.AddressDao;
import by.fpmibsu.Entity.Address;
import by.fpmibsu.Services.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl extends Util implements AddressDao {
    Connection connection = getConnection();
    public List<Address> findAllByStreet(String pattern) {
        final String SQL_SELECT_BY_STREET = "SELECT \"AddressID\", \"StreetName\", \"Entrance\", \"HouseNumber\", \"FlatNumber\" FROM public.\"Address\"\n" +
                "                WHERE \"StreetName\" = ?;";
        List<Address> addressList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_STREET);
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
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
        return addressList;
    }

    @Override
    public List<Address> findAll()  {
        final String SQL_SELECT_ALL = "SELECT  \"AddressID\", \"StreetName\", \"HouseNumber\", \"Entrance\", \"FlatNumber\"\n" +
                "\tFROM public.\"Address\";";
        List<Address> addressList = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

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
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close(statement);
            close(connection);
        }
        return addressList;
    }

    @Override
    public Address findEntityById(Long id)  {
        final String SQL_SELECT_BY_ID = "SELECT  \"AddressID\", \"StreetName\", \"HouseNumber\", \"Entrance\", \"FlatNumber\"\n" +
                "\tFROM public.\"Address\" WHERE \"AddressID\" = ?;";

        PreparedStatement preparedStatement = null;
        Address address = new Address();
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
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
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
        return address;
    }

    @Override
    public boolean delete(Address address) {
        final String SQL_DELETE_BY_STREET = "DELETE FROM public.\"Address\"\n" +
                "\tWHERE \"StreetName\" = ?;";
        PreparedStatement preparedStatement = null;

        try{
            preparedStatement = connection.prepareStatement(SQL_DELETE_BY_STREET);
            preparedStatement.setString(1,address.getStreet());

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
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"Address\"\n" +
                "\tWHERE \"AddressID\" = ?;";

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
    public boolean create(Address address) {
        final String SQL_CREATE_ADDRESS = "INSERT INTO public.\"Address\"(\n" +
                "\t\"StreetName\", \"HouseNumber\", \"Entrance\", \"FlatNumber\")\n" +
                "\tVALUES (?, ?, ?, ?);";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_CREATE_ADDRESS);
            preparedStatement.setString(1,address.getStreet());
            preparedStatement.setInt(2,address.getHouseNumber());
            preparedStatement.setInt(3,address.getEntrance());
            preparedStatement.setInt(4,address.getFlatNumber());

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
    public void update(Address address) {
        final String SQL_UPDATE = "UPDATE public.\"Address\"\n" +
                "\tSET  \"StreetName\"=?, \"HouseNumber\"=?, \"Entrance\"=?, \"FlatNumber\"=?\n" +
                "\tWHERE \"AddressID\" = ?;";

        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(SQL_UPDATE);

            preparedStatement.setString(1,address.getStreet());
            preparedStatement.setInt(2,address.getEntrance());
            preparedStatement.setInt(3,address.getFlatNumber());
            preparedStatement.setInt(4,address.getHouseNumber());
            preparedStatement.setLong(5,address.getAddressID());

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