package com.ironhack.midtermproject.repository;
import com.ironhack.midtermproject.dao.transfers.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer,Long> {
}
