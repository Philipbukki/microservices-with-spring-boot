package com.pbukki.loans.service;

import com.pbukki.loans.dto.LoanDto;

import java.util.List;

public interface LoanService {
    List<LoanDto> findAll();
    void createLoan( LoanDto loanDto);

    boolean updateLoan(String loanNumber, LoanDto loanDto);

    boolean deleteLoan(String loanNumber);


}
