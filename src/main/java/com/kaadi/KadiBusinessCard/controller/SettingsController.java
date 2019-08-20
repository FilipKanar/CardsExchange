package com.kaadi.KadiBusinessCard.controller;

import com.kaadi.KadiBusinessCard.model.User;
import com.kaadi.KadiBusinessCard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
public class SettingsController {

    @Autowired
    UserService userService;

    @GetMapping("/settings")
    public ModelAndView settings(){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        modelAndView.addObject("user", user);
        modelAndView.setViewName("settings/settings");
        return modelAndView;
    }

    @PostMapping("/saveUserDetails")
    public ModelAndView saveUserDetails(@Valid User user){
        System.out.println(user);
        userService.saveUpdatedUser(user);
        return new ModelAndView(new RedirectView("/settings"));
    }
}
