package com.datpixelstudio.cibress.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dish_ingredient", schema = "cibress", catalog = "")
public class DishIngredient {
    private int id;
    private Integer quantityIngredient;
    private Unit unit;
    private Dish dishByIdDish;
    private Ingredient ingredientByIdIngredient;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "quantity_ingredient")
    public Integer getQuantityIngredient() {
        return quantityIngredient;
    }

    public void setQuantityIngredient(Integer quantityIngredient) {
        this.quantityIngredient = quantityIngredient;
    }

    @OneToOne
    @JoinColumn(name = "id_unit", referencedColumnName = "id")
    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @ManyToOne
    @JoinColumn(name = "id_dish", referencedColumnName = "id", nullable = false)
    public Dish getDishByIdDish() {
        return dishByIdDish;
    }

    public void setDishByIdDish(Dish dishByIdDish) {
        this.dishByIdDish = dishByIdDish;
    }

    @ManyToOne
    @JoinColumn(name = "id_ingredient", referencedColumnName = "id", nullable = false)
    public Ingredient getIngredientByIdIngredient() {
        return ingredientByIdIngredient;
    }

    public void setIngredientByIdIngredient(Ingredient ingredientByIdIngredient) {
        this.ingredientByIdIngredient = ingredientByIdIngredient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishIngredient that = (DishIngredient) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
