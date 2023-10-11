package com.pbukki.accounts.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Data
@Schema(
        name = "Account",
        description = "Holds Accounts Details"
)
public class AccountDto {

//    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
//    @NotEmpty(message = "Account Number cannot be empty or null")
    @Schema(
            description = "Customer account Number",example = "1234567894"
    )
    private int accountNumber;
    @Schema(
            description = "Customer account type", example = "Savings"
    )
    @NotEmpty(message = "Account Type cannot be empty or null")
    private String accountType;
    @Schema(
            description = "Customer Account Branch", example = "123NYC"
    )
    @NotEmpty(message = "Branch Address cannot be empty or null")
    private String branchAddress;
}
