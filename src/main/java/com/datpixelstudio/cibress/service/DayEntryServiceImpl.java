package com.datpixelstudio.cibress.service;

import com.datpixelstudio.cibress.dao.*;
import com.datpixelstudio.cibress.dto.DayEntryDishDto;
import com.datpixelstudio.cibress.dto.DayEntryDto;
import com.datpixelstudio.cibress.dto.DishIngredientDto;
import com.datpixelstudio.cibress.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    UnitRepository unitRepository;

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

        List<Ingredient> dishIngredients = new ArrayList<>();

        // --- Save ingredients // TODO NO USER CHECK!
        if(dayEntryDishDto.getDishIngredientDtos() != null) {
            for(DishIngredientDto dishIngredientDto : dayEntryDishDto.getDishIngredientDtos()) {

                Ingredient ingredient = ingredientRepository.findByName(dishIngredientDto.getName());

                if(ingredient == null) {
                    ingredient = new Ingredient();
                    ingredient.setName(dishIngredientDto.getName());
                    ingredient.setPublicView(false);
                }
                ingredientRepository.saveAndFlush(ingredient);
                dishIngredients.add(ingredient);
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
        dishRepository.saveAndFlush(dish);

        toSaveEntryDish.setDish(dish);

        for(Ingredient ingredient : dishIngredients) {
            DishIngredient dishIngredient = null;
            try { // TODO srsly bad
                dishIngredient = dishIngredientRepository.findByDishAndIngredient(dish, ingredient);
            } catch (Exception ex) {
            }
            if(dishIngredient == null) {
                dishIngredient = new DishIngredient();
                dishIngredient.setDish(dish);
                dishIngredient.setIngredient(ingredient);
            }
            for(DishIngredientDto dishIngredientDto : dayEntryDishDto.getDishIngredientDtos()) {
                if(dishIngredientDto.getName().equals(ingredient.getName())) {
                    dishIngredient.setQuantity(dishIngredientDto.getQuantity());

                    Unit unit = unitRepository.findByName(dishIngredientDto.getUnit().getName());
                    if(unit == null) {
                        unit = new Unit();
                        unit.setName(dishIngredientDto.getUnit().getName());
                        unit.setShortName(true);
                        unitRepository.saveAndFlush(unit);
                    }

                    dishIngredient.setUnit(unit);
                }
            }
            dishIngredientRepository.saveAndFlush(dishIngredient);
        }

        dayEntryDishRepository.saveAndFlush(toSaveEntryDish);

        return toSaveEntryDish.getId();
    }

    @Override
    public boolean removeDishIngredient(User user, long dishIngredientId) {

        Optional<DishIngredient> dishIngredient = dishIngredientRepository.findById(dishIngredientId);
        if(dishIngredient.isPresent()) {
            dishIngredientRepository.delete(dishIngredient.get());
            return true;
        } else {
            System.out.println("Couldn't remove dishIngredient: " + dishIngredientId);
            return false;
        }
    }

    @Override
    public boolean removeDayEntryDish(User user, long dayEntryDishId) {

        Optional<DayEntryDish> dayEntryDish = dayEntryDishRepository.findById(dayEntryDishId);
        if(dayEntryDish.isPresent()) {
            dayEntryDishRepository.delete(dayEntryDish.get());
            return true;
        } else {
            System.out.println("Couldn't remove dayEntryDish: " + dayEntryDishId);
            return false;
        }
    }
}
