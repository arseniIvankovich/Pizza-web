package by.fpmibsu.Dao.DaoImpl;

import by.fpmibsu.Dao.AddressDao;
import by.fpmibsu.Dao.BaseAddressDao;
import by.fpmibsu.Dao.HikariCPDataSource;
import by.fpmibsu.Entity.BaseAddresses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    static final Logger baseAddressDaoLogger = LogManager.getLogger(BaseAddressDaoImpl.class);
    static final Logger rootLogger = LogManager.getRootLogger();
    @Override
    public Boolean checkStreet(String pattern) {
        final String SQL_SELECT_STREET = "SELECT \"AddressId\", \"Street\"\n" +
                "\tFROM public.\"Addresses\" WHERE \"Street\" = ?;";
       try (Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_STREET)) {
           baseAddressDaoLogger.info("Got connection to the db");
           preparedStatement.setString(1, pattern);
           ResultSet resultSet = preparedStatement.executeQuery();
           return resultSet.next();
       }catch (SQLException e) {
           rootLogger.error("Error: ", e);
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
