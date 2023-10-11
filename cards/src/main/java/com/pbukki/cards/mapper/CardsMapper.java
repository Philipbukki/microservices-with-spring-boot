package com.pbukki.cards.mapper;

import com.pbukki.cards.dto.CardDto;
import com.pbukki.cards.entity.Card;

public class CardsMapper {

    public static Card mapToEntity(CardDto cardDto,Card card){
        card.setCardNumber(cardDto.getCardNumber());
        card.setCardType(cardDto.getCardType());
        card.setAmountUsed(cardDto.getAmountUsed());
        card.setMobileNumber(cardDto.getMobileNumber());
        return card;

    }
    public static CardDto mapToDto(Card card, CardDto cardDto){
        cardDto.setCardNumber(card.getCardNumber());
        cardDto.setCardType(card.getCardType());
        cardDto.setAmountUsed(card.getAmountUsed());
        cardDto.setMobileNumber(card.getMobileNumber());
        return cardDto;

    }
}
