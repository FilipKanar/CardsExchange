package com.kaadi.KadiBusinessCard.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "card_id")
    private int id;

    @Column(name= "card_number")
    private int number;

    @Column(name="founder")
    private String founder;

    @ManyToOne
    User user;
}
