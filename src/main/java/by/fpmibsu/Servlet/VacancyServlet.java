package by.fpmibsu.Servlet;


import by.fpmibsu.Dao.DaoImpl.*;
import by.fpmibsu.Entity.User;
import by.fpmibsu.Entity.Vacancy;
import by.fpmibsu.Services.UserService;
import by.fpmibsu.Services.VacancyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/vacancy")
public class VacancyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService(new UserDaoImpl(),new OrderDaoImpl(), new AddressDaoImpl(), new RoleDaoImpl());
        Long id = (Long) req.getSession().getAttribute("userId");
        User user;
        try {
            user = userService.findEntityById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("user",user);
        req.getRequestDispatcher("/jsp/vacancy.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService(new UserDaoImpl(),new OrderDaoImpl(), new AddressDaoImpl(), new RoleDaoImpl());
        req.setCharacterEncoding("UTF-8");
        Long id = (Long) req.getSession().getAttribute("userId");
        String choicer = req.getParameter("choicer");
        VacancyService vacancyService = new VacancyService(new VacancyDaoImpl(), new UserDaoImpl());
        Vacancy vacancy;
        try {
            vacancy = vacancyService.findByName(choicer);
            vacancyService.addApplication(id,vacancy.getVacancyID());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
