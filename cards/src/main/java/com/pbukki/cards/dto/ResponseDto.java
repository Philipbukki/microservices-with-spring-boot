package com.pbukki.cards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
@Data
public class ResponseDto {
    private String statusCode;
    private String errorMsg;
}
