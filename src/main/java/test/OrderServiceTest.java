package test;

import by.fpmibsu.Dao.HikariCPDataSource;
import by.fpmibsu.Entity.Drink;
import by.fpmibsu.Entity.Order;
import by.fpmibsu.Entity.Pizza;
import by.fpmibsu.Services.OrderService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.ArrayList;

public class OrderServiceTest {
    public OrderService orderService;
    @BeforeClass
    public void init() {
        HikariCPDataSource.rebase();
        orderService = new OrderService();
    }

    @DataProvider(name = "NullOrdersCreations")
    public Object[] nullOrderProvider () {
        return new Object[] {
                new Order(new ArrayList<Drink>(),null,true,new Timestamp(120000),""),
                new Order(null,new ArrayList<Pizza>(),true,new Timestamp(120000),""),
        };
    }

    @DataProvider(name = "OrderAndId")
    public Object[][] orderAndIdProvider () {
        return new Object[][]{
                {1L, new Order(new ArrayList<Drink>(), new ArrayList<Pizza>(), false, new Timestamp(123, 4, 21, 16, 14, 32, 0), "наличные")},
                {2L, new Order(new ArrayList<Drink>(), new ArrayList<Pizza>(), true, new Timestamp(123, 4, 21, 16, 14, 48, 0), "картой")},
                {31L,new Order(new ArrayList<Drink>(), new ArrayList<Pizza>(),false,new Timestamp(123, 4, 26, 20, 11, 11, 0),"наличные")},
                {3L,new Order(new ArrayList<Drink>(), new ArrayList<Pizza>(),false,new Timestamp(123, 4, 26, 14, 4, 6, 0),"")}
        };
    }

    @DataProvider(name = "UpdateOrder")
    public Object[] orderUpdateProvider () {
        return new  Object[] {
                new Order(10L,new ArrayList<Drink>(), new ArrayList<Pizza>(), true, new Timestamp(123, 4, 26, 16, 5, 21, 0), ""),
                new Order(11L,new ArrayList<Drink>(), new ArrayList<Pizza>(), true, new Timestamp(2023, 4, 26, 16, 5, 26, 0), "")
        };
    }

    @Test(dataProvider = "NullOrdersCreations",expectedExceptions = {NullPointerException.class})
    public void checkNullCreation (Order order) {
        orderService.createOrder(order);
    }

    @Test
    public void checkCreateOrder () {
        Order order = orderService.createOrder(new Order(new ArrayList<Drink>(),new ArrayList<Pizza>(),"наличные"));
        Assert.assertEquals(order.getStatus(),false);
        Assert.assertEquals(order.getPizzas(),new ArrayList<>());
        Assert.assertEquals(order.getDrinks(),new ArrayList<>());
        Assert.assertEquals(order.getPaymentMethod(),"наличные");
        Order order1 = orderService.createOrder(new Order(new ArrayList<Drink>(),new ArrayList<Pizza>(),false,new Timestamp(1200),"картой"));
        Assert.assertEquals(order1.getStatus(),false);
        Assert.assertEquals(order1.getPizzas(),new ArrayList<>());
        Assert.assertEquals(order1.getDrinks(),new ArrayList<>());
        Assert.assertEquals(order1.getPaymentMethod(),"картой");
    }

    @Test(dataProvider = "OrderAndId")
    public void checkFindById (Long id, Order order) {
        Assert.assertEquals(orderService.findEntityById(id),order);
    }

    @Test(expectedExceptions = {NullPointerException.class})
    public void nullCheckFindById () {
        orderService.findEntityById(null);
    }

    @Test(dataProvider = "UpdateOrder")
    public void checkUpdateStatus (Order order) {
        Order order1 = orderService.findEntityById(order.getId());
        Assert.assertEquals(orderService.update(order1),true);
        Order order2 = orderService.findEntityById(order.getId());
        Assert.assertNotEquals(order1.equals(order2),false);
    }
}
