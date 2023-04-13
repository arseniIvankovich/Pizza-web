package by.fpmibsu.Dao;

import by.fpmibsu.Entity.User;

public interface UserDao extends BaseDao<Long, User>{
    User findByName(String string);
}
