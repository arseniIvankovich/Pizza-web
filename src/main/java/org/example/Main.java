package org.example;

import by.fpmibsu.Entity.Address;
import by.fpmibsu.Entity.Order;
import by.fpmibsu.Entity.User;
import by.fpmibsu.Services.AddressService;
import by.fpmibsu.Services.BaseAddressService;
import by.fpmibsu.Services.UserService;
import com.beust.ah.A;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.testng.Assert;

import java.sql.SQLException;

public class Main {

    private static final Logger logger = LogManager.getRootLogger();
    public static void main(String[] args) throws SQLException {
        AddressService addressService = new AddressService();
        Address address = addressService.findByStreetHouseEntranceFlat("Ленина",1,1,1);
        Assert.assertEquals(address,new Address("Ленина",1,1,1));
        Assert.assertEquals(address.getAddressID(),40L);
    }
}