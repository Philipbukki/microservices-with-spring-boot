package com.pbukki.loans.exceptions;

public class LoanAlreadyExistException extends RuntimeException{

    public LoanAlreadyExistException(String fieldName, String fieldValue) {
        super(String.format("Loan with %s: %s",fieldName,fieldValue));
    }
}
