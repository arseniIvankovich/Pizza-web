package by.fpmibsu.Services;

import by.fpmibsu.Dao.AddressDao;
import by.fpmibsu.Dao.DaoImpl.AddressDaoImpl;
import by.fpmibsu.Entity.Address;
import by.fpmibsu.Servlet.AdminServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class AddressService {
    private AddressDao addressDao;
    static final Logger addressServiceLogger = LogManager.getLogger(AddressService.class);


    public AddressService() {
        this.addressDao = new AddressDaoImpl();
    }

    public Address findByStreetHouseEntranceFlat(String street, Integer house, Integer entrance, Integer flat)  {
        addressServiceLogger.debug("Get Address by street, house, entrance, flat");
        return addressDao.findByStreetHouseEntranceFlat(street,house,entrance,flat);
    }

    public Address create (Address address) {
        addressServiceLogger.debug("Create Address object");
        return addressDao.create(address);
    }

}
