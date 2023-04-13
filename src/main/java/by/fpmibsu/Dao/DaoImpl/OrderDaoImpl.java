package by.fpmibsu.Dao.DaoImpl;
import by.fpmibsu.Services.Util;
import by.fpmibsu.Dao.OrderDao;
import by.fpmibsu.Entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class OrderDaoImpl extends Util implements OrderDao {
    Connection connection = getConnection();
    @Override
    public List<Order> findAll() {
        final String SQL_SELECT_ALL = "SELECT \"OrderID\", \"Status\", \"DeliveryDate\", \"PaymentMethod\"\n" +
                "\tFROM public.\"Order\";";
        final String SQL_INNER_1 = "SELECT \"DrinkID\"\n" +
                "\tFROM public.\"Drink_order\" WHERE \"OrderID\" = ?;";

        final String SQL_INNER_2 = "SELECT \"PizzaID\"\n" +
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

                    ArrayList<Drink> drinks = new ArrayList<>();
                    ArrayList<Pizza> pizzas = new ArrayList<>();

                    while (resultSet2.next())
                        pizzas.add(new PizzaDaoImpl().findEntityById(resultSet2.getLong("PizzaId")));

                    while (resultSet1.next())
                        drinks.add(new DrinkDaoImpl().findEntityById(resultSet1.getLong("DrinkID")));

                    order.setDrinks(drinks);
                    order.setPizzas(pizzas);
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                finally {
                    close(statement1);
                    close(statement2);
                }
                orders.add(order);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close(statement);
            close(connection);
        }
        return orders;
    }

    @Override
    public Order findEntityById(Long id) {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        Order order = new Order();
        final String SQL_SELECT_BY_ID = "SELECT \"OrderID\", \"Status\", \"DeliveryDate\", \"PaymentMethod\"\n" +
                "\tFROM public.\"Order\" WHERE \"OrderID\" = ?;";

        final String SQL_INNER_1 = "SELECT \"DrinkID\"\n" +
                "\tFROM public.\"Drink_order\" WHERE \"OrderID\" = ?;";

        final String SQL_INNER_2 = "SELECT \"PizzaID\"\n" +
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

                ArrayList<Drink> drinks = new ArrayList<>();
                ArrayList<Pizza> pizzas = new ArrayList<>();

                while (resultSet2.next())
                    pizzas.add(new PizzaDaoImpl().findEntityById(resultSet2.getLong("PizzaID")));

                while (resultSet1.next())
                    drinks.add(new DrinkDaoImpl().findEntityById(resultSet1.getLong("DrinkID")));

                order.setDrinks(drinks);
                order.setPizzas(pizzas);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
        return order;
    }

    @Override
    public boolean delete(Order order) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        final String SQL_DELETE_BY_ID = "DELETE FROM public.\"Order\"\n" +
                "\tWHERE \"OrderID\" = ?;";
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_BY_ID);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return false;
    }

    @Override
    public boolean create(Order order) {
        final String SQL_CREATE_ADDRESS = "INSERT INTO public.\"Order\"(\n" +
                "\t\"Status\", \"DeliveryDate\", \"PaymentMethod\")\n" +
                "\tVALUES (?, ?, ?);";

        final String SQL_INNER_PIZZA = "INSERT INTO public.\"Pizza_Order\"(\n" +
                "\t\"PizzaID\", \"OrderID\")\n" +
                "\tVALUES (?, ?);";

        final String SQL_INNER_DRINK = "INSERT INTO public.\"Drink_order\"(\n" +
                "\t \"OrderID\", \"DrinkID\")\n" +
                "\tVALUES (?, ?);";

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;

        try {
            preparedStatement = connection.prepareStatement(SQL_CREATE_ADDRESS);

            preparedStatement.setBoolean(1,order.getStatus());
            preparedStatement.setDate(2,  order.getDeliveryDate());
            preparedStatement.setString(3,order.getPaymentMethod());
            preparedStatement.executeUpdate();
            Long index = this.getLastID();
            ArrayList<Drink> drinks = order.getDrinks();
            for (Drink drink : drinks) {
                preparedStatement1 = connection.prepareStatement(SQL_INNER_DRINK);
                preparedStatement1.setLong(1, index);
                preparedStatement1.setLong(2, drink.getDrinkID());
                preparedStatement1.executeUpdate();
            }

            ArrayList<Pizza> pizzas = order.getPizzas();
            for (Pizza pizza : pizzas) {
                preparedStatement2 = connection.prepareStatement(SQL_INNER_PIZZA);
                preparedStatement2.setLong(1,pizza.getPizzaId());
                preparedStatement2.setLong(2,index);
                preparedStatement2.executeUpdate();
            }


            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
        return false;
    }

    @Override
    public void update(Order order) {
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
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close(preparedStatement);
            close(connection);
        }
    }

    private Long getLastID () {
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
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            close(preparedStatement);
        }
        return index;
    }
}