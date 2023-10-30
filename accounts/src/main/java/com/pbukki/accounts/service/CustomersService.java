package com.pbukki.accounts.service;

import com.pbukki.accounts.dto.CustomerDetailsDto;
import com.pbukki.accounts.dto.CustomerDto;
import com.pbukki.accounts.entity.Customer;

import java.util.List;

public interface CustomersService {
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
    void createCustomer(CustomerDto customerDto);
    List<CustomerDto> findAll();
    Customer findById(int customerId) throws Throwable;
    Customer update(int customerId, Customer customer);
    void delete(int customerId);
}
