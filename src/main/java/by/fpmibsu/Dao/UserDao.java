package by.fpmibsu.Dao;

import by.fpmibsu.Entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends BaseDao<Long, User>{
    User findByName (String string) throws SQLException;

    User findByEmail (String email) throws SQLException;

    Boolean checkUserByEmail (String email) throws SQLException;

    User checkLogin (String email, String password) throws SQLException;

    void updateOrder(User user) throws SQLException;

    List<User> getOrderedUsers() throws SQLException;
    List<User> getAllNotAdmin() throws SQLException;
}
