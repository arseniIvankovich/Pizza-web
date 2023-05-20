package tets;

import by.fpmibsu.Dao.HikariCPDataSource;
import by.fpmibsu.Services.BaseAddressService;
import org.testng.Assert;
import org.testng.annotations.*;

public class BaseAddressServiceTest {
    public BaseAddressService baseAddressService;
@BeforeMethod
    public void init() {
    HikariCPDataSource.rebase();
    baseAddressService = new BaseAddressService();
}

@DataProvider(name = "addressProvide")
    public Object[][] addressProvide () {
    return new Object[][]  {{"Ленина",true},{"Семашко",true},{"hvkjebvjeq",false},{"",false},{null,false}};
}

@Test(dataProvider = "addressProvide")
    public void checkValidStreetIs (String street,Boolean flag) {
    Assert.assertEquals(baseAddressService.checkValidStreet(street),flag);
    }
}
