package by.fpmibsu.Servlet;

import by.fpmibsu.Entity.Address;
import by.fpmibsu.Entity.User;
import by.fpmibsu.Services.AddressService;
import by.fpmibsu.Services.BaseAddressService;
import by.fpmibsu.Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    String path = "/jsp/profile.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        Long id = (Long) req.getSession().getAttribute("userId");
        User user = userService.findEntityById(id);

        String jsonString = new ObjectMapper().writeValueAsString(user);
        req.setAttribute("user", jsonString);
        req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        UserService userService = new UserService();
        AddressService addressService = new AddressService();
        BaseAddressService baseAddressService = new BaseAddressService();
        String street = req.getParameter("street");
        if (street == null || !baseAddressService.checkValidStreet(street)) {
            req.setAttribute("streetError",true);
            req.getRequestDispatcher(path).forward(req,resp);
            return;
        }
        String stringHouseNumber = req.getParameter("house");
        if (stringHouseNumber.equals("")) {
            req.setAttribute("houseNumberError",true);
            req.getRequestDispatcher(path).forward(req,resp);
            return;
        }
        Integer houseNumber = Integer.parseInt( stringHouseNumber);
        if (houseNumber <= 0 || houseNumber > 300) {
            req.setAttribute("houseNumberError",true);
            req.getRequestDispatcher(path).forward(req,resp);
            return;
        }
        String stringEntrance = req.getParameter("entrance");
        if (stringEntrance.equals("")) {
            req.setAttribute("entranceError",true);
            req.getRequestDispatcher(path).forward(req,resp);
        }
        Integer entrance = Integer.parseInt(stringEntrance);
        if (entrance > 20 || entrance < 0) {
            req.setAttribute("entranceError",true);
            req.getRequestDispatcher(path).forward(req,resp);
            return;
        }
        String stringFlatNumber = req.getParameter("flat");
        if (stringFlatNumber.equals("")) {
            req.setAttribute("flatNumberError",true);
            req.getRequestDispatcher(path).forward(req,resp);
            return;
        }
        Integer flatNumber = Integer.parseInt(stringFlatNumber);
        if (flatNumber <= 0 || flatNumber > 400) {
            req.setAttribute("flatNumberError",true);
            req.getRequestDispatcher(path).forward(req,resp);
            return;
        }
        String firstSecondName = req.getParameter("firstSecond");
        if (firstSecondName.equals("")) {
            req.setAttribute("nameError",true);
            req.getRequestDispatcher(path).forward(req,resp);
            return;
        }
        String email = req.getParameter("email");
        if (email.equals("")) {
            req.setAttribute("emailError",true);
            req.getRequestDispatcher(path).forward(req,resp);
            return;
        }
        String telephone = req.getParameter("telephone");
        if (telephone.equals("")) {
            req.setAttribute("telephoneError",true);
            req.getRequestDispatcher(path).forward(req,resp);
            return;
        }
        addressService.create(new Address(street, houseNumber, entrance, flatNumber));
        Address address = addressService.findByStreetHouseEntranceFlat(street,houseNumber,entrance,flatNumber);
        User newUser = new User(address, firstSecondName, email, telephone);
        userService.edit(userService.findEntityById((Long) req.getSession().getAttribute("userId")).getUserId(), newUser);

        resp.sendRedirect(req.getContextPath() + "/profile");
    }
}
