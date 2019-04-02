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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {

        System.out.println("get /registration");

        return "redirect:/";
    }

    @PostMapping("/test")
    public String test() {
        return "noSecurity";
    }

    @PostMapping("/registration")
    public String registerUserAccount(
            @ModelAttribute("user") @Valid UserDto userDto, BindingResult result, Model model, HttpServletRequest http) {


        System.out.println("status of userDto in post/registration:" + userDto);
        model.addAttribute("user", userDto);


        if(result.hasErrors()) {
            System.out.println("Result has errors (not valid): " + result);
            return "index";
        } else {
            User user = createUserAccount(userDto);

            if(user == null) {
                result.rejectValue("email", "Your email exists already");
                System.out.println("Result has errors (email exists)");
//                return "redirect:/#registration";
                return null; // TODO
            }
        }
        System.out.println("Result is good please login ->");
        return "redirect:/#pop-log";
    }

//    @PostMapping("/registration")
//    public ModelAndView registerUserAccount(
//            @ModelAttribute("user") @Valid UserDto accountDto,
//            BindingResult result) {
//
//        User registered = new User();
//        if(!result.hasErrors()) {
//            registered = createUserAccount(accountDto, result);
//        }
//        if(registered == null) {
//            result.rejectValue("email", "message.regError");
//        }
//        if(result.hasErrors()) {
//            System.out.println("reg error");
//            return new ModelAndView("redirect:/#registration", "user", accountDto);
//        } else {
//            System.out.println("NO reg error");
//            return new ModelAndView("redirect:/today", "user", accountDto);
//        }
//    }

//    private User createUserAccount(UserDto accountDto, BindingResult result) {
    private User createUserAccount(UserDto accountDto) {
        User registered;
//        System.out.println("createUserAccount() = " + accountDto);
        try {
            registered = userService.registerNewUserAccount(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }

    // its called if login was (not) a success
    @GetMapping("/login")
    public String login() {

        System.out.println("get /login used");

        return "redirect:/";
    }
}
