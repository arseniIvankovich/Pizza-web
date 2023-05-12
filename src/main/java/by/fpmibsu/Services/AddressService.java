package by.fpmibsu.Services;

import by.fpmibsu.Dao.AddressDao;
import by.fpmibsu.Dao.DaoImpl.AddressDaoImpl;
import by.fpmibsu.Dao.DaoImpl.UserDaoImpl;
import by.fpmibsu.Dao.UserDao;
import by.fpmibsu.Entity.Address;

import java.sql.SQLException;
import java.util.List;

public class AddressService {
    private AddressDao addressDao;

    public AddressService() {
        this.addressDao = new AddressDaoImpl();
    }

    public List<Address> findAllByStreet(String pattern) throws SQLException {
        return  addressDao.findAllByStreet(pattern);
    }

    public Address findByStreetHouseEntranceFlat(String street, Integer house, Integer entrance, Integer flat) throws SQLException {
        return addressDao.findByStreetHouseEntranceFlat(street,house,entrance,flat);
    }

    public List<Address> findAll() throws SQLException {
        return addressDao.findAll();
    }

    public Address findEntityById(Long id)  throws SQLException {
        return addressDao.findEntityById(id);
    }


}
