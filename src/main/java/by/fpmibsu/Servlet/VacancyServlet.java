package by.fpmibsu.Servlet;

import by.fpmibsu.Entity.User;
import by.fpmibsu.Entity.Vacancy;
import by.fpmibsu.Services.UserService;
import by.fpmibsu.Services.VacancyService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/vacancy")
public class VacancyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        Long id = (Long) req.getSession().getAttribute("userId");
        User user = userService.findEntityById(id);

        String jsonString = new ObjectMapper().writeValueAsString(user);
        req.setAttribute("user", jsonString);
        req.getRequestDispatcher("/jsp/vacancy.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Long id = (Long) req.getSession().getAttribute("userId");
        String choicer = req.getParameter("choicer");
        VacancyService vacancyService = new VacancyService();
        Vacancy vacancy = vacancyService.findByName(choicer);
        vacancyService.addApplication(id, vacancy.getVacancyID());

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
