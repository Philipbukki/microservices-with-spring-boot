package com.pbukki.cards.repository;

import com.pbukki.cards.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CardsRepository extends JpaRepository<Card,Long> {
    Optional<Card> findByMobileNumber(String mobileNumber);
//    @Modifying
//    @Transactional
//    void deleteByMobileNumber(String mobileNumber);
}
