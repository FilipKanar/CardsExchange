package com.kaadi.KadiBusinessCard.repository;

import com.kaadi.KadiBusinessCard.model.Card;
import com.kaadi.KadiBusinessCard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
    Card findByNumber(int number);
    List<Card> findAllByUser(User user);
}
