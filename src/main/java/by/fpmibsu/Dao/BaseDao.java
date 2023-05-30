package by.fpmibsu.Dao;


import by.fpmibsu.Entity.Entity;

public interface BaseDao<K, T extends Entity> {

    T findEntityById(K id);

    boolean delete(T t);

    boolean delete(K id);

    T create(T t);

    void update(T t);

}