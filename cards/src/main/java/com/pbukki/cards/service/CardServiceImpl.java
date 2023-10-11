package com.pbukki.cards.service;

import com.pbukki.cards.dto.CardDto;
import com.pbukki.cards.entity.Card;
import com.pbukki.cards.exceptions.CardAlreadyExistException;
import com.pbukki.cards.exceptions.ResourceNotFoundException;
import com.pbukki.cards.mapper.CardsMapper;
import com.pbukki.cards.repository.CardsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {
    CardsRepository cardsRepository;

    @Override
    public void createCard(CardDto cardDto) {
        Optional<Card> optionalCard = cardsRepository.findByMobileNumber(cardDto.getMobileNumber());
        if (optionalCard.isPresent()) {
            throw new CardAlreadyExistException("mobileNumber", cardDto.getMobileNumber());
        }
        Card card = CardsMapper.mapToEntity(cardDto, new Card());
        cardsRepository.save(card);
    }

    @Override
    public CardDto getCard(String mobileNumber) {
        Card card = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber",mobileNumber));

        return CardsMapper.mapToDto(card,new CardDto());
    }

    @Override
    public List<CardDto> findAll() {
        List<Card> cards = cardsRepository.findAll();
        return cards.stream().map(card -> CardsMapper.mapToDto(card, new CardDto()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateCard(String mobileNumber, CardDto cardDto) {
        Card card = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));

        CardsMapper.mapToEntity(cardDto, card);
        cardsRepository.save(card);
        return true;
    }
    @Override
    public boolean deleteCard(String mobileNumber) {
        Card card = cardsRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber",mobileNumber));

        cardsRepository.deleteById(card.getCardId());
        return true;
    }

}


