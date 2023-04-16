package by.fpmibsu.Dao.DaoImpl;
import by.fpmibsu.Services.Util;
import by.fpmibsu.Dao.OrderDao;
import by.fpmibsu.Entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class OrderDaoImpl extends Util implements OrderDao {
    Connection connection = getConnection();
    @Override
    public List<Order> findAll() throws SQLException {
        final String SQL_SELECT_ALL = "SELECT \"OrderID\", \"Status\", \"DeliveryDate\", \"PaymentMethod\"\n" +
                "\tFROM public.\"Order\";";
        final String SQL_INNER_1 = "SELECT \"DrinkID\",  \"NumberOfDrinks\"\n" +
                "\tFROM public.\"Drink_order\" WHERE \"OrderID\" = ?;";

        final String SQL_INNER_2 = "SELECT \"PizzaID\", \"NumberOfPizzas\"\n" +
                "\tFROM public.\"Pizza_Order\" WHERE \"OrderID\" = ?;";

        List<Order> orders = new ArrayList<>();

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("OrderID"));
                order.setStatus(resultSet.getBoolean("Status"));
                order.setDeliveryDate(resultSet.getDate("DeliveryDate"));
                order.setPaymentMethod(resultSet.getString("PaymentMethod"));

                Statement statement1 = null;
                Statement statement2 = null;
                try {
                    statement1 = connection.createStatement();
                    statement2 = connection.createStatement();

                    ResultSet resultSet1 = statement1.executeQuery(SQL_INNER_1);
                    ResultSet resultSet2 = statement2.executeQuery(SQL_INNER_2);

                    HashMap<Drink,Integer> drinks = new HashMap<>();
                    HashMap<Pizza,Integer> pizzas = new HashMap<>();

                    while (resultSet1.next())
                        drinks.put(new DrinkDaoImpl().findEntityById(resultSet1.getLong("DrinkID")),resultSet1.getInt("NumberOfDrinks"));


                    while (resultSet2.next())
                        pizzas.put(new PizzaDaoImpl().findEntityById(resultSet2.getLong("PizzaId")),resultSet2.getInt("NumberOfPizzas"));

                    order.setDrinks(drinks);
                    order.setPizzas(pizzas);
                }
                finally {
                    close(statement1);
                    close(statement2);
                }
                orders.add(order);
            }
        }
        finally {
            close(statement);
            close(connection);
        }
        return orders;
    }

    @Override
    public Order findEntityById(Long id) throws SQLException{
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        Order order = new Order();
        final String SQL_SELECT_BY_ID = "SELECT \"OrderID\", \"Status\", \"DeliveryDate\", \"PaymentMethod\"\n" +
                "\tFROM public.\"Order\" WHERE \"OrderID\" = ?;";

        final String SQL_INNER_1 = "SELECT \"DrinkID\",  \"NumberOfDrinks\"\n" +
                "\tFROM public.\"Drink_order\" WHERE \"OrderID\" = ?;";

        final String SQL_INNER_2 = "SELECT \"PizzaID\", \"NumberOfPizzas\"\n" +
                "\tFROM public.\"Pizza_Order\" WHERE \"OrderID\" = ?;";
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID);
            preparedStatement.setLong(1, id);
            preparedStatement1 = connection.prepareStatement(SQL_INNER_1);
            preparedStatement1.setLong(1, id);
            preparedStatement2 = connection.prepareStatement(SQL_INNER_2);
            preparedStatement2.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            ResultSet resultSet2 = preparedStatement2.executeQuery();

            while (resultSet.next()) {
                order.setId(resultSet.getLong("OrderID"));
                order.setStatus(resultSet.getBoolean("Status"));
                order.setDeliveryDate(resultSet.getDate("DeliveryDate"));
                order.setPaymentMethod(resultSet.getString("PaymentMethod"));

                HashMap<Drink,Integer> drinks = new HashMap<>();
                HashMap<Pizza,Integer> pizzas = new HashMap<>();

                while (resultSet1.next())
                    drinks.put(new DrinkDaoImpl().findEntityById(resultSet1.getLong("DrinkID")),resultSet1.getInt("NumberOfDrinks"));


                while (resultSet2.next())
                    pizzas.put(new PizzaDaoImpl().findEntityById(resultSet2.getLong("PizzaId")),resultSet2.getInt("NumberOfPizzas"));

                order.setDrinks(drinks);
                order.setPizzas(pizzas);
            }
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
        return order;
    }

    @Override
    public boolean delete(Order order) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Long id) throws SQLException {
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"Order\"\n" +
                "\tWHERE \"OrderID\" = ?;";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            return true;
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public boolean create(Order order) throws SQLException {
        final String SQL_CREATE_ADDRESS = "INSERT INTO public.\"Order\"(\n" +
                "\t\"Status\", \"DeliveryDate\", \"PaymentMethod\")\n" +
                "\tVALUES (?, ?, ?);";


        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_CREATE_ADDRESS);

            preparedStatement.setBoolean(1,order.getStatus());
            preparedStatement.setDate(2,  order.getDeliveryDate());
            preparedStatement.setString(3,order.getPaymentMethod());
            preparedStatement.executeUpdate();

            Long index = this.getLastID();

            for (Pizza pizza : order.getPizzas().keySet())
                addToMMPizza(index,pizza.getPizzaId(),order.getPizzas().get(pizza));

            for (Drink drink : order.getDrinks().keySet())
                addToMMDrink(index,drink.getDrinkID(),order.getDrinks().get(drink));

            return true;
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
    }

    @Override
    public void update(Order order) throws SQLException {
        final String SQL_UPDATE = "UPDATE public.\"Order\"\n" +
                "\tSET \"Status\"=?, \"DeliveryDate\"=?, \"PaymentMethod\"=?\n" +
                "\tWHERE \"OrderID\" = ?;";

        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(SQL_UPDATE);

            preparedStatement.setBoolean(1,order.getStatus());
            preparedStatement.setDate(2,  order.getDeliveryDate());
            preparedStatement.setString(3,order.getPaymentMethod());
            preparedStatement.setLong(4,order.getId());

            preparedStatement.executeUpdate();
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
    }

    private  Long getLastID () throws SQLException {
        final String SQL_LAST_ID = "SELECT \"OrderID\"\n" +
                "\tFROM public.\"Order\" ORDER BY \"OrderID\" DESC LIMIT 1;";
        PreparedStatement preparedStatement = null;
        Long index = 0L;
        try{
            preparedStatement = connection.prepareStatement(SQL_LAST_ID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                index = resultSet.getLong("OrderID");
            }
        }
        finally {
            close(preparedStatement);
        }
        return index;
    }


    @Override
    public void addToMMDrink(Long orderId, Long drinkId, Integer numberOfDrinks) throws SQLException {
        final String SQL_INNER_DRINK = "INSERT INTO public.\"Drink_order\"(\n" +
                "\t\"OrderID\", \"DrinkID\", \"NumberOfDrinks\")\n" +
                "\tVALUES ?, ?, ?);";

        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(SQL_INNER_DRINK);

            preparedStatement.setLong(1, orderId);
            preparedStatement.setLong(2, drinkId);
            preparedStatement.setInt(3,numberOfDrinks);
            preparedStatement.executeUpdate();

        }
        finally {
            close(preparedStatement);
            close(connection);
        }

    }

    @Override
    public void addToMMPizza(Long orderId, Long pizzaId, Integer numberOfPizzas) throws SQLException {
        final String SQL_INNER_PIZZA = "INSERT INTO public.\"Pizza_Order\"(\n" +
                "\t\"PizzaID\", \"OrderID\", \"NumberOfPizzas\")\n" +
                "\tVALUES (?, ?, ?);";

        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(SQL_INNER_PIZZA);

            preparedStatement.setLong(1, pizzaId);
            preparedStatement.setLong(2, orderId);
            preparedStatement.setInt(3,numberOfPizzas);
            preparedStatement.executeUpdate();

        }
        finally {
            close(preparedStatement);
            close(connection);
        }
    }
}