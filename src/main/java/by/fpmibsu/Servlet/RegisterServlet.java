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
        registerServletLogger.debug("Enter register page");
        setCorsHeaders(resp);
       // req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCorsHeaders(resp);
        req.setCharacterEncoding("UTF-8");
        AddressService addressService = new AddressService();
        BaseAddressService baseAddressService = new BaseAddressService();
        UserService userService = new UserService();
        RoleService roleService = new RoleService();
        String street = req.getParameter("street");
        registerServletLogger.debug("Enter register page");
        if (street == null || !baseAddressService.checkValidStreet(street)) {
            registerServletLogger.debug("Invalid street");
            resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);

            return;
        }
        String stringHouseNumber = req.getParameter("house");
        Integer houseNumber = Integer.parseInt(stringHouseNumber);
        if (houseNumber <= 0 || houseNumber > 300) {
            registerServletLogger.debug("Invalid house number");
            resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);

            return;
        }
        String stringEntrance = req.getParameter("entrance");
        int entrance = Integer.parseInt(stringEntrance);
        if (entrance > 20 || entrance < 0) {
            registerServletLogger.debug("Invalid entrance");
            resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);

            return;
        }
        String stringFlatNumber = req.getParameter("flat");
        int flatNumber = Integer.parseInt(stringFlatNumber);
        if (flatNumber <= 0 || flatNumber > 400) {
            registerServletLogger.debug("Invalid flat number");
            resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return;
        }
        String firstSecondName = req.getParameter("firstSecondName");
        String email = req.getParameter("email");
        String telephone = req.getParameter("telephone");
        String password = req.getParameter("password");

        addressService.create(new Address(street, houseNumber, entrance, flatNumber));
        Address address = addressService.findByStreetHouseEntranceFlat(street,houseNumber,entrance,flatNumber);
        Role role = roleService.findEntityById(3L);
        User user = new User(address, firstSecondName, password, email, telephone, role);
        userService.createUser(user);

        //resp.sendRedirect(req.getContextPath() + "/");
    }
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCorsHeaders(resp);
    }

    private void setCorsHeaders(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*"); // Replace "*" with the specific allowed origin if needed
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "3600");
    }


}
