package com.pbukki.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanDto {
    @Schema(
            description = "Loan mobile number",
            example = "+254798335550"
    )
    @NotNull
    private String mobileNumber;
    @Schema(
            description = "Loan Number",
            example = "1234567892"
    )
    @NotNull
    private String loanNumber;
    @Schema(
            description = "Loan Type",
            example = "Salary"
    )

    private String loanType;
    @Schema(
            description = "Loan Total",
            example = "10000"
    )

    private int totalLoan;
    @Schema(
            description = "Loan Amount Paid",
            example = "20000"
    )


    private int amountPaid;
    @Schema(
            description = "Loan Outstanding Amount",
            example = "10000"
    )

    private int outstandingAmount;

}
