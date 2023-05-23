package test;

import by.fpmibsu.Dao.HikariCPDataSource;
import by.fpmibsu.Entity.Address;
import by.fpmibsu.Entity.Order;
import by.fpmibsu.Entity.Role;
import by.fpmibsu.Entity.User;
import by.fpmibsu.Services.RoleService;
import by.fpmibsu.Services.UserService;
import com.beust.ah.A;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.ArrayList;

public class UserServiceTest {

    UserService userService;

    @BeforeClass
    public void init() {
        HikariCPDataSource.rebase();
        userService = new UserService();
    }
   // {new User(null,"sss","sfsf","123"),false},{new User(null,null,null,"qw","sdf","qwe","145",new Role(3L,"Пользователь"))}
    @DataProvider(name = "Users")
    public Object[][] usersProvider () {
        return new Object[][] {{new User(1L,new Address("Ленина",1,1,1),null,"Иванкович Арсений","$2a$10$e7dQaxR.UBqcFUl0JW.1jOHmlxe3SYQ11byWVzHRe0y5T/9A2vvXa","t@t","375298086623",new Role(1L,"Администратор")),1L},
                {new User(2L,new Address("Ленина",1,1,1),null,"курьер","$2a$10$e7dQaxR.UBqcFUl0JW.1jOHmlxe3SYQ11byWVzHRe0y5T/9A2vvXa","e@e","37589",new Role(2L,"Курьер")),2L},
                {new User(3L,new Address("Ленина",1,1,1),null,"пользователь","$2a$10$e7dQaxR.UBqcFUl0JW.1jOHmlxe3SYQ11byWVzHRe0y5T/9A2vvXa","mail@l","123",new Role(3L,"Пользователь")),3L},
                {new User(null,null,null,null,null,null,null,null),5L},
                {new User(null,null,null,null,null,null),5L},
                {new User(null,null,null,null,null,null,null),6L},
                {new User(null,null,null,null,null,null,null,null),7L}};
    }


    @DataProvider(name = "UsersAsObjectEmails")
    public Object[][] usersEmailProvider () {
        return new Object[][] {{new User(1L,new Address("Ленина",1,1,1),new Order(null,null,null,null,null),"Иванкович Арсений","$2a$10$e7dQaxR.UBqcFUl0JW.1jOHmlxe3SYQ11byWVzHRe0y5T/9A2vvXa","t@t","375298086623",new Role(1L,"Администратор")),"t@t"},
                {new User(2L,new Address("Ленина",1,1,1),new Order(null,null,null,null,null),"курьер","$2a$10$e7dQaxR.UBqcFUl0JW.1jOHmlxe3SYQ11byWVzHRe0y5T/9A2vvXa","e@e","37589",new Role(2L,"Курьер")),"e@e"},
                {new User(3L,new Address("Ленина",1,1,1),new Order(null,null,null,null,null),"пользователь","$2a$10$e7dQaxR.UBqcFUl0JW.1jOHmlxe3SYQ11byWVzHRe0y5T/9A2vvXa","mail@l","123",new Role(3L,"Пользователь")),"mail@l"},
                {new User(null,null,null,null,null,null,null,null),"wehff@dsv"},
                {new User(null,null,null,null,null,null),""},
                {new User(null,null,null,null,null,null,null),"false"},
                {new User(null,null,null,null,null,null,null,null),""}};
    }


    @DataProvider(name = "UsersPasswordAndEmail")
    public Object[][] usersEmailPasswordProvider () {
        return new Object[][] {{"t@t","qwerty",true},
                {"e@e","qwerty",true},
                {"mail@l","qwerty",true},
                {"e@e",null,false},
                {"e@e",null,false},
                };
    }

    @DataProvider(name = "InvalidUsersPasswordAndEmail")
    public Object[][] invalidUsersEmailPasswordProvider () {
        return new Object[][] {
                {"",""},
                {null,"qwerty"},
                {"sas","sadcasvc"},
                {null,null},
                {"sas","sadcasvc"}};
    }

    @DataProvider(name = "UsersEmails")
    public Object[][] usersEmailForCheckingProvider () {
        return new Object[][] {{"t@t",true},
                {"e@e",true},
                {"mail@l",true},
                {"adfdfsa",false},
                {"",false},
                {null,false}
        };
    }

    @Test(dataProvider = "Users")
    public void findByIdTest (User user, Long id) {
        Assert.assertEquals(userService.findEntityById(id),user);
    }

    @Test(dataProvider = "UsersAsObjectEmails")
    public void findByEmail (User user,String email) {
        Assert.assertEquals(userService.findByEmail(email),user);
    }

    @Test(dataProvider = "UsersPasswordAndEmail")
    public void checkLoginAndPasswordTest (String email,String password,Boolean flag) {
        Assert.assertEquals(userService.checkLoginPassword(email,password),flag);
    }
    @Test(dataProvider = "InvalidUsersPasswordAndEmail", expectedExceptions = {NullPointerException.class})
    public void checkNullLoginAndPasswordTest (String email,String password) {
        userService.checkLoginPassword(email,password);
    }

    @Test(dataProvider = "UsersEmails")
    public void checkEmailTest (String email,Boolean flag) {
        Assert.assertEquals(userService.checkEmail(email),flag);
    }
}

