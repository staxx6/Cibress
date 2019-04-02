package com.datpixelstudio.cibress.controller;

import com.datpixelstudio.cibress.dao.*;
import com.datpixelstudio.cibress.entity.*;
import com.datpixelstudio.cibress.service.DayEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalTime;
import java.util.Optional;

/*
    This controller returns thymeleaf fragments via ajax.
    I think it's not a good design but don't know a better way
    to load fragments via JavaScript.
 */
@Controller
public class FragmentController {

    @Autowired
    DayEntryService dayEntryService;

    @Autowired
    SessionData sessionData;

    @Autowired
    UnitRepository unitRepository;

    @Autowired
    DishRepository dishRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    DishIngredientRepository dishIngredientRepository;

    @Autowired
    DayEntryDishRepository dayEntryDishRepository;

    @GetMapping("/newDayDish")
    public String newDayDish(@AuthenticationPrincipal User user, Model model) {
        DayEntryDish dayEntryDish = new DayEntryDish();
        dayEntryDish.setTimeRecorded(LocalTime.now());
        dayEntryDish.setUnit(unitRepository.findById(1L).get());
        dayEntryDish.setQuantityIngredient(1);

        dayEntryDish.setDish(dishRepository.findById(1L).get());

        long newId = dayEntryService.newDishEntry(sessionData.getLocalDate(), user, dayEntryDish);

        model.addAttribute("id", newId);
        model.addAttribute("dishEntry", dayEntryDish);

        return "fragments/dishRow :: dishRow";
    }

    @GetMapping("/newIngredient")
    public String newIngredient(Model model, long dishId) {
        DishIngredient dishIngredient = new DishIngredient();

        dishIngredient.setIngredient(ingredientRepository.findById(1L).get());
        dishIngredient.setUnit(unitRepository.findById(2L).get());
        dishIngredient.setQuantity(100);

        System.out.println("Looking for dish: " + dishId);
        Optional<DayEntryDish> dayEntryDish = dayEntryDishRepository.findById(dishId);
        if(dayEntryDish.isPresent()) {
            dishIngredient.setDish(dayEntryDish.get().getDish());
        } else {
            System.out.println("Dish not found!"); // TODO should throw an exception
        }

//        Optional<Dish> optionalDish = dishRepository.findById(dishId);
//        if(optionalDish.isPresent()) {
//            dishIngredient.setDish(optionalDish.get());
//        } else {
//            System.out.println("Dish not found!"); // TODO should throw an exception
//        }

        dishIngredientRepository.saveAndFlush(dishIngredient);

        model.addAttribute("ingredientData", dishIngredient);

        return "fragments/dishRow :: ingredient";
    }
}
