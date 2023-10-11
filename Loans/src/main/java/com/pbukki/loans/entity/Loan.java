package com.pbukki.loans.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor @Setter @Getter @ToString @NoArgsConstructor
@Table(name = "loans")
public class Loan extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    private String mobileNumber;

    private String loanNumber;

    private String loanType;

    private int totalLoan;

    private int amountPaid;

    private int outstandingAmount;
}
