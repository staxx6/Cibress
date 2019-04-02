package com.datpixelstudio.cibress.controller;

import com.datpixelstudio.cibress.calender.Month;
import com.datpixelstudio.cibress.dto.DayEntryDishDto;
import com.datpixelstudio.cibress.dto.DayEntryDto;
import com.datpixelstudio.cibress.entity.User;
import com.datpixelstudio.cibress.service.DayEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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

        generateDayAndCalender(user, localDate, model);

        return "main";
    }

    /*
        Example: http://localhost:8080/day?year=2019&month=3&day=7
     */
    @GetMapping("/day")
    public String getDayEntry(int year, int month, int day,
              @AuthenticationPrincipal User user, Model model) {

        LocalDate localDate = LocalDate.of(year, month, day); // TODO handle wrong input
        sessionData.setLocalDate(localDate);

        generateDayAndCalender(user, localDate, model);

        return "main";
    }

    @PostMapping("/saveDishRow")
    @ResponseBody
    public DayEntryDishDto saveDishRow(@AuthenticationPrincipal User user, @RequestBody DayEntryDishDto dayEntryDishDto) {

        System.out.println("controller saveDishRow DTO: " + dayEntryDishDto);
        dayEntryService.saveDayEntryDish(user, sessionData.getLocalDate(), dayEntryDishDto);
        System.out.println("controller end /saveDishRow");

        return dayEntryDishDto;
    }

    private void generateDayAndCalender(User user, LocalDate localDate, Model model) {
        DayEntryDto dayEntryDto = dayEntryService.findByDate(user, localDate); // LocalDate.now()
        model.addAttribute("dayEntry", dayEntryDto);

        // Calender
        Month month = new Month(user, LocalDate.now(), dayEntryService);
        model.addAttribute("month", month);
    }

}
