package com.datpixelstudio.cibress.entity;

import javax.persistence.*;

@Entity
@Table(name = "unit", schema = "cibress")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "short_name")
    private boolean shortName;

    public Unit() {}

    public Unit(String name, boolean shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isShortName() {
        return shortName;
    }

    public void setShortName(boolean shortName) {
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortName=" + shortName +
                '}';
    }
}
