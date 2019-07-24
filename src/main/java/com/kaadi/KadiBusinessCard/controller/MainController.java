package com.kaadi.KadiBusinessCard.controller;

import com.kaadi.KadiBusinessCard.model.FriendsRequest;
import com.kaadi.KadiBusinessCard.model.User;
import com.kaadi.KadiBusinessCard.service.FriendsRequestService;
import com.kaadi.KadiBusinessCard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    FriendsRequestService friendsRequestService;

    @GetMapping({"/","/home"})
    public ModelAndView mainSite(){
        ModelAndView modelAndView=new ModelAndView();
        List<FriendsRequest> userFriendsRequest = new ArrayList<>();
        List<User> friendsList = new ArrayList<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        userFriendsRequest=friendsRequestService.invitedBy(user);
        friendsList=friendsRequestService.friendsList(user);
        modelAndView.addObject("invitedByList", userFriendsRequest);
        modelAndView.addObject("friendsList", friendsList);
        modelAndView.addObject("userService",userService);
        modelAndView.addObject("friendsRequestService",friendsRequestService);
        modelAndView.setViewName("main/home");
        return modelAndView;
    }

}
