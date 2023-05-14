package org.example;

import by.fpmibsu.Entity.Order;
import by.fpmibsu.Entity.User;
import by.fpmibsu.Services.AddressService;
import by.fpmibsu.Services.BaseAddressService;
import by.fpmibsu.Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
    User user = new User();
    user.setEmail("ivars7613@gmail.com");
        UserService userService = new UserService();
        System.out.println(userService.checkEmail("ivars7613@gmail.com"));
    }
}