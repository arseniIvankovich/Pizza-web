package by.fpmibsu.Dao;


import by.fpmibsu.Entity.Entity;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public interface BaseDao <K, T extends Entity>{
    T findEntityById(K id) throws SQLException;
    boolean delete(T t) throws SQLException;
    boolean delete(K id) throws SQLException;
    T create(T t) throws SQLException;
    void update(T t) throws SQLException;

}