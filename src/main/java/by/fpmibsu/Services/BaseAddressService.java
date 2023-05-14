package by.fpmibsu.Services;

import by.fpmibsu.Dao.BaseAddressDao;
import by.fpmibsu.Dao.DaoImpl.BaseAddressDaoImpl;

public class BaseAddressService {
    private final BaseAddressDao baseAddressDao;

    public BaseAddressService() {
        this.baseAddressDao = new BaseAddressDaoImpl();
    }

    public Boolean checkValidStreet(String pattern) {
        return baseAddressDao.checkStreet(pattern);
    }
}
