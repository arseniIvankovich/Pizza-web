package by.fpmibsu.Dao;

import by.fpmibsu.Entity.Vacancy;

import java.sql.SQLException;
import java.util.List;

public interface VacancyDao extends BaseDao<Long, Vacancy> {
    List<Vacancy> findByTitle(String pattern) throws SQLException;

    void addToMMUserVacancy(Long userId, Long vacancyId) throws SQLException;
}
