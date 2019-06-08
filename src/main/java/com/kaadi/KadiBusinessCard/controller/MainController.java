package com.kaadi.KadiBusinessCard.controller;

import com.kaadi.KadiBusinessCard.model.User;
import com.kaadi.KadiBusinessCard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @GetMapping({"/","/home"})
    public ModelAndView mainSite(){
        ModelAndView modelAndView=new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.setViewName("main/home");
        return modelAndView;
    }

}
