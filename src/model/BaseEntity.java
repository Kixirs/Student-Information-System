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

    @Override
    public boolean equals(Object obt) {
        if(this == obt) 
            return true;
        if(obt == null || getClass() != obt.getClass())
            return false;
        BaseEntity that = (BaseEntity) obt;
            return id == that.id;
    }

    @Override
    public int hashCode() {
        return long.hashCode(id);
    }
}