package by.fpmibsu.Services;

import by.fpmibsu.Dao.DaoImpl.VacancyDaoImpl;
import by.fpmibsu.Dao.VacancyDao;
import by.fpmibsu.Entity.Vacancy;

public class VacancyService {

    private VacancyDao vacancyDao;

    public VacancyService() {
        this.vacancyDao = new VacancyDaoImpl();
    }


    public Vacancy findByName(String pattern) {
        return new VacancyDaoImpl().findByName(pattern);
    }

    public Boolean addApplication(Long userId, Long vacancyId) {
        new VacancyDaoImpl().addToUserVacancy(userId, vacancyId);
        return true;
    }
}