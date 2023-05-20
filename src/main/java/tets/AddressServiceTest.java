package tets;

import by.fpmibsu.Dao.HikariCPDataSource;
import by.fpmibsu.Entity.Address;
import by.fpmibsu.Services.AddressService;
import com.beust.ah.A;
import org.postgresql.util.PSQLException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class AddressServiceTest {
    public AddressService addressService;
    @BeforeMethod
    public void init() {
        HikariCPDataSource.rebase();
        addressService = new AddressService();
    }

    @DataProvider(name = "ValidAddresses")
    public Object[] insertAddresses() {
        return new Object[]{new Address("Ленина",1,1,1),
                new Address("Семашко",1,1,1)
                };
    }

    @DataProvider(name = "ValidAddressesWithId")
    public Object[][] insertAddressesWithId() {
        return new Object[][]{{1L,"Ленина",1,1,1},
                {2L,"Семашко",1,1,1}
        };
    }

    @DataProvider(name = "FindValidAddresses")
    public Object[][] provideValidAddresses() {
        return new Object[][] {{"Ленина",1,1,1,1L},
                {"Семашко",1,1,1,2L}};
    }

    @DataProvider(name = "NullAddresses")
    public Object[] insertNullAddresses() {
        return new Object[]{
        new Address("Ленина",null,1,1),
        new Address("Ленина",1,null,1),
        new Address("Ленина",1,1,null),
        new Address(null,null,null,null)};
    }



    @Test(dataProvider = "ValidAddresses")
    public void insertAddresses (Address address) {
        Assert.assertEquals(addressService.create(address),address);
    }

    @Test(dataProvider = "NullAddresses", expectedExceptions = {SQLException.class, NullPointerException.class, PSQLException.class})
    public void insertNullDate (Address address) {
        addressService.create(address);
    }

    @Test(dataProvider = "ValidAddressesWithId")
    public void findAddresses (Long id,String street,Integer house,Integer entrance,Integer flat) {
        Address address = addressService.findByStreetHouseEntranceFlat(street,house,entrance,flat);
        Assert.assertEquals(address,new Address(street,house,entrance,flat));
        Assert.assertEquals(address.getAddressID(),id);

    }
}