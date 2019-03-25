package com.datpixelstudio.cibress.controller;

import com.datpixelstudio.cibress.entity.DayEntryDish;
import com.datpixelstudio.cibress.entity.Dish;
import com.datpixelstudio.cibress.entity.Unit;
import com.datpixelstudio.cibress.service.DayEntryService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/newDayDish")
    public String newDayDish(Model model) {
        DayEntryDish dayEntryDish = new DayEntryDish();
        dayEntryDish.setTimeRecorded(LocalTime.now());
        dayEntryDish.setUnit(new Unit("Portion", false));
        dayEntryDish.setQuantityIngredient(1);

        Dish dish = new Dish();
        dish.setName("Empty");
        dayEntryDish.setDish(dish);

        dayEntryService.addDish(dayEntryDish);

        model.addAttribute("id", 0);
        model.addAttribute("dishEntry", dayEntryDish);

        return "fragments/dishRow :: dishRow";
    }
}
