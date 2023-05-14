package by.fpmibsu.Servlet;

import by.fpmibsu.Entity.Address;
import by.fpmibsu.Entity.Role;
import by.fpmibsu.Entity.User;
import by.fpmibsu.Services.AddressService;
import by.fpmibsu.Services.BaseAddressService;
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
    final String path = "/jsp/register.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        AddressService addressService = new AddressService();
        BaseAddressService baseAddressService = new BaseAddressService();
        UserService userService = new UserService();
        RoleService roleService = new RoleService();
        String street = req.getParameter("street");
        if (street == null || !baseAddressService.checkValidStreet(street)) {
            req.setAttribute("streetError",true);
            req.getRequestDispatcher(path).forward(req,resp);
        }
        int houseNumber = Integer.parseInt(req.getParameter("house"));
        if (houseNumber > 300) {
            req.setAttribute("houseNumberError",true);
        }
        int entrance = Integer.parseInt(req.getParameter("entrance"));
        int flatNumber = Integer.parseInt(req.getParameter("flat"));
        String firstSecondName = req.getParameter("firstSecondName");
        String email = req.getParameter("email");
        String telephone = req.getParameter("telephone");
        String passwordInput = req.getParameter("password");
        String password = BCrypt.hashpw(passwordInput, BCrypt.gensalt());
        Address address = addressService.findByStreetHouseEntranceFlat(street, houseNumber, entrance, flatNumber);
        Role role = roleService.findEntityById(3L);

        User user = new User(address, firstSecondName, password, email, "+" + telephone, role);
        userService.createUser(user);

        resp.sendRedirect(req.getContextPath() + "/");
    }

}
