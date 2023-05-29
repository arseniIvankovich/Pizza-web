package by.fpmibsu.Servlet;

import by.fpmibsu.Entity.User;
import by.fpmibsu.Entity.Vacancy;
import by.fpmibsu.Services.UserService;
import by.fpmibsu.Services.VacancyService;
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

@WebServlet("/vacancy")
public class VacancyServlet extends HttpServlet {
    static final Logger vacancyServletLogger = LogManager.getLogger(VacancyServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        vacancyServletLogger.info("Enter the vacancy page");
        Long id = (Long) req.getSession().getAttribute("userId");
        User user = userService.findEntityById(id);

        String jsonString = new ObjectMapper().writeValueAsString(user);
        req.setAttribute("user", jsonString);
        req.getRequestDispatcher("/jsp/vacancy.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        vacancyServletLogger.debug("Add new vacancy to the user");
        Long id = (Long) req.getSession().getAttribute("userId");
        String choicer = req.getParameter("choicer");
        VacancyService vacancyService = new VacancyService();
        Vacancy vacancy = vacancyService.findByName(choicer);
        vacancyService.addApplication(id, vacancy.getVacancyID());
        vacancyServletLogger.debug("Redirect to main page");
        resp.sendRedirect(req.getContextPath() + "/");

    }
}