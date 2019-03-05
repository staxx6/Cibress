package com.datpixelstudio.cibress.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "day_entry", schema = "cibress", catalog = "")
public class DayEntry {
    private int id;
    private Date entryRecord;
    private Users usersByUsername;
    private AnonymousComment anonymousCommentByIdAnonymousComment;
    private Collection<DayEntryDish> dayEntryDishesById;
    private Collection<Symptom> symptomsById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "entry_record")
    public Date getEntryRecord() {
        return entryRecord;
    }

    public void setEntryRecord(Date entryRecord) {
        this.entryRecord = entryRecord;
    }

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    public Users getUsersByUsername() {
        return usersByUsername;
    }

    public void setUsersByUsername(Users usersByUsername) {
        this.usersByUsername = usersByUsername;
    }

    @ManyToOne
    @JoinColumn(name = "id_anonymous_comment", referencedColumnName = "id")
    public AnonymousComment getAnonymousCommentByIdAnonymousComment() {
        return anonymousCommentByIdAnonymousComment;
    }

    public void setAnonymousCommentByIdAnonymousComment(AnonymousComment anonymousCommentByIdAnonymousComment) {
        this.anonymousCommentByIdAnonymousComment = anonymousCommentByIdAnonymousComment;
    }

    @OneToMany(mappedBy = "dayEntryByIdDayEntry")
    public Collection<DayEntryDish> getDayEntryDishesById() {
        return dayEntryDishesById;
    }

    public void setDayEntryDishesById(Collection<DayEntryDish> dayEntryDishesById) {
        this.dayEntryDishesById = dayEntryDishesById;
    }

    @OneToMany(mappedBy = "dayEntryByIdDayEntry")
    public Collection<Symptom> getSymptomsById() {
        return symptomsById;
    }

    public void setSymptomsById(Collection<Symptom> symptomsById) {
        this.symptomsById = symptomsById;
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
