package com.datpixelstudio.cibress.controller;

import com.datpixelstudio.cibress.calender.Month;
import com.datpixelstudio.cibress.dto.DayEntryDto;
import com.datpixelstudio.cibress.entity.User;
import com.datpixelstudio.cibress.service.DayEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class CibressController {

    @Autowired
    DayEntryService dayEntryService;

    @Autowired
    SessionData sessionData;

    @GetMapping("/today")
    public String getTodayDayEntry(@AuthenticationPrincipal User user, Model model) {

        LocalDate localDate = LocalDate.now();
        sessionData.setLocalDate(localDate);

//        LocalDate dateWithData = LocalDate.of(2019,3, 10);
        DayEntryDto dayEntryDto = dayEntryService.findByDate(user, localDate); // LocalDate.now()
        model.addAttribute("dayEntry", dayEntryDto);

        // Calender
        Month month = new Month(user, LocalDate.now(), dayEntryService);
        model.addAttribute("month", month);

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
