package by.fpmibsu.Services;

import by.fpmibsu.Dao.AddressDao;
import by.fpmibsu.Dao.DaoImpl.AddressDaoImpl;
import by.fpmibsu.Entity.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class AddressService {
    private AddressDao addressDao;


    public AddressService() {
        this.addressDao = new AddressDaoImpl();
    }

    public Address findByStreetHouseEntranceFlat(String street, Integer house, Integer entrance, Integer flat)  {
        return addressDao.findByStreetHouseEntranceFlat(street,house,entrance,flat);
    }

    public Address findEntityById(Long id) {
        return addressDao.findEntityById(id);
    }

    public Address create (Address address) {
        return addressDao.create(address);
    }

}
