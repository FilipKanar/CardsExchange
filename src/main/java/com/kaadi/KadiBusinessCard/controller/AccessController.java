package com.kaadi.KadiBusinessCard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccessController {

    @GetMapping("/access-denied")
    public ModelAndView accessDenied(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("access-denied");
        return modelAndView;
    }
}
