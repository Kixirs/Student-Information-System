package model;

import java.util.Objects;

public abstract class BaseEntity {
    private final long id;
    private String name;

    protected BaseEntity(long id, string name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    } 
}