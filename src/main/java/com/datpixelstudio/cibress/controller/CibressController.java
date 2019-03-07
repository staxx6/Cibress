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

import java.util.Date;

@Controller
public class CibressController {

    @Autowired
    DayEntryService dayEntryService;

    @GetMapping("/today")
    public String getTodayDayEntry(@AuthenticationPrincipal User user, Model model) {

        DayEntryDto dayEntryDto = dayEntryService.findByDate(user, new Date());
        model.addAttribute("dayEntry", dayEntryDto);

        return "main";
    }

    @GetMapping("/day/{id}")
    public String getDayEntry(@PathVariable Long id, Model model) {
        return null;
    }
}
