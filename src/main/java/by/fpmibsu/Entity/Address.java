package by.fpmibsu.Entity;

import java.util.Objects;

public class Address extends Entity{
    private String street;
    private Integer houseNumber;
    private Integer entrance;
    private Integer flatNumber;

    public Address() {}

    public Address(String street, Integer houseNumber, Integer entrance, Integer flatNumber) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.entrance = entrance;
        this.flatNumber = flatNumber;
    }

    public Address(Long id, String street, Integer houseNumber, Integer entrance, Integer flatNumber) {
        super(id);
        this.street = street;
        this.houseNumber = houseNumber;
        this.entrance = entrance;
        this.flatNumber = flatNumber;
    }


    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouseNumber() {
        return this.houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Integer getEntrance() {
        return this.entrance;
    }

    public void setEntrance(Integer entrance) {
        this.entrance = entrance;
    }

    public Integer getFlatNumber() {
        return this.flatNumber;
    }

    public void setFlatNumber(Integer flatNumber) {
        this.flatNumber = flatNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(houseNumber, address.houseNumber) && Objects.equals(entrance, address.entrance) && Objects.equals(flatNumber, address.flatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, houseNumber, entrance, flatNumber);
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                ", entrance=" + entrance +
                ", flatNumber=" + flatNumber +
                '}';
    }
}