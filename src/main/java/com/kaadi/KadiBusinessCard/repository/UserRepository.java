package com.kaadi.KadiBusinessCard.repository;

import com.kaadi.KadiBusinessCard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    User findById(int id);
}
