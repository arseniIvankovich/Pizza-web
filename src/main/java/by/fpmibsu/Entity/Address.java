package by.fpmibsu.Entity;

import java.util.Objects;

public class Address extends Entity{
    private String street;
    private String houseNumber;
    private Integer entrance;
    private Integer flatNumber;

    public Address() {}

    public Address(String street, String houseNumber, Integer entrance, Integer flatNumber) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.entrance = entrance;
        this.flatNumber = flatNumber;
    }

    public Address(Long addressID, String street, String houseNumber, Integer entrance, Integer flatNumber) {
        super(addressID);
        this.street = street;
        this.houseNumber = houseNumber;
        this.entrance = entrance;
        this.flatNumber = flatNumber;
    }

    public Long getAddressID() {
        return this.getId();
    }

    public void setAddressID(Long addressID) {
        this.setId(addressID);
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return this.houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
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