package com.datpixelstudio.cibress.service;

import com.datpixelstudio.cibress.dto.DayEntryDto;
import com.datpixelstudio.cibress.entity.DayEntryDish;
import com.datpixelstudio.cibress.entity.User;

import java.time.LocalDate;

public interface DayEntryService {
    DayEntryDto findByDate(User user, LocalDate day);
    void addDish(DayEntryDish dayEntryDish);
}
