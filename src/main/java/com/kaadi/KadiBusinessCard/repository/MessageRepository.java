package com.kaadi.KadiBusinessCard.repository;

import com.kaadi.KadiBusinessCard.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {
    List<Message> findAllBySenderIdAndReceiverId(int sender, int receiver);
}
