package by.fpmibsu.Dao.DaoImpl;

import by.fpmibsu.Dao.AddressDao;
import by.fpmibsu.Dao.BaseAddressDao;
import by.fpmibsu.Dao.HikariCPDataSource;
import by.fpmibsu.Entity.BaseAddresses;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseAddressDaoImpl implements BaseAddressDao {
    private DataSource dataSource;

    public BaseAddressDaoImpl() {
        this.dataSource = HikariCPDataSource.getDataSource();
    }
    @Override
    public Boolean checkStreet(String pattern) {
        final String SQL_SELECT_STREET = "SELECT \"AddressId\", \"Street\"\n" +
                "\tFROM public.\"Addresses\" WHERE \"Street\" = ?;";
       try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_STREET)) {
           preparedStatement.setString(1, pattern);
           ResultSet resultSet = preparedStatement.executeQuery();
           return resultSet.next();
       }catch (SQLException e) {
           e.printStackTrace();
       }
       return false;
    }

    @Override
    public BaseAddresses findEntityById(Long id) {
        return null;
    }

    @Override
    public boolean delete(BaseAddresses baseAddresses) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public BaseAddresses create(BaseAddresses baseAddresses) {
        return null;
    }

    @Override
    public void update(BaseAddresses baseAddresses) {

    }
}
