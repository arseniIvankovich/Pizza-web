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
        setCorsHeaders(resp);

        UserService userService = new UserService();
        vacancyServletLogger.debug("Enter the vacancy page");
        Long id = (Long) req.getSession().getAttribute("userId");
        User user = userService.findEntityById(id);
        resp.getWriter().write(new ObjectMapper().writeValueAsString(user));
      //  req.getRequestDispatcher("/jsp/vacancy.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setCorsHeaders(resp);
        req.setCharacterEncoding("UTF-8");

        vacancyServletLogger.debug("Add new vacancy to the user");
        Long id = (Long) req.getSession().getAttribute("userId");
        String choicer = req.getParameter("choicer");
        VacancyService vacancyService = new VacancyService();
        Vacancy vacancy = vacancyService.findByName(choicer);
        vacancyService.addApplication(id, vacancy.getVacancyID());
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
