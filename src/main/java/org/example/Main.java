package org.example;

import by.fpmibsu.Entity.Address;
import by.fpmibsu.Entity.Order;
import by.fpmibsu.Entity.Role;
import by.fpmibsu.Entity.User;
import by.fpmibsu.Services.AddressService;
import by.fpmibsu.Services.BaseAddressService;
import by.fpmibsu.Services.RoleService;
import by.fpmibsu.Services.UserService;
import com.beust.ah.A;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.testng.Assert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final Logger logger = LogManager.getRootLogger();
    public static void main(String[] args) throws SQLException, IOException {
        UserService userService = new UserService();
        List<User> users = (ArrayList<User>) userService.getUndeliveredOrdersForUsers();
        StringWriter sw =new StringWriter();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(sw, users);
        System.out.println(sw.toString());
    }
}