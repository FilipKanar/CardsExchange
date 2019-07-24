package com.kaadi.KadiBusinessCard.service;

import com.kaadi.KadiBusinessCard.model.Message;
import com.kaadi.KadiBusinessCard.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository=messageRepository;
    }

    public List<Message> findMessagesBySenderAndReceiverId(int senderId, int receiverId){
        return messageRepository.findAllBySenderIdAndReceiverId(senderId,receiverId);
    }

    public List<Message> getAllFriendRelatedMessages(int id,int secondId){
        Set<Message> messageSet = new HashSet<>(messageRepository.findAllBySenderIdAndReceiverId(id,secondId));
        messageSet.addAll(messageRepository.findAllBySenderIdAndReceiverId(secondId,id));
        System.out.println("<>-<>-<>-<>-<>-<>-<>-<>-<>-<>-<>-<>-<>");
        System.out.println(messageSet);
        System.out.println("<>-<>-<>-<>-<>-<>-<>-<>-<>-<>-<>-<>-<>");
        return new ArrayList<Message>(messageSet);
    }

    public Message saveMessage(Message message){
        return messageRepository.save(message);
    }
}
