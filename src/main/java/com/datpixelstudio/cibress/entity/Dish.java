package com.datpixelstudio.cibress.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "dish")
public class Dish {
    private int id;
    private String name;
    private boolean publicView;
    private List<DayEntryDish> dayEntryDishesById;
    private AnonymousComment anonymousCommentByIdAnonymousComment;
    private List<DishIngredient> dishIngredient;

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

    @OneToMany(mappedBy = "dish")
    public List<DayEntryDish> getDayEntryDishesById() {
        return dayEntryDishesById;
    }

    public void setDayEntryDishesById(List<DayEntryDish> dayEntryDishesById) {
        this.dayEntryDishesById = dayEntryDishesById;
    }

    @ManyToOne
    @JoinColumn(name = "id_anonymous_comment", referencedColumnName = "id")
    public AnonymousComment getAnonymousCommentByIdAnonymousComment() {
        return anonymousCommentByIdAnonymousComment;
    }

    public void setAnonymousCommentByIdAnonymousComment(AnonymousComment anonymousCommentByIdAnonymousComment) {
        this.anonymousCommentByIdAnonymousComment = anonymousCommentByIdAnonymousComment;
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
}
