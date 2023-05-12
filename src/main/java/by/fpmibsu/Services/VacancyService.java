package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.PizzaDaoImpl;
import by.fpmibsu.Dao.DaoImpl.UserDaoImpl;
import by.fpmibsu.Dao.DaoImpl.VacancyDaoImpl;
import by.fpmibsu.Dao.PizzaDao;
import by.fpmibsu.Dao.VacancyDao;
import by.fpmibsu.Entity.Vacancy;

import java.sql.SQLException;
import java.util.List;

public class VacancyService {

    private VacancyDao vacancyDao;

    public VacancyService() {
        this.vacancyDao = new VacancyDaoImpl();
    }


    public Vacancy findByName(String pattern) throws SQLException {
        return new VacancyDaoImpl().findByName(pattern);
    }

    public void addApplication(Long userId, Long vacancyId) throws SQLException {
        new VacancyDaoImpl().addToMMUserVacancy(userId,vacancyId);
    }
}