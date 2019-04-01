package com.datpixelstudio.cibress.service;

import com.datpixelstudio.cibress.dao.*;
import com.datpixelstudio.cibress.dto.DayEntryDishDto;
import com.datpixelstudio.cibress.dto.DayEntryDto;
import com.datpixelstudio.cibress.entity.*;
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

    @Autowired
    AnonymousCommentRepository anonymousCommentRepository;

    @Autowired
    DishIngredientRepository dishIngredientRepository;

    @Autowired
    IngredientRepository ingredientRepository;

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
    public long newDishEntry(LocalDate localDate, User user, DayEntryDish dayEntryDish) {

        // --- dish
        dishRepository.saveAndFlush(dayEntryDish.getDish());
//        Long dishId = dayEntryDish.getDish().getId();

        // --- dayEntry
        DayEntry dayEntry = dayEntryRepository.findByUserAndEntryRecord(user, localDate);
        if(dayEntry == null) {
            dayEntry = new DayEntry();
            dayEntry.setUser(user);
            AnonymousComment cmt = anonymousCommentRepository.findById(1L);
            if(cmt == null) { // shouldn't happen
                cmt = new AnonymousComment();
                cmt.setText("Enter a new comment");
                anonymousCommentRepository.save(cmt);
            }
            dayEntry.setAnonymousComment(cmt);
        }
        dayEntry.setEntryRecord(localDate);
        dayEntryRepository.saveAndFlush(dayEntry); // TODO ID is not updated
        dayEntryDish.setDayEntry(dayEntry);

        // --- dayEntryDish
        System.out.println("DayEntryService: " + dayEntryDish);
        dayEntryDishRepository.saveAndFlush(dayEntryDish);

        return dayEntryDish.getId();
    }

    // TODO null handling by DB not found queries
    @Override
    public long saveDayEntryDish(User user, LocalDate localDate, DayEntryDishDto dayEntryDishDto) {

        // --- Is Id correct / user is authenticated?
        DayEntryDish toSaveEntryDish = dayEntryDishRepository.findById(dayEntryDishDto.getId()).get();

        if(toSaveEntryDish == null ||
                toSaveEntryDish.getDayEntry().getUser().getId() != user.getId()) {
            System.out.println("DayEntryService: Couldn't save dayEntryDish.");
            return -1; // TODO should throw an exception - User not allowed to save with this dayEntryDish (wrong id or user)
        }

        // -- 1. save ingredients // TODO NO USER CHECK!
        if(dayEntryDishDto.getDishIngredients() != null) {
            for(DishIngredient dishIngredient : dayEntryDishDto.getDishIngredients()) {
                // TODO nullptr here: 
                Ingredient ingredient = ingredientRepository.findByName(dishIngredient.getIngredient().getName());
                if(ingredient == null) {
                    ingredient = new Ingredient();
                    ingredient.setName(dishIngredient.getIngredient().getName());
                    ingredient.setPublicView(false);
                }
                dishIngredient.setIngredient(ingredient);
                ingredientRepository.saveAndFlush(ingredient);
            }
        }

        // --- Find Dish or create it // TODO NO USER CHECK!
        Dish dish = dishRepository.findByName(dayEntryDishDto.getDishName());
        if(dish == null) {
            dish = new Dish();
            dish.setName(dayEntryDishDto.getDishName());
            dish.setAnonymousComment(anonymousCommentRepository.findById(1L));
            dish.setPublicView(false);
        }
        dish.setDishIngredient(dayEntryDishDto.getDishIngredients());
        dishRepository.saveAndFlush(dish);

        toSaveEntryDish.setDish(dish);

        dayEntryDishRepository.saveAndFlush(toSaveEntryDish);

        // TODO unit is missing!

        return toSaveEntryDish.getId();
    }

    @Deprecated
    private LocalDate stringDateToLocalDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        return LocalDate.parse(dateString, formatter);
    }
}
