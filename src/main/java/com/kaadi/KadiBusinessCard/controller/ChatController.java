package com.kaadi.KadiBusinessCard.controller;

import com.kaadi.KadiBusinessCard.model.Message;
import com.kaadi.KadiBusinessCard.service.MessageService;
import com.kaadi.KadiBusinessCard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


@Controller
public class ChatController {

    @Autowired
    UserService userService;

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/userChat/{userId}", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView userChat(@PathVariable int userId){
        ModelAndView modelAndView=new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ArrayList<Message> tempArr = new ArrayList<Message>();
        tempArr.addAll(messageService.getAllFriendRelatedMessages(userId, userService.findUserByEmail(auth.getName()).getId()));
        Collections.sort(tempArr, new Comparator<Message>() {
            @Override
            public int compare(Message message, Message t1) {
                return message.getTime().compareTo(t1.getTime());
            }
        }.reversed());
        modelAndView.addObject("messages", tempArr);
        modelAndView.addObject("friend", userService.findUserById(userId));
        modelAndView.addObject("current", userService.findUserByEmail(auth.getName()).getUsername());
        modelAndView.setViewName("chat/chat");
        return modelAndView;
    }

    @GetMapping("/sendMessage/{sendToId}")
    public ModelAndView sendMessage(@PathVariable int sendToId,@RequestParam("sendMessage") String messageContent){
        Message message = new Message(messageContent, userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getId(), sendToId);
        messageService.saveMessage(message);
        return new ModelAndView(new RedirectView("/userChat/"+sendToId));
    }
}
