package by.fpmibsu.Servlet;

import by.fpmibsu.Dao.DaoImpl.AddressDaoImpl;
import by.fpmibsu.Dao.DaoImpl.OrderDaoImpl;
import by.fpmibsu.Dao.DaoImpl.RoleDaoImpl;
import by.fpmibsu.Dao.DaoImpl.UserDaoImpl;
import by.fpmibsu.Services.AddressService;
import by.fpmibsu.Services.RoleSetvice;
import by.fpmibsu.Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private UserService userService;
    private AddressService addressService;

    @Override
    public void init() throws ServletException {
        this.addressService = new AddressService(new AddressDaoImpl());
        this.userService = new UserService(new UserDaoImpl(),new OrderDaoImpl(), new AddressDaoImpl(), new RoleDaoImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("streetP","affs");
        req.getRequestDispatcher("/jsp/profile.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
