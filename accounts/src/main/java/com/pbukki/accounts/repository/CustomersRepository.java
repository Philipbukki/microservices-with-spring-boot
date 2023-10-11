package com.pbukki.accounts.repository;

import com.pbukki.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepository extends JpaRepository<Customer,Integer> {
}
