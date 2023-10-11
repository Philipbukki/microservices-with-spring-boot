package com.pbukki.accounts.mapper;

import com.pbukki.accounts.dto.AccountDto;
import com.pbukki.accounts.entity.Account;

public class AccountsMapper {

    public static Account mapToEntity(AccountDto accountDto){
        Account account = new Account();
        account.setAccountType(accountDto.getAccountType());
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setBranchAddress(accountDto.getBranchAddress());

        return account;

    }
    public static AccountDto mapToDto(Account account){
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setBranchAddress(account.getBranchAddress());

        return accountDto;

    }

}
