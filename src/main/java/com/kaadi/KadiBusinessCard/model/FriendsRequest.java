package com.kaadi.KadiBusinessCard.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class FriendsRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "friends_request_id")
    private int id;

    @Column(name = "requesting_user_id")
    private int requestUserId;

    @Column(name = "answering_user_id")
    private int answerUserId;

    @Column(name = "is_accepted")
    private int isAccepted;

}
