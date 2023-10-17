package com.pbukki.loans.controller;

import com.pbukki.loans.contants.LoansConstants;
import com.pbukki.loans.dto.ErrorResponseDto;
import com.pbukki.loans.dto.LoanDto;
import com.pbukki.loans.dto.LoansContactInfoDto;
import com.pbukki.loans.dto.ResponseDto;
import com.pbukki.loans.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name="Loan REST API Endpoints",
        description = "CRUD REST API'S for loans"
)
@RestController
//@AllArgsConstructor
@RequestMapping(value = "api/loans/", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LoanController {
    private final LoanService loanService;
    private final LoansContactInfoDto loansContactInfoDto;

    @Value("${build.version}")
    private String buildVersion;

    public LoanController(LoanService loanService,LoansContactInfoDto loansContactInfoDto) {
        this.loanService = loanService;
        this.loansContactInfoDto = loansContactInfoDto;
    }

    @GetMapping
    public ResponseEntity<List<LoanDto>> getLoans(){
        return  new ResponseEntity<>(loanService.findAll(),HttpStatus.OK);
    }

    @Operation(
            summary = "Creates new loan",
            description = "REST Endpoint to create loan"
    )

    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP STATUS CREATED"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description ="HTTP STATUS BAD REQUEST",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )

    })
    @PostMapping()
    public ResponseEntity<ResponseDto> createAccount(@RequestBody LoanDto loanDto){
        loanService.createLoan(loanDto);
        ResponseDto responseDto = new ResponseDto(LoansConstants.STATUS_201,LoansConstants.MESSAGE_201);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateLoan(@PathVariable("id") String loanNumber, @RequestBody LoanDto loanDto ){
        boolean isUpdated = loanService.updateLoan(loanNumber,loanDto);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200,LoansConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_UPDATE));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto>  deleteLoan(@PathVariable("id") String loanNumber){
        boolean isDeleted = loanService.deleteLoan(loanNumber);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200,LoansConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_UPDATE));
        }

    }

    @Operation(
            summary = "Loans Rest Api Build version",
            description = "Returns Loans Rest Api Build version"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS OK"
    )
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildVersion(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }

    @Operation(
            summary = "Loans Rest Api Build version",
            description = "Returns Loans Rest Api Build version"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS OK"
    )
    @GetMapping("/contact-info")
    public ResponseEntity<LoansContactInfoDto> getContactInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loansContactInfoDto);
    }

}
