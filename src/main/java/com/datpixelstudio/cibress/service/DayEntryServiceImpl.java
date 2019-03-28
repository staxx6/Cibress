package com.datpixelstudio.cibress.service;

import com.datpixelstudio.cibress.dao.DayEntryDishRepository;
import com.datpixelstudio.cibress.dao.DayEntryRepository;
import com.datpixelstudio.cibress.dao.DishRepository;
import com.datpixelstudio.cibress.dto.DayEntryDto;
import com.datpixelstudio.cibress.entity.AnonymousComment;
import com.datpixelstudio.cibress.entity.DayEntry;
import com.datpixelstudio.cibress.entity.DayEntryDish;
import com.datpixelstudio.cibress.entity.User;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class DayEntryServiceImpl implements DayEntryService {

    @Autowired
    DayEntryRepository dayEntryRepository;

    @Autowired
    DayEntryDishRepository dayEntryDishRepository;

    @Autowired
    DishRepository dishRepository;

    @Override
    public DayEntryDto findByDate(User user, LocalDate date) {

        DayEntryDto dayEntryDto = new DayEntryDto();
        DayEntry dayEntry = dayEntryRepository.findByUserAndEntryRecord(user, date);

        if(dayEntry == null) {
            // Create a empty DTO
            dayEntryDto.setDate(date);
            AnonymousComment cmt = new AnonymousComment();
            cmt.setText("A comment for the dish"); // TODO old? should be here?
            dayEntryDto.setAnonymousComment(cmt);

                // dont save empty day entries
//            long newId = saveDayEntry(user, dayEntryDto);
//            dayEntryDto.setId(newId);

            return dayEntryDto;
        }

        // DayEntry data found transfer data to DTO

        //  Got confused here, not sure if needed the id here
        dayEntryDto.setId(dayEntry.getId()); // TODO what if new date? see if==null statement

        dayEntryDto.setDate(date);
        dayEntryDto.setDayEntryDishes(dayEntry.getDayEntryDishes());
        dayEntryDto.setSymptoms(dayEntry.getSymptoms());
        dayEntryDto.setAnonymousComment(dayEntry.getAnonymousComment());

        return dayEntryDto;
    }

    @Override
    public long saveDayEntry(User user, DayEntryDto dayEntryDto) {


        return -1;
    }

    /*
        1. save the dish
        2. save the dayEntry
        3. save the dayEntryDish
     */
    @Override
    public long newDishEntry(String stringDate, User user, DayEntryDish dayEntryDish) {

        // dish
        dishRepository.save(dayEntryDish.getDish());
//        Long dishId = dayEntryDish.getDish().getId();

        // dayEntry
        LocalDate date = stringDateToLocalDate(stringDate);

        DayEntry dayEntry = dayEntryDish.getDayEntry();
        dayEntry.setEntryRecord(date);
        dayEntryRepository.save(dayEntryDish.getDayEntry());
//        Long dayEntryId = dayEntryDish.getDayEntry().getId();

        // dayEntryDish
        dayEntryDishRepository.save(dayEntryDish);

        return dayEntryDish.getId();
    }

    private LocalDate stringDateToLocalDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        return LocalDate.parse(dateString, formatter);
    }
}
