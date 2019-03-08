package com.datpixelstudio.cibress.entity;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "day_entry", schema = "cibress", catalog = "")
public class DayEntry {
    private int id;
    private LocalDate entryRecord;
    private User user;
    private AnonymousComment anonymousComment;
    private List<DayEntryDish> dayEntryDishes;
    private List<Symptom> symptoms;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Basic
    @Column(name = "entry_record")
    public LocalDate getEntryRecord() {
        return entryRecord;
    }

    public void setEntryRecord(LocalDate entryRecord) {
        this.entryRecord = entryRecord;
    }

    @ManyToOne
    @JoinColumn(name = "id_anonymous_comment", referencedColumnName = "id")
    public AnonymousComment getAnonymousComment() {
        return anonymousComment;
    }

    public void setAnonymousComment(AnonymousComment anonymousCommentByIdAnonymousComment) {
        this.anonymousComment = anonymousCommentByIdAnonymousComment;
    }

    @OneToMany(mappedBy = "dayEntryByIdDayEntry")
    public List<DayEntryDish> getDayEntryDishes() {
        return dayEntryDishes;
    }

    public void setDayEntryDishes(List<DayEntryDish> dayEntryDishesById) {
        this.dayEntryDishes = dayEntryDishesById;
    }

    @OneToMany(mappedBy = "dayEntryByIdDayEntry")
    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptomsById) {
        this.symptoms = symptomsById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayEntry dayEntry = (DayEntry) o;
        return id == dayEntry.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
