package com.datpixelstudio.cibress.dto;

import com.datpixelstudio.cibress.entity.AnonymousComment;
import com.datpixelstudio.cibress.entity.DayEntryDish;
import com.datpixelstudio.cibress.entity.Symptom;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public class DayEntryDto {

    @NotNull
    private Date date;

    private List<DayEntryDish> dayEntryDishes;
    private List<Symptom> symptoms;
    private AnonymousComment anonymousComment;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<DayEntryDish> getDayEntryDishes() {
        return dayEntryDishes;
    }

    public void setDayEntryDishes(List<DayEntryDish> dayEntryDishes) {
        this.dayEntryDishes = dayEntryDishes;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public AnonymousComment getAnonymousComment() {
        return anonymousComment;
    }

    public void setAnonymousComment(AnonymousComment anonymousComment) {
        this.anonymousComment = anonymousComment;
    }
}
