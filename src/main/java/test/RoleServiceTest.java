package test;

import by.fpmibsu.Dao.HikariCPDataSource;
import by.fpmibsu.Services.RoleService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RoleServiceTest {
    RoleService roleService;

    @BeforeClass
    public void init() {
        HikariCPDataSource.rebase();
        roleService = new RoleService();
    }

    @DataProvider(name = "ValidRoles")
    public Object[][] roleProviders () {
        return new Object[][] {{1L,"Администратор"},{2L,"Курьер"},{3L,"Пользователь"}};
    }

    @DataProvider(name = "InvalidData")
    public Object[] inValidProvider() {
        return new Object[] {-1L,0L};
    }

    @Test(dataProvider = "ValidRoles")
    public void findByIdTest (Long id,String pattern) {
        Assert.assertEquals(roleService.findEntityById(id).getRole(),pattern);
    }

    @Test (expectedExceptions = {NullPointerException.class})
    public void findIdTestNull () {
        roleService.findEntityById(null);
    }

    @Test(dataProvider = "InvalidData")
    public void findWithInvalidData(Long id) {
        Assert.assertEquals(roleService.findEntityById(id).getRole(),null);
    }
}
