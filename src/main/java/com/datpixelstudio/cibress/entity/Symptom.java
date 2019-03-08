package com.datpixelstudio.cibress.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Symptom {
    private int id;
    private LocalDateTime recorded;
    private String text;
    private byte intensity;
    private SymptomName symptomNameByIdSymptomName;
    private DayEntry dayEntryByIdDayEntry;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "recorded")
    public LocalDateTime getRecorded() {
        return recorded;
    }

    public void setRecorded(LocalDateTime recorded) {
        this.recorded = recorded;
    }

    @Basic
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "intensity")
    public byte getIntensity() {
        return intensity;
    }

    public void setIntensity(byte intensity) {
        this.intensity = intensity;
    }

    @ManyToOne
    @JoinColumn(name = "id_symptom_name", referencedColumnName = "id", nullable = false)
    public SymptomName getSymptomNameByIdSymptomName() {
        return symptomNameByIdSymptomName;
    }

    public void setSymptomNameByIdSymptomName(SymptomName symptomNameByIdSymptomName) {
        this.symptomNameByIdSymptomName = symptomNameByIdSymptomName;
    }

    @ManyToOne
    @JoinColumn(name = "id_day_entry", referencedColumnName = "id", nullable = false)
    public DayEntry getDayEntryByIdDayEntry() {
        return dayEntryByIdDayEntry;
    }

    public void setDayEntryByIdDayEntry(DayEntry dayEntryByIdDayEntry) {
        this.dayEntryByIdDayEntry = dayEntryByIdDayEntry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Symptom symptom = (Symptom) o;
        return id == symptom.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
