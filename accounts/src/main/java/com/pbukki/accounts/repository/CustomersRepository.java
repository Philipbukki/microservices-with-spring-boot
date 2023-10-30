package com.pbukki.accounts.repository;

import com.pbukki.accounts.entity.Account;
import com.pbukki.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomersRepository extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByMobileNumber(String mobileNumber);
}
