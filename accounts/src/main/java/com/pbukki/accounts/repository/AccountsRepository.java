package com.pbukki.accounts.repository;

import com.pbukki.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends JpaRepository<Account,Integer> {

}
