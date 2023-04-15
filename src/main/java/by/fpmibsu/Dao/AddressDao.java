package by.fpmibsu.Dao;



import by.fpmibsu.Entity.Address;

import java.sql.SQLException;
import java.util.List;

public interface AddressDao extends BaseDao<Long, Address> {
    List<Address> findAllByStreet(String pattern) throws SQLException;
}
