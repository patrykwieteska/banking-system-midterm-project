package com.ironhack.midtermproject.repository;

import com.ironhack.midtermproject.dao.accounts.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckingRepository extends JpaRepository<Checking,Long> {


}
