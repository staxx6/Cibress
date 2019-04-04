package com.datpixelstudio.cibress.controller;

import com.datpixelstudio.cibress.dao.MessageRepository;
import com.datpixelstudio.cibress.dto.UserDto;
import com.datpixelstudio.cibress.entity.User;
import com.datpixelstudio.cibress.playground.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal User user, Model model) {
//        System.out.println("home says hello!");

        // TODO is this correct?
        if(user != null) {
            System.out.println(user.getUsername());

            // TODO WARNING really stupid check (I think :))!!!
            if(user.getUsername() != null) {
                System.out.println("home() return main");
                return "redirect:/today";
            }
        }

        UserDto userDto = new UserDto();

        model.addAttribute("user", userDto);
//        System.out.println("home() return index");
        return "index";
    }


    /*
     * Routes just for learning purposes
     */

//    @GetMapping("/noSecurity")
//    public String noSecurity() {
//        return "noSecurity";
//    }
//
//    @GetMapping("/admin")
//    public String admin() {
//        return "admin";
//    }

//    @GetMapping("/messages")
//    public String listMessages(@AuthenticationPrincipal User user, Model model) {
//        Iterable<Message> messages = messageRepository.findByUserId(user.getId());
//        model.addAttribute("messages", messages);
//        return "listMessages";
//    }

//    @GetMapping("/messages/{id}")
//    public String viewMessage(@PathVariable Long id, Model model) {
//        Message message = messageRepository.findById(id).get();
//        model.addAttribute("message", message);
//        return "viewMessage";
//    }

//    @GetMapping("/privateMessage/{id}")
//    public String viewPrivateMessage(@PathVariable Long id, Model model) {
//        Message message = messageRepository.findOne(id);
//        model.addAttribute("message", message);
//        return "viewMessage";
//    }
}
