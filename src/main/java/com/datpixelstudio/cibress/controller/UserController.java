package com.datpixelstudio.cibress.controller;

import com.datpixelstudio.cibress.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/registration")
    public String registration(Model model) {
        UserDto userDto = new UserDto();
        userDto.setFirstName("FIRST NAME");
        model.addAttribute("user", userDto);
        return "registration";
    }
}
