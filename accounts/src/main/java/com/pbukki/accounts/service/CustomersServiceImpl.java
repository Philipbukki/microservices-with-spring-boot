package com.pbukki.accounts.service;
import com.pbukki.accounts.dto.AccountDto;
import com.pbukki.accounts.dto.CardDto;
import com.pbukki.accounts.dto.CustomerDetailsDto;
import com.pbukki.accounts.dto.CustomerDto;
import com.pbukki.accounts.entity.Account;
import com.pbukki.accounts.entity.Customer;
import com.pbukki.accounts.exceptions.ResourceNotFoundException;
import com.pbukki.accounts.mapper.AccountsMapper;
import com.pbukki.accounts.mapper.CustomersMapper;
import com.pbukki.accounts.repository.AccountsRepository;
import com.pbukki.accounts.repository.CustomersRepository;
import com.pbukki.accounts.service.client.CardsFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements CustomersService{

    private CustomersRepository customersRepository;
    private AccountsRepository accountsRepository;
    private CardsFeignClient cardsFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {

        Customer customer = customersRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer","mobileNumber",1)
        );

        Account account = accountsRepository.findById(1).orElseThrow(
                ()-> new ResourceNotFoundException("Account","mobileNumber",customer.getCustomerId())
        );
        ResponseEntity<CardDto> cardDto = cardsFeignClient.fetchCardDetails(mobileNumber);

        CustomerDetailsDto customerDetails = CustomersMapper.mapToCustomerDetailsDto(customer,new CustomerDetailsDto());
        customerDetails.setAccountDto(AccountsMapper.mapToDto(account));
        customerDetails.setCardDto(cardDto.getBody());

        return customerDetails;
    }

    @Override
    public void createCustomer(CustomerDto customerDto) {
         Customer customer = new Customer();
         CustomersMapper.mapToEntity(customerDto, customer);
         customersRepository.save(customer);
         CustomersMapper.mapToDto(customer,customerDto);
    }
    @Override
    public List<CustomerDto> findAll() {
        List<Customer>  customers = customersRepository.findAll();
        return customers.stream().map(customer->CustomersMapper.mapToDto(customer,new CustomerDto()))
                .collect(Collectors.toList());
    }
    @Override
    public Customer findById(int customerId) throws Throwable {

        return customersRepository.findById(customerId).orElseThrow(new Supplier<Throwable>() {
            @Override
            public Throwable get() {
                return new ResourceNotFoundException("Customer", "customerId", customerId);
            }
        });
    }

    @Override
    public Customer update(int customerId, Customer theCustomer) {
        Customer customer = customersRepository.findById(customerId).orElseThrow(
                ()->new ResourceNotFoundException("Customer", "customerId", customerId));

        customer.setCustomerId(theCustomer.getCustomerId());
        customer.setMobileNumber(theCustomer.getMobileNumber());
        customer.setEmail(theCustomer.getEmail());
        customer.setName(theCustomer.getName());
        customer.setUpdatedAt(LocalDateTime.now());

        return customersRepository.save(customer);
    }

    @Override
    public void delete(int customerId) {
        Customer customer = customersRepository.findById(customerId).orElseThrow(
                ()->new ResourceNotFoundException("Customer", "customerId", customerId));

        customersRepository.delete(customer);
    }

}
