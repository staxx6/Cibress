package com.datpixelstudio.cibress.service;

import com.datpixelstudio.cibress.dto.DayEntryDishDto;
import com.datpixelstudio.cibress.dto.DayEntryDto;
import com.datpixelstudio.cibress.entity.DayEntryDish;
import com.datpixelstudio.cibress.entity.User;

import java.time.LocalDate;

public interface DayEntryService {
    DayEntryDto findByDate(User user, LocalDate day);
    long newDishEntry(LocalDate date, User user, DayEntryDish dayEntryDish);
    long saveDayEntry(User user, DayEntryDto dayEntryDto);
    long saveDayEntryDish(User user, LocalDate localDate, DayEntryDishDto dayEntryDishDto);
    boolean removeDishIngredient(User user, long dishIngredientId);
    boolean removeDayEntryDish(User user, long dayEntryDish);
}
