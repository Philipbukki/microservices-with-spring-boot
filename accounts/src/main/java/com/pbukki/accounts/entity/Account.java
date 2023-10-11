package com.pbukki.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "accounts")
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity{
    private int customerId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="account_number")
    private int accountNumber;
    @Column(name="account_type")
    private String accountType;
    @Column(name="branch_address")
    private String branchAddress;

}
