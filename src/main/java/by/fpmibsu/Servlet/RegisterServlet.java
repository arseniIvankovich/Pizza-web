package by.fpmibsu.Servlet;

import by.fpmibsu.Entity.Address;
import by.fpmibsu.Entity.Role;
import by.fpmibsu.Entity.User;
import by.fpmibsu.Services.AddressService;
import by.fpmibsu.Services.RoleService;
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        AddressService addressService = new AddressService();
        UserService userService = new UserService();
        RoleService roleSetvice = new RoleService();
        String street = req.getParameter("street");
        int houseNumber = Integer.parseInt(req.getParameter("house"));
        int entrance = Integer.parseInt(req.getParameter("entrance"));
        int flatNumber = Integer.parseInt(req.getParameter("flat"));
        String firstSecondName = req.getParameter("firstSecondName");
        String email = req.getParameter("email");
        String telephone = req.getParameter("telephone");
        String passwordInput = req.getParameter("password");
        String password = BCrypt.hashpw(passwordInput, BCrypt.gensalt());
        Address address = addressService.findByStreetHouseEntranceFlat(street, houseNumber, entrance, flatNumber);
        Role role = roleSetvice.findEntityById(3L);

        User user = new User(address, firstSecondName, password, email, "+" + telephone, role);
        userService.createUser(user);

        resp.sendRedirect(req.getContextPath() + "/");
    }

}
