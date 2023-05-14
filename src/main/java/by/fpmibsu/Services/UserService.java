package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.AddressDaoImpl;
import by.fpmibsu.Dao.DaoImpl.UserDaoImpl;
import by.fpmibsu.Dao.UserDao;
import by.fpmibsu.Entity.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private UserDao userDao;

    public UserService() {
        this.userDao = new UserDaoImpl();
    }

    public User findEntityById(Long id)  {
        return userDao.findEntityById(id);
    }

    public User findByEmail(String email)  {
        return userDao.findByEmail(email);
    }

    public User checkLogin(String email, String password)  {
        return userDao.checkLogin(email, password);
    }

    public void delete(Long id) {
        userDao.delete(id);
    }

    public void delete(String email)  {
        userDao.delete(userDao.findByEmail(email));
    }

    public void edit(Long id, User newUser)  {
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
    }


    public User createUser(User user)  {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        return userDao.create(user);
    }

    public void editOrder(User user) {
        userDao.updateOrder(user);
    }

    public List<User> getUndeliveredOrdersForUsers()  {
        List<User> users = (ArrayList<User>) userDao.getOrderedUsers();
        List<User> orderedUsers = new ArrayList<>();
        for (User user : users)
            if (user.getOrder().getStatus() == false)
                orderedUsers.add(user);

        return orderedUsers;
    }

    public List<User> getAllNotdAmin()  {
        return userDao.getAllNotAdmin();
    }

}
