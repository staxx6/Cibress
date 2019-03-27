package com.datpixelstudio.cibress.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Ingredient {
    private int id;
    private String name;
    private boolean publicView;
    private Collection<DishIngredient> dishes;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "public_view")
    public boolean getPublicView() {
        return publicView;
    }

    public void setPublicView(boolean publicView) {
        this.publicView = publicView;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return id == that.id &&
                publicView == that.publicView &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, publicView);
    }

    @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY)
    public Collection<DishIngredient> getDishes() {
        return dishes;
    }

    public void setDishes(Collection<DishIngredient> dishes) {
        this.dishes = dishes;
    }
}
