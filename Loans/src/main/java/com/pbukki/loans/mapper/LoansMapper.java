package com.pbukki.loans.mapper;

import com.pbukki.loans.dto.LoanDto;
import com.pbukki.loans.entity.Loan;

public class LoansMapper {
    public static Loan mapToEntity(LoanDto loanDto, Loan loan){
        loan.setLoanNumber(loanDto.getLoanNumber());
        loan.setLoanType(loanDto.getLoanType());
        loan.setTotalLoan(loanDto.getTotalLoan());
        loan.setAmountPaid(loanDto.getAmountPaid());
        loan.setOutstandingAmount(loanDto.getOutstandingAmount());
        loan.setMobileNumber(loanDto.getMobileNumber());

        return loan;
    }

    public static LoanDto mapToDto(Loan loan, LoanDto loanDto){
        loanDto.setLoanNumber(loan.getLoanNumber());
        loanDto.setLoanType(loan.getLoanType());
        loanDto.setTotalLoan(loan.getTotalLoan());
        loanDto.setAmountPaid(loan.getAmountPaid());
        loanDto.setOutstandingAmount(loan.getOutstandingAmount());
        loanDto.setMobileNumber(loan.getMobileNumber());

        return loanDto;



    }
}
