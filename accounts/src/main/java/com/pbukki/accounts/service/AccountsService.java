package com.pbukki.accounts.service;

import com.pbukki.accounts.dto.AccountDto;
import com.pbukki.accounts.entity.Account;

import java.util.List;

public interface AccountsService {

    void createAccount(AccountDto accountDto);
    Account findById(int accountNumber);
    List<AccountDto> findAll();
    boolean updateAccount(int accountId, AccountDto accountDto);
    boolean delete(int accountId);
}
