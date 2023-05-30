package by.fpmibsu.Dao;


import by.fpmibsu.Entity.Address;

public interface AddressDao extends BaseDao<Long, Address> {

    Address findByStreetHouseEntranceFlat(String street, Integer house, Integer entrance, Integer flat);

    boolean checkByStreetHouseEntranceFlat(String street, Integer house, Integer entrance, Integer flat);
}