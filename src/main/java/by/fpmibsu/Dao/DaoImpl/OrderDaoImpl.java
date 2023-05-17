package by.fpmibsu.Dao.DaoImpl;

import by.fpmibsu.Dao.HikariCPDataSource;
import by.fpmibsu.Dao.OrderDao;
import by.fpmibsu.Entity.Drink;
import by.fpmibsu.Entity.Order;
import by.fpmibsu.Entity.Pizza;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private DataSource dataSource;

    public OrderDaoImpl() {
        this.dataSource = HikariCPDataSource.getDataSource();
    }

    static final Logger orderDaoLogger = LogManager.getLogger(OrderDaoImpl.class);
    static final Logger rootLogger = LogManager.getRootLogger();

    @Override
    public Order findEntityById(Long id)  {
        Order order = new Order();
        final String SQL_SELECT_BY_ID = "SELECT \"OrderID\", \"Status\", \"DeliveryDate\", \"PaymentMethod\"\n" +
                "\tFROM public.\"Order\" WHERE \"OrderID\" = ?;";

        final String SQL_INNER_1 = "SELECT \"DrinkID\",  \"NumberOfDrinks\"\n" +
                "\tFROM public.\"Drink_order\" WHERE \"OrderID\" = ?;";

        final String SQL_INNER_2 = "SELECT \"PizzaID\", \"NumberOfPizzas\"\n" +
                "\tFROM public.\"Pizza_Order\" WHERE \"OrderID\" = ?;";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
             PreparedStatement preparedStatement1 = connection.prepareStatement(SQL_INNER_1);
             PreparedStatement preparedStatement2 = connection.prepareStatement(SQL_INNER_2)) {
            orderDaoLogger.info("Got connection to the db");
            preparedStatement.setLong(1, id);
            preparedStatement1.setLong(1, id);
            preparedStatement2.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            ResultSet resultSet2 = preparedStatement2.executeQuery();

            while (resultSet.next()) {
                order.setId(resultSet.getLong("OrderID"));
                order.setStatus(resultSet.getBoolean("Status"));
                order.setDeliveryDate(resultSet.getTimestamp("DeliveryDate"));
                order.setPaymentMethod(resultSet.getString("PaymentMethod"));

                List<Drink> drinks = new ArrayList<>();
                List<Pizza> pizzas = new ArrayList<>();

                while (resultSet1.next()) {
                    Drink drink = new DrinkDaoImpl().findEntityById(resultSet1.getLong("DrinkID"));
                    drink.setCounter(resultSet1.getInt("NumberOfDrinks"));
                    drinks.add(drink);
                }


                while (resultSet2.next()) {
                    Pizza pizza = new PizzaDaoImpl().findEntityById(resultSet2.getLong("PizzaId"));
                    pizza.setCounter(resultSet2.getInt("NumberOfPizzas"));
                    pizzas.add(pizza);
                }

                order.setDrinks(drinks);
                order.setPizzas(pizzas);
            }
        } catch (SQLException e) {
            rootLogger.error("Error: ",e);

        }
        return order;
    }

    @Override
    public boolean delete(Order order)  {
        return false;
    }

    @Override
    public boolean delete(Long id)  {
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"Order\"\n" +
                "\tWHERE \"OrderID\" = ?;";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID)) {
            orderDaoLogger.info("Got connection to the db");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Order create(Order order) {
        final String SQL_CREATE_ADDRESS = "INSERT INTO public.\"Order\"(\n" +
                "\t\"Status\", \"DeliveryDate\", \"PaymentMethod\")\n" +
                "\tVALUES (?, ?, ?);";


        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_ADDRESS)) {
            orderDaoLogger.info("Got connection to the db");
            preparedStatement.setBoolean(1, order.getStatus());
            preparedStatement.setTimestamp(2, order.getDeliveryDate());
            preparedStatement.setString(3, order.getPaymentMethod());
            preparedStatement.executeUpdate();

            Long index = this.getLastID(order.getStatus(),order.getDeliveryDate(),order.getPaymentMethod());

            for (Pizza pizza : order.getPizzas())
                addToMMPizza(index, pizza.getPizzaId(), pizza.getCounter());

            for (Drink drink : order.getDrinks())
                addToMMDrink(index, drink.getDrinkID(), drink.getCounter());

            order.setId(index);

        } catch (SQLException e) {
            rootLogger.error("Error: ",e);
        }
        return order;
    }

    @Override
    public void update(Order order)  {
        final String SQL_UPDATE = "UPDATE public.\"Order\"\n" +
                "\tSET \"Status\"=?, \"DeliveryDate\"=?, \"PaymentMethod\"=?\n" +
                "\tWHERE \"OrderID\" = ?;";


        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)) {

            preparedStatement.setBoolean(1, order.getStatus());
            preparedStatement.setTimestamp(2, order.getDeliveryDate());
            preparedStatement.setString(3, order.getPaymentMethod());
            preparedStatement.setLong(4, order.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Long getLastID(Boolean status,Timestamp deliveryDate,String paymentMethod)  {
        final String SQL_LAST_ID = "SELECT \"OrderID\" FROM public.\"Order\" WHERE \"Status\" = ? AND \"DeliveryDate\" = ? AND \"PaymentMethod\" = ? ORDER BY \"OrderID\" DESC LIMIT 1;";

        Long index = 0L;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_LAST_ID)) {
            orderDaoLogger.info("Got connection to the db");
            preparedStatement.setBoolean(1,status);
            preparedStatement.setTimestamp(2,deliveryDate);
            preparedStatement.setString(3,paymentMethod);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
                index = resultSet.getLong("OrderID");
        } catch (SQLException e) {
            rootLogger.error("Error: ",e);
        }
        return index;
    }


    @Override
    public void addToMMDrink(Long orderId, Long drinkId, Integer numberOfDrinks)  {
        final String SQL_INNER_DRINK = "INSERT INTO public.\"Drink_order\"(\n" +
                "\t\"OrderID\", \"DrinkID\", \"NumberOfDrinks\")\n" +
                "\tVALUES (?, ?, ?);";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INNER_DRINK)) {
            orderDaoLogger.info("Got connection to the db");
            preparedStatement.setLong(1, orderId);
            preparedStatement.setLong(2, drinkId);
            preparedStatement.setInt(3, numberOfDrinks);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            rootLogger.error("Error: ",e);
        }
    }

    @Override
    public void addToMMPizza(Long orderId, Long pizzaId, Integer numberOfPizzas) {
        final String SQL_INNER_PIZZA = "INSERT INTO public.\"Pizza_Order\"(\n" +
                "\t\"PizzaID\", \"OrderID\", \"NumberOfPizzas\")\n" +
                "\tVALUES (?, ?, ?);";


        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INNER_PIZZA)) {
            orderDaoLogger.info("Got connection to the db");
            preparedStatement.setLong(1, pizzaId);
            preparedStatement.setLong(2, orderId);
            preparedStatement.setInt(3, numberOfPizzas);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            rootLogger.error("Error: ",e);
        }
    }



}