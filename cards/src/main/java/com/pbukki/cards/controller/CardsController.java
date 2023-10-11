package com.pbukki.cards.controller;

import com.pbukki.cards.constants.CardsConstants;
import com.pbukki.cards.dto.CardDto;
import com.pbukki.cards.dto.ResponseDto;
import com.pbukki.cards.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cards")
@Tag(
        name="CRUD REST api for Card's Microservice",
        description = "CRUD REST api performs create, read,fetch, update and delete operations"
)
public class CardsController {
    private CardService cardService;
    @Operation(
            summary = "Create Card API",
            description = "Creates a new Card in Card's Microservice"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS CREATED"
    )
    @PostMapping
    public ResponseEntity<ResponseDto>  createCard(@RequestBody CardDto cardDto){
        cardService.createCard(cardDto);
        ResponseDto responseDto = new ResponseDto(CardsConstants.STATUS_201,CardsConstants.MESSAGE_201);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);

    }

    @GetMapping("mobileNumber/")
    public ResponseEntity<CardDto> fetchCardDetails(@RequestParam("mobileNumber") String mobileNumber) {
        CardDto cardsDto = cardService.getCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardsDto);
    }

    @GetMapping
    public ResponseEntity<List<CardDto>> getAllCards(){

        return new ResponseEntity<>(cardService.findAll(),HttpStatus.OK);

    }
    @PutMapping("{mobileNumber}/")
    public ResponseEntity<ResponseDto> updateCard(@PathVariable("mobileNumber") String mobileNumber, @RequestBody CardDto cardDto){
        boolean isUpdated = cardService.updateCard(mobileNumber,cardDto);

        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200,CardsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardsConstants.STATUS_417,CardsConstants.MESSAGE_417_UPDATE));
        }

    }
    @DeleteMapping("{mobileNumber}/")
    public ResponseEntity<ResponseDto> deleteCard(@PathVariable("mobileNumber") String mobileNumber){
        boolean isDeleted = cardService.deleteCard(mobileNumber);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200,CardsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardsConstants.STATUS_417,CardsConstants.MESSAGE_417_DELETE));
        }

    }

}
