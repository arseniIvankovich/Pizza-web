package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.UserDaoImpl;
import by.fpmibsu.Dao.DaoImpl.VacancyDaoImpl;
import by.fpmibsu.Entity.Vacancy;

import java.sql.SQLException;
import java.util.List;

public class VacancyService {
    final VacancyDaoImpl vacancyDao;

    final UserDaoImpl userDao;

    public VacancyService(VacancyDaoImpl vacancyDao, UserDaoImpl userDao) {
        this.vacancyDao = vacancyDao;
        this.userDao = userDao;
    }

    public List<Vacancy> findAll() throws SQLException {
        return vacancyDao.findAll();
    }

    public Vacancy findByName(String pattern) throws SQLException {
        return vacancyDao.findByName(pattern);
    }

    public void addToMMUserVacancy(Long userId, Long vacancyId) throws SQLException {
        vacancyDao.addToMMUserVacancy(userId,vacancyId);
    }

    public void submitApplication (Long vacancyId, Long userId) throws SQLException{
        if (userDao.checkUserByEmail(userDao.findEntityById(userId).getEmail()))
            vacancyDao.addToMMUserVacancy(userId, vacancyId);
        else
            throw new SQLException();

    }

}