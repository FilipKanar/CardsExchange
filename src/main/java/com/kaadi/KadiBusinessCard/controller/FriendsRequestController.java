package com.kaadi.KadiBusinessCard.controller;

import com.kaadi.KadiBusinessCard.service.FriendsRequestService;
import com.kaadi.KadiBusinessCard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class FriendsRequestController {

    @Autowired
    FriendsRequestService friendsRequestService;

    @Autowired
    UserService userService;

    @PostMapping("/acceptFriendsRequest")
    public ModelAndView acceptFriendsRequest(@RequestParam("id") int friendsRequestId){
        friendsRequestService.setFriendsRequestActive(friendsRequestService.findFriendsRequestById(friendsRequestId));
        return new ModelAndView(new RedirectView("/home"));
    }


    @PostMapping("/refuseFriendsRequest")
    public ModelAndView refuseFriendsRequest(@RequestParam("id") int friendsRequestId){
        friendsRequestService.deleteFriendsRequest(friendsRequestService.findFriendsRequestById(friendsRequestId));
        return new ModelAndView(new RedirectView("/home"));
    }



}
