package org.example;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        String s = BCrypt.hashpw("password",BCrypt.gensalt());
        System.out.println(s);
    }
}