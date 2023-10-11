package com.pbukki.loans.controller;

import com.pbukki.loans.contants.LoansConstants;
import com.pbukki.loans.dto.LoanDto;
import com.pbukki.loans.dto.ResponseDto;
import com.pbukki.loans.service.LoanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "api/loans/", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LoanController {
    private LoanService loanService;

    @GetMapping
    public ResponseEntity<List<LoanDto>> getLoans(){
        return  new ResponseEntity<>(loanService.findAll(),HttpStatus.OK);
    }
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
}
