package com.pbukki.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "Cards",
        description = "Holds Cards Details"
)
public class CardDto {
    private String mobileNumber;
    private String cardNumber;
    private String cardType;
    private int totalLimit;
    private int amountUsed;
    private int availableAmount;
}
