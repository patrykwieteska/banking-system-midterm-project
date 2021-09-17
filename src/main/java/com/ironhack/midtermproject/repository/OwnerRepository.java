package com.ironhack.midtermproject.repository;

import com.ironhack.midtermproject.dao.owners.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner,Long> {
}
