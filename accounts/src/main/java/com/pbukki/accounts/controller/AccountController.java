package com.pbukki.accounts.controller;

import com.pbukki.accounts.constants.AccountsConstants;
import com.pbukki.accounts.dto.AccountDto;
import com.pbukki.accounts.dto.AccountsContactInfoDto;
import com.pbukki.accounts.dto.ErrorResponseDto;
import com.pbukki.accounts.dto.ResponseDto;
import com.pbukki.accounts.entity.Account;
import com.pbukki.accounts.service.AccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@AllArgsConstructor
@RequestMapping(value = "/api/accounts",
        produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(
        name="CRUD REST api for Accounts Microservice",
        description = "CRUD REST api performs create, read,fetch, update and delete operations"
)
public class AccountController {
    private AccountsService accountsService;
    private AccountsContactInfoDto accountsContactInfoDto;
    @Value("${build.version}")
    private String buildVersion;

    public AccountController(AccountsService accountsService, AccountsContactInfoDto accountsContactInfoDto){
        this.accountsService = accountsService;
        this.accountsContactInfoDto = accountsContactInfoDto;
    }
    @Operation(
            summary = "Create Account API",
            description = "Creates a new Account in Account's Microservice"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS CREATED"
    )
    @PostMapping
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody AccountDto accountDto){
        accountsService.createAccount(accountDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccount(@PathVariable("accountNumber") int accountNumber){
        Account account = accountsService.findById(accountNumber);
        return ResponseEntity.ok(account);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAccounts(){
        return new ResponseEntity<>(accountsService.findAll(),HttpStatus.OK);
    }
    @Operation(
            summary = "Update Account API",
            description = "Updates an existing Account in Account's Microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    }
    )
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateAccount(@PathVariable("id") int accountId,@Valid @RequestBody AccountDto accountDto){
        boolean isUpdated = accountsService.updateAccount(accountId,accountDto);
        if(isUpdated){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500));
    }

    @Operation(
            summary = "Delete Account API",
            description = "Deletes an Account from Account's Microservice"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDto.class
                            )
                    )
            )
    }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteAccount(@PathVariable("id") int accountId){
        boolean isDeleted = accountsService.delete(accountId);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500));
        }

    }
    @Operation(
            summary = "Account Api build Version",
            description = "Returns build version of Account's Microservice"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS OK"
    )
    @GetMapping("/build-version")
    public ResponseEntity<String> getBuildVersion(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(buildVersion);
    }
    @Operation(
            summary = "Account Api contact info",
            description = "Returns contact info of Account's Microservice"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS OK"
    )
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo(){
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }

}
