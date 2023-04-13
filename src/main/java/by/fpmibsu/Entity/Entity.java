package by.fpmibsu.Entity;

public abstract class Entity {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Entity () {}
    public Entity(Long id) {
        this.id = id;
    }
}
