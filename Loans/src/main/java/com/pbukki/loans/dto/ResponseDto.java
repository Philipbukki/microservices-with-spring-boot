package com.pbukki.loans.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseDto {
    private String responseCode;
    private String responseMsg;
}
