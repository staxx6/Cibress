package com.datpixelstudio.cibress.calender;

import java.time.LocalDate;

public class Day {

    private LocalDate date;
    private int intensity;
    private boolean today = false;

    public Day(LocalDate date, int intensity) {
        this.date = date;
        this.intensity = intensity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate day) {
        this.date = day;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public boolean isToday() {
        return today;
    }

    public void setToday(boolean today) {
        this.today = today;
    }

    @Override
    public String toString() {
        return "Day{" +
                "date=" + date +
                ", intensity=" + intensity +
                ", today=" + today +
                '}';
    }
}
