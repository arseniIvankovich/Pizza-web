package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.AddressDaoImpl;
import by.fpmibsu.Entity.Address;

import java.sql.SQLException;
import java.util.List;

public class AddressService {
    final AddressDaoImpl addressDao;

    public AddressService(AddressDaoImpl addressDao) {
        this.addressDao = addressDao;
    }

    public List<Address> findAllByStreet(String pattern) throws SQLException {
        return addressDao.findAllByStreet(pattern);
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
