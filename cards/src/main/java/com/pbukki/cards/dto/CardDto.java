package com.pbukki.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Schema(
        name="Card",
        description = "Holds Card Details"
)
public class CardDto {
    private String mobileNumber;
    @Schema(
            description = "Info for Card Number", example = "1234567893"
    )
    private String cardNumber;

    private String cardType;

    private int totalLimit;

    private int amountUsed;

    private int availableAmount;
}
