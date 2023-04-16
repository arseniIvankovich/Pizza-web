package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.VacancyDaoImpl;
import by.fpmibsu.Entity.Vacancy;

import java.sql.SQLException;
import java.util.List;

public class VacancyService {

    final VacancyDaoImpl vacancyDao;

    public VacancyService(VacancyDaoImpl vacancyDao) {
        this.vacancyDao = vacancyDao;
    }

    public List<Vacancy> findAll() throws SQLException {
        return vacancyDao.findAll();
    }

    public Vacancy findByName(String pattern) throws SQLException {
        return vacancyDao.findByName(pattern);
    }


}
