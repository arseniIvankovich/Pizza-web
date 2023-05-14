package by.fpmibsu.Entity;

public class BaseAddresses extends Entity{
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
    public String toString() {
        return "BaseAddresses{" +
                "street='" + street + '\'' +
                '}';
    }
}
