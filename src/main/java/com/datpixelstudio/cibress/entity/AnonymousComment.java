package com.datpixelstudio.cibress.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "anonymous_comment", schema = "cibress", catalog = "")
public class AnonymousComment {
    private long id;
    private String text;
    private Collection<DayEntry> dayEntries;
    private Collection<Dish> dishes;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
    public Collection<DayEntry> dayEntries() {
        return dayEntries;
    }

    public void setDayEntries(Collection<DayEntry> dayEntriesById) {
        this.dayEntries = dayEntries;
    }

    @OneToMany(mappedBy = "anonymousComment")
    public Collection<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Collection<Dish> dishesById) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "AnonymousComment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
