package com.datpixelstudio.cibress.controller;

import com.datpixelstudio.cibress.dto.UserDto;
import com.datpixelstudio.cibress.entity.User;
import com.datpixelstudio.cibress.exception.EmailExistsException;
import com.datpixelstudio.cibress.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    UserService userService;

    @GetMapping("/registration")
    public String registration( Model model) {
        UserDto userDto = new UserDto();

        // TODO: REMOVE TEST DATA
        userDto.setFirstName("first");
        userDto.setLastName("last");
        userDto.setEmail("first@first.com");
        userDto.setPassword("first");
        userDto.setMatchingPassword("first");

        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/registration")
    public ModelAndView registerUserAccount(
            @ModelAttribute("user") @Valid UserDto accountDto,
            BindingResult result) {

        User registered = new User();
        if(!result.hasErrors()) {
            registered = createUserAccount(accountDto, result);
        }
        if(registered == null) {
            result.rejectValue("email", "message.regError");
        }
        if(result.hasErrors()) {
            return new ModelAndView("registration", "user", accountDto);
        } else {
            return new ModelAndView("login", "user", accountDto);
        }
    }

    private User createUserAccount(UserDto accountDto, BindingResult result) {
        User registered;
        try {
            registered = userService.registerNewUserAccount(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
