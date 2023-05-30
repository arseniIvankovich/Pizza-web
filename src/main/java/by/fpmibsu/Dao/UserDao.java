package by.fpmibsu.Dao;

import by.fpmibsu.Entity.User;

import java.util.List;

public interface UserDao extends BaseDao<Long, User> {
    List<User> findAll();

    User findByEmail(String email);

    Boolean checkUserByEmail(String email);

    void updateOrder(User user);

    List<User> getOrderedUsers();

    List<User> getAllNotAdmin();
}
