package com.datpixelstudio.cibress.controller;

import com.datpixelstudio.cibress.dao.DishRepository;
import com.datpixelstudio.cibress.dao.UnitRepository;
import com.datpixelstudio.cibress.entity.*;
import com.datpixelstudio.cibress.service.DayEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalTime;

/*
    This controller returns thymeleaf fragments via ajax.
    I think it's not a good design but I don't know a better way
    to load fragments via JavaScript
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

    @GetMapping("/newDayDish")
    public String newDayDish(@AuthenticationPrincipal User user, Model model) {
        DayEntryDish dayEntryDish = new DayEntryDish();
        dayEntryDish.setTimeRecorded(LocalTime.now());
        dayEntryDish.setUnit(unitRepository.findById(1L).get());
        dayEntryDish.setQuantityIngredient(1);

        dayEntryDish.setDish(dishRepository.findById(1L).get());

        long newId = dayEntryService.newDishEntry(sessionData.getLocalDate(), user, dayEntryDish);
        System.out.println("id for DishEntry: " + dayEntryDish.getId());

        model.addAttribute("id", newId); // TODO after 'add' dayEntryDish has a new id?
        model.addAttribute("dishEntry", dayEntryDish);

        return "fragments/dishRow :: dishRow";
    }

    @GetMapping("/newIngredient")
    public String newIngredient(Model model) {
        DishIngredient dishIngredient = new DishIngredient();

        Ingredient ingredient = new Ingredient();
        ingredient.setName("Enter a new ingredient!");
        ingredient.setPublicView(false);

        dishIngredient.setIngredient(ingredient);
        dishIngredient.setUnit(new Unit("g", true));
        dishIngredient.setQuantity(100);

        model.addAttribute("ingredientData", dishIngredient);

        return "fragments/dishRow :: ingredient";
    }
}
