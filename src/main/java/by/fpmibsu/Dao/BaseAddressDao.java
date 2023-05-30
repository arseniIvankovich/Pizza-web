package by.fpmibsu.Dao;


import by.fpmibsu.Entity.BaseAddresses;

public interface BaseAddressDao extends BaseDao<Long, BaseAddresses> {
    Boolean checkStreet(String pattern);
}
