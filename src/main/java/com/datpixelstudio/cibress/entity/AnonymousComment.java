package com.datpixelstudio.cibress.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "anonymous_comment", schema = "cibress", catalog = "")
public class AnonymousComment {
    private int id;
    private String text;
    private Collection<DayEntry> dayEntriesById;
    private Collection<Dish> dishesById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnonymousComment that = (AnonymousComment) o;
        return id == that.id &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }

    @OneToMany(mappedBy = "anonymousComment")
    public Collection<DayEntry> getDayEntriesById() {
        return dayEntriesById;
    }

    public void setDayEntriesById(Collection<DayEntry> dayEntriesById) {
        this.dayEntriesById = dayEntriesById;
    }

    @OneToMany(mappedBy = "anonymousCommentByIdAnonymousComment")
    public Collection<Dish> getDishesById() {
        return dishesById;
    }

    public void setDishesById(Collection<Dish> dishesById) {
        this.dishesById = dishesById;
    }
}
