package com.pbukki.loans.service;

import com.pbukki.loans.dto.LoanDto;
import com.pbukki.loans.entity.Loan;
import com.pbukki.loans.exceptions.LoanAlreadyExistException;
import com.pbukki.loans.exceptions.ResourceNotFoundException;
import com.pbukki.loans.mapper.LoansMapper;
import com.pbukki.loans.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class LoanServiceImpl implements LoanService{
    private LoanRepository loanRepository;
    @Override
    public List<LoanDto> findAll() {
        List<Loan> loans = loanRepository.findAll();
        return loans.stream().map(loan -> LoansMapper.mapToDto(loan,new LoanDto()))
        .collect(Collectors.toList());
    }
    @Override
    public void createLoan(LoanDto loanDto) {
        Optional<Loan> optionalLoan = loanRepository.findByLoanNumber(loanDto.getLoanNumber());
        if(optionalLoan.isPresent()){
            throw new LoanAlreadyExistException("loanNumber",loanDto.getLoanNumber());
        }
        Loan loan = LoansMapper.mapToEntity(loanDto, new Loan());
        loanRepository.save(loan);
    }

    @Override
    public boolean updateLoan(String loanNumber, LoanDto loanDto) {
        Loan loan = loanRepository.findByLoanNumber(loanNumber).orElseThrow(
                ()->new ResourceNotFoundException("Loan","loanNumber",loanNumber));

            LoansMapper.mapToEntity(loanDto,loan);
            loanRepository.save(loan);
            return true;
    }
    @Override
    public boolean deleteLoan(String loanNumber) {
        Loan loan = loanRepository.findByLoanNumber(loanNumber).orElseThrow(
                ()->new ResourceNotFoundException("Loan","loanNumber",loanNumber));

        loanRepository.deleteById(loan.getLoanId());
        return true;
    }
}
