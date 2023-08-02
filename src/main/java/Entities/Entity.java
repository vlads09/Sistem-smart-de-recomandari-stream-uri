package Entities;

public class Entity {
    private final int id;
    private final String name;

    protected Entity(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
