package by.fpmibsu.Dao;

import by.fpmibsu.Entity.Vacancy;

import java.sql.SQLException;
import java.util.List;

public interface VacancyDao extends BaseDao<Long, Vacancy> {
    Vacancy findByName(String pattern);

    void addToMMUserVacancy(Long userId, Long vacancyId);
}
