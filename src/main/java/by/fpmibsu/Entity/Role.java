package by.fpmibsu.Entity;

public class Role extends Entity {
    public void setRole(String role) {
        this.role = role;
    }

    private String role;

    public String getRole() {
        return role;
    }

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Role(Long roleID, String role) {
        super(roleID);
        this.role = role;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Role other = (Role) obj;
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        return true;
    }

    public String toString() {
        return "Role [role=" + role + "]";
    }
}