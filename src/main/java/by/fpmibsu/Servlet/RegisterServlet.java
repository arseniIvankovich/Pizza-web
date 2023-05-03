package by.fpmibsu.Servlet;

import by.fpmibsu.Dao.DaoImpl.AddressDaoImpl;
import by.fpmibsu.Dao.DaoImpl.OrderDaoImpl;
import by.fpmibsu.Dao.DaoImpl.RoleDaoImpl;
import by.fpmibsu.Dao.DaoImpl.UserDaoImpl;
import by.fpmibsu.Entity.Address;
import by.fpmibsu.Entity.Role;
import by.fpmibsu.Entity.User;
import by.fpmibsu.Services.AddressService;
import by.fpmibsu.Services.RoleSetvice;
import by.fpmibsu.Services.UserService;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserService userService;
    private AddressService addressService;

    private RoleSetvice roleSetvice;
    @Override
    public void init() throws ServletException {
        this.addressService = new AddressService(new AddressDaoImpl());
        this.userService = new UserService(new UserDaoImpl(),new OrderDaoImpl(), new AddressDaoImpl(), new RoleDaoImpl());
        this.roleSetvice = new RoleSetvice(new RoleDaoImpl());

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String street = req.getParameter("street");
        int houseNumber = Integer.parseInt(req.getParameter("house"));
        int entrance = Integer.parseInt(req.getParameter("entrance"));
        int flatNumber = Integer.parseInt(req.getParameter("flat"));
        String firstSecondName = req.getParameter("firstSecondName");
        String email = req.getParameter("email");
        String telephone = req.getParameter("telephone");
        String passwordInput = req.getParameter("password");
        String password = BCrypt.hashpw(passwordInput,BCrypt.gensalt());
        Address address;
        Role role;
        try {
            address = addressService.findByStreetHouseEntranceFlat(street,houseNumber,entrance,flatNumber);
            role = roleSetvice.findEntityById(3L);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        User user = new User(address,firstSecondName,password,email,"+" + telephone,role);
        try {
            userService.createUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("/").forward(req,resp);
    }

}
