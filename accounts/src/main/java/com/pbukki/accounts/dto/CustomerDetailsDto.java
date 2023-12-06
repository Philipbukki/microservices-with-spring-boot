package com.pbukki.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
@Data
@Schema(
        name="Customer Details",
        description = "Holds Customer Accounts and Cards Details"
)
public class CustomerDetailsDto {
    @Schema(
            description = "customer name"
    )
    private String name;
    @Schema(
            description = "customer email address"
    )
    private String email;
    @Schema(
            description = "customer mobile number"
    )
    private String mobileNumber;
    @Schema(
            description = "customer Account Details"
    )
    private AccountDto accountDto;
    @Schema(
            description = "customer Card Details"
    )
    private CardDto cardDto;

}
