package test;

import by.fpmibsu.Dao.HikariCPDataSource;
import by.fpmibsu.Services.OrderService;
import by.fpmibsu.Services.VacancyService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VacancyTest {
    public VacancyService vacancyService;
    @BeforeClass
    public void init() {
        HikariCPDataSource.rebase();
        vacancyService = new VacancyService();
    }


    @DataProvider(name = "InvalidVacancies")
    public Object[] invalidVacancyProvider () {
        return new Object[] {
                "","hvgf",null
        };
    }

    @DataProvider(name = "Vacancies")
    public Object[] VacancyProvider () {
        return new Object[] {
                "Пиццер","Пеший курьер","Водитель-курьер"
        };
    }

    @DataProvider(name = "InvalidApplications")
    public Object[][] InvalidIds () {
        return new Object[][] {
                {null,null},
                {null,1L},
                {1L,null},
        };
    }

    @DataProvider(name = "ValidApplications")
    public Object[][] validIds () {
        return new Object[][] {
                {2L,1L},
                {2L,2L},
                {2L,3L},
                {3L,1L},
                {3L,2L}
        };
    }

    @Test(dataProvider = "InvalidVacancies")
    public void invalidFindVacancy (String pattern) {
        vacancyService.findByName(pattern);
    }

    @Test(dataProvider = "Vacancies")
    public void findVacancy (String pattern) {
        Assert.assertEquals(vacancyService.findByName(pattern).getName(),pattern);
    }

    @Test(dataProvider = "InvalidApplications",expectedExceptions = {NullPointerException.class})
    public void invalidAddApplication (Object id1,Object id2) {
        vacancyService.addApplication((Long) id1, (Long) id2);
    }

    @Test(dataProvider = "ValidApplications")
    public void validAddApplication (Long id1,Long id2) {
        Assert.assertEquals(vacancyService.addApplication(id1,id2),true);
    }
}
