package org.example;

import by.fpmibsu.Dao.DaoImpl.RoleDaoImpl;
import by.fpmibsu.Entity.Role;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Role> roles = new RoleDaoImpl().findAll();
        System.out.println(roles);
    }
}