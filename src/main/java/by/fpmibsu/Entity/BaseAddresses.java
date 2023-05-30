package by.fpmibsu.Entity;

import java.util.Objects;

public class BaseAddresses extends Entity {
    private String street;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public BaseAddresses(String street) {
        this.street = street;
    }

    public BaseAddresses(Long id, String street) {
        super(id);
        this.street = street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseAddresses that = (BaseAddresses) o;
        return Objects.equals(street, that.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street);
    }

    @Override
    public String toString() {
        return "BaseAddresses{" +
                "street='" + street + '\'' +
                '}';
    }
}
