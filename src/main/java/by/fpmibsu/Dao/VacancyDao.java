package by.fpmibsu.Dao;

import by.fpmibsu.Entity.Vacancy;

import java.util.List;

public interface VacancyDao extends BaseDao<Long, Vacancy> {
    List<Vacancy> findByTitle(String pattern);

    void AddToMMUserVacancy(Long userId, Long vacancyId);
}
