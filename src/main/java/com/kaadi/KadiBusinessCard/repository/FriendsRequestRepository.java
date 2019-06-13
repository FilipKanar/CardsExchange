package com.kaadi.KadiBusinessCard.repository;

import com.kaadi.KadiBusinessCard.model.FriendsRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendsRequestRepository extends JpaRepository<FriendsRequest,Integer> {
    FriendsRequest findByRequestUserIdAndAnswerUserId(int requestUserId, int answerUserId);
    FriendsRequest findByAnswerUserId(int answerUserId);
    FriendsRequest findById(int id);
}
