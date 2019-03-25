package com.datpixelstudio.cibress.calender;

import com.datpixelstudio.cibress.dto.DayEntryDto;
import com.datpixelstudio.cibress.entity.DayEntry;
import com.datpixelstudio.cibress.entity.Symptom;
import com.datpixelstudio.cibress.entity.User;
import com.datpixelstudio.cibress.service.DayEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Should be this a service?
public class Month {

    private int month;
    private List<Day> days;

    DayEntryService dayEntryService;

    // TODO seriously heavy DB load with a query for every day
    public Month(User user, LocalDate date, DayEntryService service) {
        this.month = date.getMonthValue();
        this.dayEntryService = service;
        days = new ArrayList<>(35);

        for(int i = 1; i < date.lengthOfMonth(); i++) {
            LocalDate newDate = LocalDate.of(date.getYear(), month, i);

            DayEntryDto dayEntryDto =  dayEntryService.findByDate(user, newDate);
            List<Symptom> symptoms = dayEntryDto.getSymptoms();
            if(symptoms == null) {
                Day newDay = new Day(newDate, 0);
                if(newDate.isEqual(date)) {
                    newDay.setToday(true);
                }
                addDay(newDay);
                continue;
            }

            int a = 0;
            double intensity = 0;
            for(Symptom s : symptoms) {
                a++;
                intensity += s.getIntensity();
            }
            intensity = Math.round(intensity / a);

            Day newDay = new Day(newDate, (int) intensity);
            if(newDate.isEqual(date)) {
                newDay.setToday(true);
            }
            addDay(newDay);
        }
    }

    public void addDay(Day day) {
        days.add(day);
    }

    public Day getDay(int index) {
        return days.get(index);
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}