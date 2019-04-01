package com.datpixelstudio.cibress.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dish_ingredient", schema = "cibress", catalog = "")
public class DishIngredient {
    private long id;
    private Integer quantity;
    private Unit unit;
    private Dish dish;
    private Ingredient ingredient;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "quantity_ingredient")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    @ManyToOne
    @JoinColumn(name = "id_ingredient", referencedColumnName = "id", nullable = false)
    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        return "DishIngredient{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", unit=" + unit +
                ", dish=" + dish +
                ", ingredient=" + ingredient +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
