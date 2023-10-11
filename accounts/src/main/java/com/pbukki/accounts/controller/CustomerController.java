package com.pbukki.accounts.controller;

import com.pbukki.accounts.constants.AccountsConstants;
import com.pbukki.accounts.dto.CustomerDto;
import com.pbukki.accounts.dto.ResponseDto;
import com.pbukki.accounts.entity.Account;
import com.pbukki.accounts.entity.Customer;
import com.pbukki.accounts.service.CustomersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/customers", produces = {
        MediaType.APPLICATION_JSON_VALUE
})
@AllArgsConstructor
public class CustomerController {
    private CustomersService customersService;

    @PostMapping
    public ResponseEntity<ResponseDto> saveCustomer(@RequestBody CustomerDto customerDto){
        customersService.createCustomer(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }
    @GetMapping
    public ResponseEntity<List<CustomerDto>>  listCustomers(){
        return new ResponseEntity<>(customersService.findAll(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int customerId,
                                                   @RequestBody Customer theCustomer){

        return new ResponseEntity<>(customersService.update(customerId,theCustomer),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") int customerId){
        customersService.delete(customerId);
        return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
    }

}
