package by.fpmibsu.Servlet;

import by.fpmibsu.Entity.Address;
import by.fpmibsu.Entity.Role;
import by.fpmibsu.Entity.User;
import by.fpmibsu.Services.AddressService;
import by.fpmibsu.Services.BaseAddressService;
import by.fpmibsu.Services.RoleService;
import by.fpmibsu.Services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    static final Logger registerServletLogger = LogManager.getLogger(RegisterServlet.class);
    final String path = "/jsp/register.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        registerServletLogger.info("Enter register page");
        req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        registerServletLogger.info("User register into the system");
        AddressService addressService = new AddressService();
        BaseAddressService baseAddressService = new BaseAddressService();
        UserService userService = new UserService();
        RoleService roleService = new RoleService();
        String street = req.getParameter("street");
        registerServletLogger.debug("Enter register page");
        if (street == null || !baseAddressService.checkValidStreet(street)) {
            registerServletLogger.debug("Invalid street");
            req.setAttribute("streetError", true);
            req.getRequestDispatcher(path).forward(req, resp);
            return;
        }
        String stringHouseNumber = req.getParameter("house");
        if (stringHouseNumber.equals("")) {
            registerServletLogger.debug("Empty string");
            req.setAttribute("houseNumberError", true);
            req.getRequestDispatcher(path).forward(req, resp);
            return;
        }
        Integer houseNumber = Integer.parseInt(stringHouseNumber);
        if (houseNumber <= 0 || houseNumber > 300) {
            registerServletLogger.debug("Invalid house number");
            req.setAttribute("houseNumberError", true);
            req.getRequestDispatcher(path).forward(req, resp);
            return;
        }
        String stringEntrance = req.getParameter("entrance");
        if (stringEntrance.equals("")) {
            registerServletLogger.debug("Empty string");
            req.setAttribute("entranceError", true);
            req.getRequestDispatcher(path).forward(req, resp);
        }
        int entrance = Integer.parseInt(stringEntrance);
        if (entrance > 20 || entrance < 0) {
            registerServletLogger.debug("Invalid entrance");
            req.setAttribute("", true);
            req.getRequestDispatcher(path).forward(req, resp);
            return;
        }
        String stringFlatNumber = req.getParameter("flat");
        if (stringFlatNumber.equals("")) {
            registerServletLogger.debug("Empty string");
            req.setAttribute("flatNumberError", true);
            req.getRequestDispatcher(path).forward(req, resp);
            return;
        }
        int flatNumber = Integer.parseInt(stringFlatNumber);
        if (flatNumber <= 0 || flatNumber > 400) {
            registerServletLogger.debug("Invalid flat number");
            req.setAttribute("flatNumberError", true);
            req.getRequestDispatcher(path).forward(req, resp);
            return;
        }
        String firstSecondName = req.getParameter("firstSecondName");
        if (firstSecondName.equals("")) {
            registerServletLogger.debug("Empty string");
            req.setAttribute("nameError", true);
            req.getRequestDispatcher(path).forward(req, resp);
            return;
        }
        String email = req.getParameter("email");
        if (email.equals("")) {
            registerServletLogger.debug("Empty string");
            req.setAttribute("emailError", true);
            req.getRequestDispatcher(path).forward(req, resp);
            return;
        }
        String telephone = req.getParameter("telephone");
        if (telephone.equals("")) {
            registerServletLogger.debug("Empty string");
            req.setAttribute("telephoneError", true);
            req.getRequestDispatcher(path).forward(req, resp);
            return;
        }
        String password = req.getParameter("password");
        if (password.equals("")) {
            registerServletLogger.debug("Empty string");
            req.setAttribute("passwordError", true);
            req.getRequestDispatcher(path).forward(req, resp);
            return;
        }

        addressService.create(new Address(street, houseNumber, entrance, flatNumber));
        Address address = addressService.findByStreetHouseEntranceFlat(street, houseNumber, entrance, flatNumber);
        Role role = roleService.findEntityById(3L);
        User user = new User(address, firstSecondName, password, email, telephone, role);
        userService.createUser(user);

        resp.sendRedirect(req.getContextPath() + "/");
    }

}