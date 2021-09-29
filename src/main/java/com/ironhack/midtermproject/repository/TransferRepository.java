package com.ironhack.midtermproject.repository;
import com.ironhack.midtermproject.dao.transactions.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transaction,Long> {
}
