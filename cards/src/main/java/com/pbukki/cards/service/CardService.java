package com.pbukki.cards.service;

import com.pbukki.cards.dto.CardDto;

import java.util.List;

public interface CardService {
    void createCard(CardDto cardDto);
    CardDto getCard(String mobileNumber);
    List<CardDto> findAll();
    boolean updateCard(String mobileNumber, CardDto cardDto);
    boolean deleteCard(String mobileNumber);

}

