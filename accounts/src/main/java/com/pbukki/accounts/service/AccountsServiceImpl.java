package com.pbukki.accounts.service;

import com.pbukki.accounts.dto.AccountDto;
import com.pbukki.accounts.entity.Account;
import com.pbukki.accounts.exceptions.ResourceNotFoundException;
import com.pbukki.accounts.mapper.AccountsMapper;
import com.pbukki.accounts.repository.AccountsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.pbukki.accounts.mapper.AccountsMapper.mapToDto;
import static com.pbukki.accounts.mapper.AccountsMapper.mapToEntity;

@AllArgsConstructor
@Service
public class AccountsServiceImpl implements AccountsService{
    private AccountsRepository accountsRepository;

    @Override
    public void createAccount(AccountDto accountDto) {
        Account account = mapToEntity(accountDto);
        Account savedAccount =  accountsRepository.save(account);
        mapToDto(savedAccount);
    }
    @Override
    public Account findById(int accountNumber) {
        Optional<Account> account = accountsRepository.findById(accountNumber);
        Account myAccount = null;
        if(account.isPresent()){
            myAccount = account.get();
        }else{
            throw new ResourceNotFoundException("Account","accountId",accountNumber);
        }
        return myAccount;
    }
    @Override
    public List<AccountDto> findAll() {
     List<Account> accounts = accountsRepository.findAll();
        return accounts.stream().map(
                AccountsMapper::mapToDto).collect(Collectors.toList());
    }
    @Override
    public boolean updateAccount(int accountId, AccountDto accountDto) {

        Account updatedAccount= accountsRepository.findById(accountId).orElseThrow(
                ()->new ResourceNotFoundException("Account","accountId",accountId));
        updatedAccount.setAccountNumber(accountDto.getAccountNumber());
        updatedAccount.setBranchAddress(accountDto.getBranchAddress());
        updatedAccount.setAccountNumber(accountDto.getAccountNumber());
        updatedAccount.setAccountType(accountDto.getAccountType());
        accountsRepository.save(updatedAccount);
        return true;
    }

    @Override
    public boolean delete(int accountId) {
        Account account= accountsRepository.findById(accountId).orElseThrow(
                ()->new ResourceNotFoundException("Account","accountId",accountId));
            accountsRepository.delete(account);
            return true;
    }
}
