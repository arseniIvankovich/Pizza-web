package test;

import by.fpmibsu.Dao.HikariCPDataSource;
import by.fpmibsu.Entity.Address;
import by.fpmibsu.Entity.Order;
import by.fpmibsu.Entity.Role;
import by.fpmibsu.Entity.User;
import by.fpmibsu.Services.UserService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {

    UserService userService;

    @BeforeClass
    public void init() {
        HikariCPDataSource.rebase();
        userService = new UserService();
    }

    @DataProvider(name = "Users")
    public Object[][] usersProvider () {
        return new Object[][] {{new User(1L,new Address("Семашко",1,1,1),null,"Иванов Иванов","$2a$10$e7dQaxR.UBqcFUl0JW.1jOHmlxe3SYQ11byWVzHRe0y5T/9A2vvXa","y@t","3752980866235",new Role(1L,"Администратор")),1L},
                {new User(2L,new Address("Ленина",1,1,1),null,"курьер","$2a$10$e7dQaxR.UBqcFUl0JW.1jOHmlxe3SYQ11byWVzHRe0y5T/9A2vvXa","e@e","37589",new Role(2L,"Курьер")),2L},
                {new User(3L,new Address("Ленина",1,1,1),null,"пользователь","$2a$10$e7dQaxR.UBqcFUl0JW.1jOHmlxe3SYQ11byWVzHRe0y5T/9A2vvXa","mail@l","123",new Role(3L,"Пользователь")),3L},
                {new User(null,null,null,null,null,null,null,null),5L},
                {new User(null,null,null,null,null,null),5L},
                {new User(null,null,null,null,null,null,null),6L},
                {new User(null,null,null,null,null,null,null,null),7L}};
    }


    @DataProvider(name = "UsersAsObjectEmails")
    public Object[][] usersEmailProvider () {
        return new Object[][] {{new User(1L,new Address("Семашко",1,1,1),new Order(null,null,null,null,null),"Иванов Иванов","$2a$10$e7dQaxR.UBqcFUl0JW.1jOHmlxe3SYQ11byWVzHRe0y5T/9A2vvXa","y@t","3752980866235",new Role(1L,"Администратор")),"y@t"},
                {new User(null,null,null,null,null,null,null,null),"wehff@dsv"},
                {new User(null,null,null,null,null,null),""},
                {new User(null,null,null,null,null,null,null),"false"},
                {new User(null,null,null,null,null,null,null,null),""}};
    }


    @DataProvider(name = "UsersPasswordAndEmail")
    public Object[][] usersEmailPasswordProvider () {
        return new Object[][] {{"y@t","qwerty",true},
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
        return new Object[][] {{"y@t",true},
                {"e@e",true},
                {"mail@l",true},
                {"adfdfsa",false},
                {"",false},
                {null,false}
        };
    }
    @DataProvider(name = "UsersForUpdate")
    public Object[]updateProvider () {
        return new Object[] {
                new User(new Address(1L,"Ленина",1,1,1),new Order(null,null,null,null,null),"Иванов иванов","$2a$10$81fulal82SJbTKdYgbPlyeBEi/MzIztT/9//vcPTiCsvRssYHO8/2","r@t","375893",new Role(3L,"Пользователь")),
                new User(new Address(2L,"Семашко",1,1,1),new Order(null,null,null,null,null),"Иванов иванов","$2a$10$81fulal82SJbTKdYgbPlyeBEi/MzIztT/9//vcPTiCsvRssYHO8/2","r@t","3758931",new Role(3L,"Пользователь")),
        };
    }
    @DataProvider(name = "InvalidUserForCreateOrUpdate")
    public Object[] invalidUserProviderForCreation () {
        return new Object[] {
                new User(null,null,null,null,null,null,null),
                new User(null,"курьер","$2a$10$e7dQaxR.UBqcFUl0JW.1jOHmlxe3SYQ11byWVzHRe0y5T/9A2vvXa","e@e","37589",new Role(2L,"Курьер")),
                new User(new Address("Ленина",1,1,1),null,"$2a$10$e7dQaxR.UBqcFUl0JW.1jOHmlxe3SYQ11byWVzHRe0y5T/9A2vvXa","e@e","37589",new Role(2L,"Курьер")),
                new User(new Address("Ленина",1,1,1),"курьер","$2a$10$e7dQaxR.UBqcFUl0JW.1jOHmlxe3SYQ11byWVzHRe0y5T/9A2vvXa",null,"37589",new Role(2L,"Курьер")),
                new User(new Address("Ленина",1,1,1),"курьер","$2a$10$e7dQaxR.UBqcFUl0JW.1jOHmlxe3SYQ11byWVzHRe0y5T/9A2vvXa","e@e",null,new Role(2L,"Курьер")),
                new User(new Address("Ленина",1,1,1),"курьер","$2a$10$e7dQaxR.UBqcFUl0JW.1jOHmlxe3SYQ11byWVzHRe0y5T/9A2vvXa","e@e","37589",null)
        };
    }



    @Test(dataProvider = "Users",priority = 3)
    public void findByIdTest (User user, Long id) {
        Assert.assertEquals(userService.findEntityById(id),user);
    }

    @Test(dataProvider = "UsersAsObjectEmails",priority = 3)
    public void findByEmail (User user,String email) {
        Assert.assertEquals(userService.findByEmail(email),user);
    }

    @Test(dataProvider = "UsersPasswordAndEmail" ,priority = 3)
    public void checkLoginAndPasswordTest (String email,String password,Boolean flag) {
        Assert.assertEquals(userService.checkLoginPassword(email,password),flag);
    }
    @Test(dataProvider = "InvalidUsersPasswordAndEmail", expectedExceptions = {NullPointerException.class},priority = 3)
    public void checkNullLoginAndPasswordTest (String email,String password) {
        userService.checkLoginPassword(email,password);
    }

    @Test(dataProvider = "UsersEmails", priority = 3)
    public void checkEmailTest (String email,Boolean flag) {
        Assert.assertEquals(userService.checkEmail(email),flag);
    }

    @Test(priority = 3)
    public void checkListOfNotAdmin()  {
        List<User> usersList = new ArrayList<>();
        usersList.add(new User(3L,new Address("Ленина",1,1,1),new Order(new ArrayList<>(),new ArrayList<>(),false,new Timestamp(123,4,21,16,14,32,0),"наличные"),"пользователь","$2a$10$e7dQaxR.UBqcFUl0JW.1jOHmlxe3SYQ11byWVzHRe0y5T/9A2vvXa","mail@l","123",new Role(3L,"Пользователь")));
        usersList.add(new User(2L,new Address("Ленина",1,1,1),new Order(new ArrayList<>(),new ArrayList<>(),true,new Timestamp(123,4,21,16,14,48,0),"картой"),"курьер","$2a$10$e7dQaxR.UBqcFUl0JW.1jOHmlxe3SYQ11byWVzHRe0y5T/9A2vvXa","e@e","37589",new Role(2L,"Курьер")));
        usersList.add(new User(4L,new Address("Ленина",1,1,1),new Order(new ArrayList<>(),new ArrayList<>(),false,new Timestamp(123,4,21,16,14,32,0),"наличные"),"пользователь1","$2a$10$e7dQaxR.UBqcFUl0JW.1jOHmlxe3SYQ11byWVzHRe0y5T/9A2vvXa","m@m","1234",new Role(3L,"Пользователь")));
        Assert.assertEquals(userService.getAllNotAdmin().size(),3);
        for (int i = 0; i < userService.getAllNotAdmin().size(); i++) {
            Assert.assertEquals(userService.getAllNotAdmin().get(i).getEmail(), usersList.get(i).getEmail());
            Assert.assertEquals(userService.getAllNotAdmin().get(i).getOrder(), usersList.get(i).getOrder());
        }
    }

    @Test(priority = 3)
    public void checkListOfUndelivered () {
        Assert.assertEquals(userService.getUndeliveredOrdersForUsers().size(),2);
        User user = new User(2L,new Address("Ленина",1,1,1),new Order(new ArrayList<>(),new ArrayList<>(),true,new Timestamp(123,4,21,16,14,48,0),"картой"),"курьер","$2a$10$e7dQaxR.UBqcFUl0JW.1jOHmlxe3SYQ11byWVzHRe0y5T/9A2vvXa","e@e","37589",new Role(2L,"Курьер"));

        for (int i = 0; i < userService.getUndeliveredOrdersForUsers().size(); i++) {
            Assert.assertEquals(userService.getUndeliveredOrdersForUsers().get(i).getOrder().getStatus(), false);
        }
        Assert.assertNotEquals(user.getOrder().getStatus(),false);
    }

    @Test(dataProvider = "InvalidUserForCreateOrUpdate", expectedExceptions = {NullPointerException.class},priority = 3)
    public void invalidCreate (User user) {
        userService.createUser(user);
    }

    @Test(priority = 0)
    public void checkCreation() {
       Assert.assertEquals(userService.createUser(new User(new Address(1L,"Ленина",1,1,1),new Order(null,null,null,null,null),"пользователь2","qwerty","r@t","375893",new Role(3L,"Пользователь"))),userService.findByEmail("r@t"));
    }

    @Test(priority = 2)
    public void checkDelete () {
        Assert.assertEquals(userService.delete("r@t"),true);
    }

    @Test(dataProvider = "InvalidUserForCreateOrUpdate",priority = 3, expectedExceptions = {NullPointerException.class})
    public void editOrderInvalid (User user) {
        userService.editOrder(user);
    }

    @Test(dataProvider = "InvalidUserForCreateOrUpdate",priority = 3, expectedExceptions = {NullPointerException.class})
    public void editUserInvalid (User user) {
        userService.edit(1L,user);
        userService.edit(null,user);
    }

    @Test(dataProvider = "UsersForUpdate",priority = 1)
    public void checkEdit (User user) {
        User user1 = userService.findByEmail("r@t");
        Assert.assertEquals(userService.edit(user1.getUserId(), user),true);
        User user2 = userService.findByEmail("r@t");
        Assert.assertEquals(user2.getAddresses(),user.getAddresses());
        User user3 = userService.findByEmail("r@t");
        Assert.assertEquals(user3.getTelephone(),user.getTelephone());
        User user4 = userService.findByEmail("r@t");
        Assert.assertEquals(user4.getFirstName_lastName(),user.getFirstName_lastName());
    }

}

