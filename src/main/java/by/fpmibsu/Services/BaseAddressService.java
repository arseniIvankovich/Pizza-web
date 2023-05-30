package by.fpmibsu.Services;

import by.fpmibsu.Dao.BaseAddressDao;
import by.fpmibsu.Dao.DaoImpl.BaseAddressDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseAddressService {
    private final BaseAddressDao baseAddressDao;

    public BaseAddressService() {
        this.baseAddressDao = new BaseAddressDaoImpl();
    }

    static final Logger baseAddressServiceLogger = LogManager.getLogger(BaseAddressService.class);

    public Boolean checkValidStreet(String pattern) {
        baseAddressServiceLogger.debug("Check valid street is");
        return baseAddressDao.checkStreet(pattern);
    }
}
