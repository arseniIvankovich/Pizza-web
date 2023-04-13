package by.fpmibsu.Dao;


import by.fpmibsu.Entity.Entity;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public interface BaseDao <K, T extends Entity>{
    List<T> findAll();
    T findEntityById(K id);
    boolean delete(T t);
    boolean delete(K id);
    boolean create(T t);
    void update(T t);

    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    default void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}