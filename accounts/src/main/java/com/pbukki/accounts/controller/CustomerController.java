package com.pbukki.accounts.controller;

import com.pbukki.accounts.constants.AccountsConstants;
import com.pbukki.accounts.dto.CustomerDetailsDto;
import com.pbukki.accounts.dto.CustomerDto;
import com.pbukki.accounts.dto.ResponseDto;
import com.pbukki.accounts.entity.Customer;
import com.pbukki.accounts.mapper.CustomersMapper;
import com.pbukki.accounts.repository.AccountsRepository;
import com.pbukki.accounts.service.CustomersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "api/customers", produces = {
        MediaType.APPLICATION_JSON_VALUE
})
@Validated
@Tag(
        name="CRUD REST api for Customer",
        description = "CRUD REST api performs create, read,fetch, update and delete operations"
)
@AllArgsConstructor
public class CustomerController {
    private CustomersService customersService;

    @GetMapping("/details")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
            String mobileNumber){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customersService.fetchCustomerDetails(mobileNumber));
    }

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
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id") int customerId,
                                                   @RequestBody Customer theCustomer){

        Customer customer = customersService.update(customerId,theCustomer);

        return new ResponseEntity<>(CustomersMapper.mapToDto(customer,new CustomerDto()), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") int customerId){
        customersService.delete(customerId);
        return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
    }

}
