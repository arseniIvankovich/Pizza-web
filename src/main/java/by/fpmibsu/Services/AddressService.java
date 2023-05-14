package by.fpmibsu.Services;

import by.fpmibsu.Dao.AddressDao;
import by.fpmibsu.Dao.DaoImpl.AddressDaoImpl;
import by.fpmibsu.Entity.Address;

import java.sql.SQLException;
import java.util.List;

public class AddressService {
    private AddressDao addressDao;

    public AddressService() {
        this.addressDao = new AddressDaoImpl();
    }

    public Address findByStreetHouseEntranceFlat(String street, String house, Integer entrance, Integer flat)  {
        return addressDao.findByStreetHouseEntranceFlat(street,house,entrance,flat);
    }

    public Address findEntityById(Long id) {
        return addressDao.findEntityById(id);
    }

    public Address create (Address address) {
        return addressDao.create(address);
    }

}
