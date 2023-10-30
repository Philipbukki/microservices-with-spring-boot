package com.pbukki.cards.controller;

import com.pbukki.cards.constants.CardsConstants;
import com.pbukki.cards.dto.CardDto;
import com.pbukki.cards.dto.CardsContactDto;
import com.pbukki.cards.dto.ResponseDto;
import com.pbukki.cards.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
//@AllArgsConstructor
@RequestMapping(value = "/api/cards/", produces = {MediaType.APPLICATION_JSON_VALUE})
@Tag(
        name="CRUD REST api for Card's Microservice",
        description = "CRUD REST api performs create, read,fetch, update and delete operations"
)
public class CardsController {

    @Value("${build.version}")
    private String buildVersion;

    private final CardService cardService;
    private final CardsContactDto cardsContactDto;
    public CardsController(CardService cardService, CardsContactDto cardsContactDto) {
        this.cardService = cardService;
        this.cardsContactDto = cardsContactDto;
    }

    @Autowired
    private Environment environment;

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

    @Operation(
            summary = "Fetch Card API",
            description = "Fetches Card  details using mobile number in Card's Microservice"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS OK"
    )
    @GetMapping("mobileNumber")
    public ResponseEntity<CardDto> fetchCardDetails(@RequestParam("mobileNumber") String mobileNumber) {
        CardDto cardsDto = cardService.getCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardsDto);
    }

    @Operation(
            summary = "Fetch Cards API",
            description = "Fetches All Cards in Card's Microservice"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS OK"
    )
    @GetMapping
    public ResponseEntity<List<CardDto>> getAllCards(){

        return new ResponseEntity<>(cardService.findAll(),HttpStatus.OK);

    }

    @Operation(
            summary = "Update Card API",
            description = "Updates existing Card in Card's Microservice"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS OK"
    )
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

    @Operation(
            summary = "Contact Info for API",
            description = "Contact Info for Card's Microservice"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS OK"
    )
    @GetMapping("contact-info")
    public ResponseEntity<CardsContactDto> getContactInfo(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardsContactDto);
    }

    @GetMapping("build-version")
    public ResponseEntity<String> getBuildVersion(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }

}
