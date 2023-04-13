package by.fpmibsu.Entity;

import java.util.Objects;

public class Drink extends Entity{
    private String name;
    private Double capacity;
    private Double price;

    public Drink(){}

    public Drink(String name, Double capacity, Double price) {
        this.name = name;
        this.capacity = capacity;
        this.price = price;
    }

    public Drink(Long drinkID, String name, Double capacity, Double price) {
        super(drinkID);
        this.name = name;
        this.capacity = capacity;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drink drink = (Drink) o;
        return Objects.equals(name, drink.name) && Objects.equals(capacity, drink.capacity) && Objects.equals(price, drink.price);
    }

    public Long getDrinkID() {
        return getId();
    }

    public void setDrinkID(Long drinkID) {
        setId(drinkID);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", price=" + price +
                '}';
    }
}