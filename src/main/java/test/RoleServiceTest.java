package test;

import by.fpmibsu.Dao.HikariCPDataSource;
import by.fpmibsu.Entity.Role;
import by.fpmibsu.Services.AddressService;
import by.fpmibsu.Services.RoleService;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RoleServiceTest {
    RoleService roleService;

    @BeforeMethod
    public void init() {
        HikariCPDataSource.rebase();
        roleService = new RoleService();
    }

    @DataProvider(name = "ValidRoles")
    public Object[][] roleProviders () {
        return new Object[][] {{1L,"Администратор"},{2L,"Курьер"},{3L,"Пользователь"}};
    }

    @Test(dataProvider = "ValidRoles")
    @Description("Find role by id")
    public void findByIdTest (Long id,String pattern) {
        Assert.assertEquals(roleService.findEntityById(id).getRole(),pattern);
    }

}
