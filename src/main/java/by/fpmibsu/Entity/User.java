package by.fpmibsu.Entity;

import java.util.Objects;

public class User extends Entity{


    private Address addresses;

    private Order order;

    private String firstName_lastName;

    private String password;

    private String Email;

    private String Telephone;

    private Role role;

    public User(){}

    public User(Address addresses, Order order, String firstName_lastName, String password, String email, String telephone, Role role) {
        this.addresses = addresses;
        this.order = order;
        this.firstName_lastName = firstName_lastName;
        this.password = password;
        Email = email;
        Telephone = telephone;
        this.role = role;
    }

    public User(Long userId, Address addresses, Order order, String firstName_lastName, String password, String email, String telephone, Role role) {
        super(userId);
        this.addresses = addresses;
        this.order = order;
        this.firstName_lastName = firstName_lastName;
        this.password = password;
        Email = email;
        Telephone = telephone;
        this.role = role;
    }

    public Long getUserId() {
        return this.getId();
    }

    public void setUserId(Long userId) {
        this.setId(userId);
    }

    public Address getAddresses() {
        return addresses;
    }

    public void setAddresses(Address addresses) {
        this.addresses = addresses;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getFirstName_lastName() {
        return firstName_lastName;
    }

    public void setFirstName_lastName(String firstName_lastName) {
        this.firstName_lastName = firstName_lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(addresses, user.addresses) && Objects.equals(order, user.order) && Objects.equals(firstName_lastName, user.firstName_lastName) && Objects.equals(password, user.password) && Objects.equals(Email, user.Email) && Objects.equals(Telephone, user.Telephone) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addresses, order, firstName_lastName, password, Email, Telephone, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "addresses=" + addresses +
                ", order=" + order +
                ", firstName_lastName='" + firstName_lastName + '\'' +
                ", password='" + password + '\'' +
                ", Email='" + Email + '\'' +
                ", Telephone='" + Telephone + '\'' +
                ", role=" + role +
                '}';
    }
}
