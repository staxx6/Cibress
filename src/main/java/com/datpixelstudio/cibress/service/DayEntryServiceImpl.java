package com.datpixelstudio.cibress.service;

import com.datpixelstudio.cibress.dao.DayEntryRepository;
import com.datpixelstudio.cibress.dto.DayEntryDto;
import com.datpixelstudio.cibress.entity.AnonymousComment;
import com.datpixelstudio.cibress.entity.DayEntry;
import com.datpixelstudio.cibress.entity.DayEntryDish;
import com.datpixelstudio.cibress.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DayEntryServiceImpl implements DayEntryService {

    @Autowired
    DayEntryRepository dayEntryRepository;

    @Override
    public DayEntryDto findByDate(User user, LocalDate date) {

        DayEntryDto dayEntryDto = new DayEntryDto();
        DayEntry dayEntry = dayEntryRepository.findByUserAndEntryRecord(user, date);

        if(dayEntry == null) {
            dayEntryDto.setDate(date);
            AnonymousComment cmt = new AnonymousComment();
            cmt.setText("A comment for the dish");
            dayEntryDto.setAnonymousComment(cmt);
            return dayEntryDto;
        }

        //  Got confused here, not sure if needed the id here
        dayEntryDto.setId(dayEntry.getId()); // TODO what if new date? see if==null statement

        dayEntryDto.setDate(date);
        dayEntryDto.setDayEntryDishes(dayEntry.getDayEntryDishes());
        dayEntryDto.setSymptoms(dayEntry.getSymptoms());
        dayEntryDto.setAnonymousComment(dayEntry.getAnonymousComment());

        return dayEntryDto;
    }

    @Override
    public void addDish(DayEntryDish dayEntryDish) {
        // TODO
    }
}
