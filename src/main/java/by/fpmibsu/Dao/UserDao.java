package by.fpmibsu.Dao;

import by.fpmibsu.Entity.User;

import java.sql.SQLException;

public interface UserDao extends BaseDao<Long, User>{
    User findByName (String string) throws SQLException;

    User findByEmail (String email) throws SQLException;

    Boolean checkUserByEmail (String email) throws SQLException;

    User checkLogin (String email, String password) throws SQLException;
}
