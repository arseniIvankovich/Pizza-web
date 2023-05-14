package by.fpmibsu.Servlet;

import by.fpmibsu.Entity.Address;
import by.fpmibsu.Entity.User;
import by.fpmibsu.Services.AddressService;
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        Long id = (Long) req.getSession().getAttribute("userId");
        User user = userService.findEntityById(id);

        String jsonString = new ObjectMapper().writeValueAsString(user);
        req.setAttribute("user", jsonString);
        req.getRequestDispatcher("/jsp/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        UserService userService = new UserService();
        AddressService addressService = new AddressService();
        String street = req.getParameter("streetP");
        String houseNumber = req.getParameter("houseP");
        Integer entrance = Integer.parseInt(req.getParameter("entranceP"));
        Integer flatNumber = Integer.parseInt(req.getParameter("flatP"));
        String firstSecondName = req.getParameter("firstSecondP");
        String email = req.getParameter("emailP");
        String telephone = req.getParameter("telephoneP");
        Address address = addressService.findByStreetHouseEntranceFlat(street, houseNumber, entrance, flatNumber);
        User newUser = new User(address, firstSecondName, email, telephone);
        userService.edit(userService.findEntityById((Long) req.getSession().getAttribute("userId")).getUserId(), newUser);

        resp.sendRedirect(req.getContextPath() + "/profile");
    }
}
