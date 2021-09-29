package com.ironhack.midtermproject.repository;

import com.ironhack.midtermproject.dao.accounts.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    List<Account> findByPrimaryOwnerId(Long id);
}
