package by.fpmibsu.Servlet;

import by.fpmibsu.Entity.Address;
import by.fpmibsu.Entity.User;
import by.fpmibsu.Services.AddressService;
import by.fpmibsu.Services.BaseAddressService;
import by.fpmibsu.Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    static final Logger profileServletLogger = LogManager.getLogger(ProfileServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        profileServletLogger.debug("Enter the profile page");
        setCorsHeaders(resp);
        UserService userService = new UserService();
        Long id = (Long) req.getSession().getAttribute("userId");
        User user = userService.findEntityById(id);
        resp.getWriter().write(new ObjectMapper().writeValueAsString(user));

        //resp.getWriter().write(new ObjectMapper().writeValueAsString(user));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCorsHeaders(resp);
        req.setCharacterEncoding("UTF-8");
        UserService userService = new UserService();
        AddressService addressService = new AddressService();
        BaseAddressService baseAddressService = new BaseAddressService();
        String street = req.getParameter("street");
        if (!baseAddressService.checkValidStreet(street)) {
            profileServletLogger.debug("Invalid string");
            resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);

            return;
        }
        String stringHouseNumber = req.getParameter("house");
        Integer houseNumber = Integer.parseInt(stringHouseNumber);
        if (houseNumber <= 0 || houseNumber > 300) {
            profileServletLogger.debug("Invalid house number");
            resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);

            return;
        }
        String stringEntrance = req.getParameter("entrance");

        Integer entrance = Integer.parseInt(stringEntrance);
        if (entrance > 20 || entrance < 0) {
            profileServletLogger.debug("Invalid entrance");
            resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            return;
        }
        String stringFlatNumber = req.getParameter("flat");
        Integer flatNumber = Integer.parseInt(stringFlatNumber);
        if (flatNumber <= 0 || flatNumber > 400) {
            profileServletLogger.debug("Invalid flat number");
            resp.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);

            return;
        }
        String firstSecondName = req.getParameter("firstSecondName");
        String email = req.getParameter("email");
        String telephone = req.getParameter("telephone");
        addressService.create(new Address(street, houseNumber, entrance, flatNumber));
        Address address = addressService.findByStreetHouseEntranceFlat(street,houseNumber,entrance,flatNumber);
        User newUser = new User(address, firstSecondName, email, telephone);
        userService.edit(userService.findEntityById((Long) req.getSession().getAttribute("userId")).getUserId(), newUser);

        //resp.sendRedirect(req.getContextPath() + "/profile");
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
