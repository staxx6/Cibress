package com.datpixelstudio.cibress.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "dish")
public class Dish {
    private long id;
    private String name;
    private boolean publicView;
    private List<DayEntryDish> dayEntryDishes;
    private AnonymousComment anonymousComment;
    private List<DishIngredient> dishIngredient;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    @OneToMany(mappedBy = "dish")
    public List<DayEntryDish> getDayEntryDishes() {
        return dayEntryDishes;
    }

    public void setDayEntryDishes(List<DayEntryDish> dayEntryDishes) {
        this.dayEntryDishes = dayEntryDishes;
    }

    @ManyToOne
    @JoinColumn(name = "id_anonymous_comment", referencedColumnName = "id")
    public AnonymousComment getAnonymousComment() {
        return anonymousComment;
    }

    public void setAnonymousComment(AnonymousComment anonymousComment) {
        this.anonymousComment = anonymousComment;
    }

    @OneToMany(mappedBy = "dish")
    public List<DishIngredient> getDishIngredient() {
        return dishIngredient;
    }

    public void setDishIngredient(List<DishIngredient> dishIngredient) {
        this.dishIngredient = dishIngredient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return id == dish.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publicView=" + publicView +
                ", anonymousComment=" + anonymousComment +
                '}';
    }
}
