package com.kaadi.KadiBusinessCard.controller;

import com.kaadi.KadiBusinessCard.model.Card;
import com.kaadi.KadiBusinessCard.model.User;
import com.kaadi.KadiBusinessCard.service.CardService;
import com.kaadi.KadiBusinessCard.service.FriendsRequestService;
import com.kaadi.KadiBusinessCard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CardController {

    @Autowired
    CardService cardService;

    @Autowired
    UserService userService;

    @Autowired
    FriendsRequestService friendsRequestService;

    @PostMapping("/addCard")
    public ModelAndView addContact(@RequestParam(value = "number", required = true) int number){

        ModelAndView modelAndView = new ModelAndView();

        if (cardService.findCardByNumber(number)!=null){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());
            Card card = cardService.findCardByNumber(number);
            if(card.getUser()!=null){
                friendsRequestService.saveFriendRequest(user.getId(),card.getUser().getId());
            }
            cardService.findCardByNumber(number).setUser(user);
            cardService.saveCard(card);
            modelAndView = new ModelAndView(new RedirectView("/home"));
        } else {
            modelAndView.setViewName("/cardNumberError");
        }
        return modelAndView;
    }
}
