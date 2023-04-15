package by.fpmibsu.Dao;


import by.fpmibsu.Entity.Entity;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public interface BaseDao <K, T extends Entity>{
    List<T> findAll() throws SQLException;
    T findEntityById(K id) throws SQLException;
    boolean delete(T t) throws SQLException;
    boolean delete(K id) throws SQLException;
    boolean create(T t) throws SQLException;
    void update(T t) throws SQLException;

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