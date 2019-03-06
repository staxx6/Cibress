package com.datpixelstudio.cibress.controller;

import com.datpixelstudio.cibress.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {

    @GetMapping("/registration")
    public String registration( Model model) {
        UserDto userDto = new UserDto();
        userDto.setFirstName("FIRST NAME");
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/registration")
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDto accountDto,
            BindingResult result) {

//        User

        return null;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
