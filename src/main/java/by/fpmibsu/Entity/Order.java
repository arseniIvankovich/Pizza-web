package by.fpmibsu.Entity;

import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Order extends Entity {
    private List<Drink> drinks;
    private List<Pizza> pizzas;
    private Boolean status;
    private Timestamp deliveryDate;
    private String paymentMethod;

    public Order(){}

    public Order(List<Drink> drinks, List<Pizza> pizzas, Boolean status, Timestamp deliveryDate, String paymentMethod) {
        this.drinks = drinks;
        this.pizzas = pizzas;
        this.status = status;
        this.deliveryDate = deliveryDate;
        this.paymentMethod = paymentMethod;
    }

    public Order(List<Drink> drinks, List<Pizza> pizzas, String paymentMethod) {
        this.drinks = drinks;
        this.pizzas = pizzas;
        this.paymentMethod = paymentMethod;
    }

    public Order(Long id, List<Drink> drinks, List<Pizza> pizzas, Boolean status, Timestamp deliveryDate, String paymentMethod) {
        super(id);
        this.drinks = drinks;
        this.pizzas = pizzas;
        this.status = status;
        this.deliveryDate = deliveryDate;
        this.paymentMethod = paymentMethod;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Timestamp getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Timestamp deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(drinks, order.drinks) && Objects.equals(pizzas, order.pizzas) && Objects.equals(status, order.status) && Objects.equals(deliveryDate, order.deliveryDate) && Objects.equals(paymentMethod, order.paymentMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(drinks, pizzas, status, deliveryDate, paymentMethod);
    }

    @Override
    public String toString() {
        return "Order{" +
                "drinks=" + drinks +
                ", pizzas=" + pizzas +
                ", status=" + status +
                ", deliveryDate=" + deliveryDate +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }
}
