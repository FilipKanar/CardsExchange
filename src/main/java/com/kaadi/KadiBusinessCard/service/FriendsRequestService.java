package com.kaadi.KadiBusinessCard.service;

import com.kaadi.KadiBusinessCard.model.FriendsRequest;
import com.kaadi.KadiBusinessCard.model.User;
import com.kaadi.KadiBusinessCard.repository.FriendsRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendsRequestService {

    FriendsRequestRepository friendsRequestRepository;

    @Autowired
    public FriendsRequestService(FriendsRequestRepository friendsRequestRepository){
        this.friendsRequestRepository=friendsRequestRepository;
    }

    public FriendsRequest saveFriendRequest(int requestUserId, int answerUserId){
        FriendsRequest friendsRequest=new FriendsRequest();
        friendsRequest.setRequestUserId(requestUserId);
        friendsRequest.setAnswerUserId(answerUserId);
        return friendsRequestRepository.save(friendsRequest);
    }

}
