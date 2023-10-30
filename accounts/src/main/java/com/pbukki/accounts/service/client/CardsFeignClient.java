package com.pbukki.accounts.service.client;

import com.pbukki.accounts.dto.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClient {

    @GetMapping(value = {"/api/cards/mobileNumber"},consumes = "application/json")
    ResponseEntity<CardDto> fetchCardDetails(@RequestParam("mobileNumber") String mobileNumber);

}
