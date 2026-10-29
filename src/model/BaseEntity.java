package model;

import java.util.Objects;

public abstract class BaseEntity {
    private final long id;
    private String name;

    protected BaseEntity(long id, String name) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) 
            return true;
        if (o == null || getClass() != o.getClass()) 
            return false;
        BaseEntity that = (BaseEntity) o;
            return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}