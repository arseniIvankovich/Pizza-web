package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.AddressDaoImpl;
import by.fpmibsu.Dao.DaoImpl.OrderDaoImpl;
import by.fpmibsu.Dao.DaoImpl.RoleDaoImpl;
import by.fpmibsu.Dao.DaoImpl.UserDaoImpl;
import by.fpmibsu.Entity.Order;
import by.fpmibsu.Entity.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    final UserDaoImpl userDao;
    final OrderDaoImpl orderDao;
    final AddressDaoImpl addressDao;

    final RoleDaoImpl roleDao;


    public UserService(UserDaoImpl userDao, OrderDaoImpl orderDao, AddressDaoImpl addressDao, RoleDaoImpl roleDao) {
        this.userDao = userDao;
        this.orderDao = orderDao;
        this.addressDao = addressDao;
        this.roleDao = roleDao;
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

    public User findByName(String name) throws SQLException {
        return userDao.findByName(name);
    }

    public User checkLogin(String email, String password) throws SQLException {
        return userDao.checkLogin(email,password);
    }
    public void delete(Long id) throws SQLException {
        orderDao.delete(userDao.findEntityById(id).getOrder().getId());
        userDao.delete(id);
    }

    public void edit (Long id, User newUser) throws SQLException{
        User oldUser = userDao.findEntityById(id);

        if (addressDao.checkByStreetHouseEntranceFlat(newUser.getAddresses().getStreet(),newUser.getAddresses().getHouseNumber(),
                newUser.getAddresses().getEntrance(), newUser.getAddresses().getFlatNumber()))
            oldUser.setAddresses(newUser.getAddresses());
        if (newUser.getRole() != null)
            oldUser.setRole(newUser.getRole());
        if (newUser.getEmail() != null)
            oldUser.setEmail(newUser.getEmail());
        if (newUser.getTelephone() != null)
            oldUser.setTelephone(newUser.getTelephone());
        if (newUser.getFirstName_lastName() != null)
            oldUser.setFirstName_lastName(newUser.getFirstName_lastName());

        userDao.update(oldUser);
    }


    public boolean createUser (User user) throws SQLException {
        return userDao.create(user);
    }

    public void editOrder (User user, Order order) throws SQLException {
        Long index = orderDao.getLastID();
        user.setOrder(orderDao.findEntityById(index));
    }
}
