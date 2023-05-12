package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.UserDaoImpl;
import by.fpmibsu.Dao.DaoImpl.VacancyDaoImpl;
import by.fpmibsu.Entity.Vacancy;

import java.sql.SQLException;
import java.util.List;

public class VacancyService {

    public List<Vacancy> findAll() throws SQLException {
        return new VacancyDaoImpl().findAll();
    }

    public Vacancy findByName(String pattern) throws SQLException {
        return new VacancyDaoImpl().findByName(pattern);
    }

    public void addApplication(Long userId, Long vacancyId) throws SQLException {
        new VacancyDaoImpl().addToMMUserVacancy(userId,vacancyId);
    }
}