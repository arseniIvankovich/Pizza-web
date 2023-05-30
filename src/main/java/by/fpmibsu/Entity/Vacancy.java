package by.fpmibsu.Entity;

import java.util.HashSet;
import java.util.Objects;

public class Vacancy extends Entity {

    private HashSet<User> user;
    private Double salary;
    private Integer trial;
    private String name;

    public Vacancy() {
    }

    public Vacancy(HashSet<User> user, Double salary, Integer trial, String name) {
        this.user = user;
        this.salary = salary;
        this.trial = trial;
        this.name = name;
    }

    public Vacancy(Long vacancyID, HashSet<User> user, Double salary, Integer trial, String name) {
        super(vacancyID);
        this.user = user;
        this.salary = salary;
        this.trial = trial;
        this.name = name;
    }

    public Long getVacancyID() {
        return this.getId();
    }

    public void setVacancyID(Long vacancyID) {
        this.setId(vacancyID);
    }

    public HashSet<User> getUser() {
        return user;
    }

    public void setUser(HashSet<User> user) {
        this.user = user;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getTrial() {
        return trial;
    }

    public void setTrial(Integer trial) {
        this.trial = trial;
    }

    public String getName() {
        return name;
    }

    public void setName(String description) {
        this.name = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(user, vacancy.user) && Objects.equals(salary, vacancy.salary) && Objects.equals(trial, vacancy.trial) && Objects.equals(name, vacancy.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, salary, trial, name);
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "user=" + user +
                ", salary=" + salary +
                ", trial=" + trial +
                ", name='" + name + '\'' +
                '}';
    }
}