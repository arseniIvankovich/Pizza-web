package by.fpmibsu.Dao;

import by.fpmibsu.Entity.Vacancy;

public interface VacancyDao extends BaseDao<Long, Vacancy> {
    Vacancy findByName(String pattern);

    boolean addToUserVacancy(Long userId, Long vacancyId);
}
