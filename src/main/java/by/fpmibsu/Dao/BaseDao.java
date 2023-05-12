package by.fpmibsu.Dao;


import by.fpmibsu.Entity.Entity;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public interface BaseDao <K, T extends Entity>{
    T findEntityById(K id);
    boolean delete(T t);
    boolean delete(K id);
    T create(T t);
    void update(T t);

}