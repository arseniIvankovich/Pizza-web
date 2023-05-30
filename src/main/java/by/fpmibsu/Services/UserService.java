package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.AddressDaoImpl;
import by.fpmibsu.Dao.DaoImpl.UserDaoImpl;
import by.fpmibsu.Dao.UserDao;
import by.fpmibsu.Entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final UserDao userDao;

    static final Logger userServiceLogger = LogManager.getLogger(UserService.class);

    public UserService() {
        this.userDao = new UserDaoImpl();
    }

    public User findEntityById(Long id) {
        userServiceLogger.debug("find user object by id");
        return userDao.findEntityById(id);
    }

    public User findByEmail(String email) {
        userServiceLogger.debug("find user object by id");
        return userDao.findByEmail(email);
    }


    public Boolean checkLoginPassword(String email, String password) {
        userServiceLogger.debug("Check valid email is");
        User user = userDao.findByEmail(email);
        return BCrypt.checkpw(password, user.getPassword());
    }

    public Boolean delete(String email) {
        userServiceLogger.debug("Delete information about user by email in DB");
        return userDao.delete(userDao.findByEmail(email));
    }

    public boolean edit(Long id, User newUser) {
        userServiceLogger.debug("Edit information about user");
        User oldUser = new UserDaoImpl().findEntityById(id);

        if (new AddressDaoImpl().checkByStreetHouseEntranceFlat(newUser.getAddresses().getStreet(), newUser.getAddresses().getHouseNumber(),
                newUser.getAddresses().getEntrance(), newUser.getAddresses().getFlatNumber()))
            oldUser.setAddresses(newUser.getAddresses());
        if (newUser.getEmail() != null)
            oldUser.setEmail(newUser.getEmail());
        if (newUser.getTelephone() != null)
            oldUser.setTelephone(newUser.getTelephone());
        if (newUser.getFirstName_lastName() != null)
            oldUser.setFirstName_lastName(newUser.getFirstName_lastName());

        userDao.update(oldUser);
        return true;
    }


    public Boolean createUser(User user) {
        userServiceLogger.debug("Create new user");
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userDao.create(user);
        return true;
    }

    public Boolean editOrder(User user) {
        userServiceLogger.debug("Edit user's information about order");
        userDao.updateOrder(user);
        return true;
    }

    public List<User> getUndeliveredOrdersForUsers() {
        userServiceLogger.debug("Get list of user's undelivered orders");
        List<User> users = (ArrayList<User>) userDao.getOrderedUsers();
        List<User> orderedUsers = new ArrayList<>();
        for (User user : users)
            if (!user.getOrder().getStatus())
                orderedUsers.add(user);

        return orderedUsers;
    }

    public List<User> getAllNotAdmin() {
        userServiceLogger.debug("Get list of user's who are not admins");
        return userDao.getAllNotAdmin();
    }

    public Boolean checkEmail(String email) {
        userServiceLogger.debug("Check valid email is");
        return userDao.checkUserByEmail(email);
    }

    public List<User> findAll() {
        userServiceLogger.debug("Find all users");
        return userDao.findAll();
    }

    public void deleteById(Long id) {
        userServiceLogger.debug("Delete user by id");
        userDao.delete(id);
    }
}
