package com.pbukki.cards.exceptions;

public class CardAlreadyExistException extends RuntimeException{
    public CardAlreadyExistException(String fieldName, String fieldValue) {
        super(String.format("Card with %s: %s already exists",fieldName,fieldValue));
    }
}
