package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.AddressDaoImpl;
import by.fpmibsu.Dao.DaoImpl.OrderDaoImpl;
import by.fpmibsu.Dao.DaoImpl.RoleDaoImpl;
import by.fpmibsu.Dao.DaoImpl.UserDaoImpl;
import by.fpmibsu.Dao.UserDao;
import by.fpmibsu.Entity.Order;
import by.fpmibsu.Entity.User;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private  UserDao userDao;

    public UserService() {
        this.userDao = new UserDaoImpl();
    }
    public List<User> findAll () throws SQLException {
        return userDao.findAll();
    }

    public User findEntityById (Long id) throws SQLException {
        return userDao.findEntityById(id);
    }

    public User findByEmail (String email) throws SQLException {
        return userDao.findByEmail(email);
    }

    public User checkLogin(String email, String password) throws SQLException {
        return userDao.checkLogin(email,password);
    }
    public void delete(Long id) throws SQLException {
        userDao.delete(id);
    }

    public void delete (String email) throws SQLException {
        userDao.delete(userDao.findByEmail(email));
    }

    public void edit (Long id, User newUser) throws SQLException{
      /*  User oldUser = new UserDaoImpl().findEntityById(id);

        if (new AddressDaoImpl().checkByStreetHouseEntranceFlat(newUser.getAddresses().getStreet(),newUser.getAddresses().getHouseNumber(),
                newUser.getAddresses().getEntrance(), newUser.getAddresses().getFlatNumber()))
            oldUser.setAddresses(newUser.getAddresses());
        if (newUser.getEmail() != null)
            oldUser.setEmail(newUser.getEmail());
        if (newUser.getTelephone() != null)
            oldUser.setTelephone(newUser.getTelephone());
        if (newUser.getFirstName_lastName() != null)
            oldUser.setFirstName_lastName(newUser.getFirstName_lastName());

        userDao.update(oldUser);*/
    }


    public User createUser (User user) throws SQLException {
        return userDao.create(user);
    }

    public void editOrder (User user) throws SQLException {
        userDao.updateOrder(user);
    }

    public List<User> getUndeliveredOrdersForUsers() throws SQLException {
        List<User> users = (ArrayList<User>) userDao.getOrderedUsers();
        List<User> orderedUsers = new ArrayList<>();
        for (User user: users)
            if (user.getOrder().getStatus() == false)
                orderedUsers.add(user);

        return orderedUsers;
    }

    public List<User> getAllNotdAmin() throws SQLException {
        return userDao.getAllNotAdmin();
    }

}
