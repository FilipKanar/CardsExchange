package com.kaadi.KadiBusinessCard.service;

import com.kaadi.KadiBusinessCard.model.FriendsRequest;
import com.kaadi.KadiBusinessCard.model.User;
import com.kaadi.KadiBusinessCard.repository.FriendsRequestRepository;
import com.kaadi.KadiBusinessCard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

@Service
public class FriendsRequestService {


    FriendsRequestRepository friendsRequestRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    public FriendsRequestService(FriendsRequestRepository friendsRequestRepository){
        this.friendsRequestRepository=friendsRequestRepository;
    }

    public FriendsRequest findFriendsRequestById(int id){
        return friendsRequestRepository.findById(id);
    }

    public FriendsRequest saveFriendRequest(int requestUserId, int answerUserId){
        FriendsRequest friendsRequest=new FriendsRequest();

        friendsRequest.setRequestUserId(requestUserId);
        friendsRequest.setAnswerUserId(answerUserId);
        for(FriendsRequest request : friendsRequestRepository.findAll()){
            if(!((request.getAnswerUserId() == answerUserId && request.getRequestUserId() ==requestUserId)
                    || (request.getAnswerUserId() == requestUserId && request.getRequestUserId()==answerUserId)
                    || (request.getAnswerUserId() == answerUserId && request.getRequestUserId() == answerUserId)
                    || (request.getAnswerUserId() == requestUserId && request.getRequestUserId()==requestUserId))){
                friendsRequest.setIsAccepted(0);
            }else{
                friendsRequest.setIsAccepted(2);
            }
        }
        return friendsRequestRepository.save(friendsRequest);
    }

    public ArrayList<FriendsRequest>  invitedBy(User user){
        ArrayList<FriendsRequest> invitedByList=new ArrayList<>();

        for(FriendsRequest friendsRequest : friendsRequestRepository.findAll()){
            if(friendsRequest.getAnswerUserId()==user.getId()&&friendsRequest.getIsAccepted()==0){
                invitedByList.add(friendsRequest);
            }
        }
        return invitedByList;
    }

    public ArrayList<User> friendsList(User user){
        ArrayList<User> friendsList = new ArrayList<>();
        for(FriendsRequest friendsRequest : friendsRequestRepository.findAll()){
            if(friendsRequest.getAnswerUserId()==user.getId()||friendsRequest.getRequestUserId()==user.getId()){
                if(friendsRequest.getIsAccepted()==1){
                    if(friendsRequest.getAnswerUserId()==user.getId()){
                        friendsList.add(userRepository.findById(friendsRequest.getRequestUserId()));
                    } else {
                        friendsList.add(userRepository.findById(friendsRequest.getAnswerUserId()));
                    }
                }
            }
        }
        return friendsList;
    }

    public void setFriendsRequestActive(FriendsRequest friendsRequest){
        friendsRequest.setIsAccepted(1);
        friendsRequestRepository.save(friendsRequest);
    }

    public void deleteFriendsRequest(FriendsRequest friendsRequest){
        friendsRequestRepository.delete(friendsRequest);
    }
}
