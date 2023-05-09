package org.example;

import by.fpmibsu.Dao.DaoImpl.AddressDaoImpl;
import by.fpmibsu.Dao.DaoImpl.OrderDaoImpl;
import by.fpmibsu.Dao.DaoImpl.RoleDaoImpl;
import by.fpmibsu.Dao.DaoImpl.UserDaoImpl;
import by.fpmibsu.Entity.Address;
import by.fpmibsu.Entity.Role;
import by.fpmibsu.Entity.User;
import by.fpmibsu.Services.AddressService;
import by.fpmibsu.Services.RoleSetvice;
import by.fpmibsu.Services.UserService;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        String s = BCrypt.hashpw("password",BCrypt.gensalt());
        System.out.println(s);
    }
}