package com.pbukki.accounts.mapper;

import com.pbukki.accounts.dto.CustomerDetailsDto;
import com.pbukki.accounts.dto.CustomerDto;
import com.pbukki.accounts.entity.Customer;

public class CustomersMapper {
    public static Customer mapToEntity(CustomerDto customerDto, Customer customer){
        customer.setMobileNumber(customerDto.getMobileNumber());
        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
        return customer;
    }

    public static CustomerDetailsDto mapToCustomerDetailsDto(Customer customer, CustomerDetailsDto customerDetailsDto){
        customerDetailsDto.setMobileNumber(customer.getMobileNumber());
        customerDetailsDto.setEmail(customer.getEmail());
        customerDetailsDto.setName(customer.getName());
        return customerDetailsDto;
    }
    public static CustomerDto mapToDto(Customer customer, CustomerDto customerDto){
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;

    }

}
