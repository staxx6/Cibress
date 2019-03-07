package com.datpixelstudio.cibress.service;

import com.datpixelstudio.cibress.dao.DayEntryRepository;
import com.datpixelstudio.cibress.dto.DayEntryDto;
import com.datpixelstudio.cibress.entity.AnonymousComment;
import com.datpixelstudio.cibress.entity.DayEntry;
import com.datpixelstudio.cibress.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DayEntryServiceImpl implements DayEntryService {

    @Autowired
    DayEntryRepository dayEntryRepository;

    @Override
    public DayEntryDto findByDate(User user, Date date) {

        DayEntryDto dayEntryDto = new DayEntryDto();
        DayEntry dayEntry = dayEntryRepository.findByUserAndEntryRecord(user, date);

        if(dayEntry == null) {
            dayEntryDto.setDate(date);
            dayEntry.setAnonymousComment();
            return dayEntryDto;
        }

        dayEntryDto.setDate(date);
        dayEntryDto.setDayEntryDishes(dayEntry.getDayEntryDishes());
        dayEntryDto.setSymptoms(dayEntry.getSymptoms());
        dayEntryDto.setAnonymousComment(dayEntry.getAnonymousComment());

        return dayEntryDto;
    }
}
