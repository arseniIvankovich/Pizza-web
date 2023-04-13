package by.fpmibsu.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    static final String DB_URL = "jdbc:postgresql://localhost:5432/Pizza-web";
    static final String USER = "Teamlead";
    static final String PASS = "password";
    static final  String DB_DRIVER = "org.postgresql.Driver";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (ClassNotFoundException  |SQLException e) {
            e.printStackTrace();
        }
        return  connection;
    }
}

