package com.kaadi.KadiBusinessCard.service;

import com.kaadi.KadiBusinessCard.model.Card;
import com.kaadi.KadiBusinessCard.model.User;
import com.kaadi.KadiBusinessCard.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    private CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository){
        this.cardRepository=cardRepository;
    }

    public Card findCardByNumber(int number){
        return cardRepository.findByNumber(number);
    }

    public Card saveCard(Card card){
        return cardRepository.save(card);
    }

    public List<Card> findAllByUser(User user){
        return cardRepository.findAllByUser(user);
    }

}
