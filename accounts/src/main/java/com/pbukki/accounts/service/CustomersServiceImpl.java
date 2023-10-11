package com.pbukki.accounts.service;

import com.pbukki.accounts.dto.CustomerDto;
import com.pbukki.accounts.entity.Customer;
import com.pbukki.accounts.exceptions.ResourceNotFoundException;
import com.pbukki.accounts.mapper.CustomersMapper;
import com.pbukki.accounts.repository.CustomersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements CustomersService{

    private CustomersRepository customersRepository;
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
