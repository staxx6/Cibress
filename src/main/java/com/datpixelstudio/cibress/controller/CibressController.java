package com.datpixelstudio.cibress.controller;

import com.datpixelstudio.cibress.dto.DayEntryDto;
import com.datpixelstudio.cibress.entity.User;
import com.datpixelstudio.cibress.service.DayEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@Controller
public class CibressController {

    @Autowired
    DayEntryService dayEntryService;

    @GetMapping("/today")
    public String getTodayDayEntry(@AuthenticationPrincipal User user, Model model) {

//        System.out.println("Im doing something /today controller");

//        LocalDate dateWithData = LocalDate.of(2019,3, 10);
        DayEntryDto dayEntryDto = dayEntryService.findByDate(user, LocalDate.now()); // LocalDate.now()
//        System.out.println(dayEntryDto);
        model.addAttribute("dayEntry", dayEntryDto);

        return "main";
    }

    /*
        Example: http://localhost:8080/day?year=2019&month=3&day=7
     */
    @GetMapping("/day")
    public String getDayEntry(int year, int month, int day,
              @AuthenticationPrincipal User user, Model model) {

        LocalDate date = LocalDate.of(year, month, day);
        DayEntryDto dayEntryDto = dayEntryService.findByDate(user, date);
        model.addAttribute("dayEntry", dayEntryDto);

        return "main";
    }
}
