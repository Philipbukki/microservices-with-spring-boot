package com.pbukki.loans.repository;

import com.pbukki.loans.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan,Long> {
    Optional<Loan> findByLoanNumber(String loanNumber);
}
