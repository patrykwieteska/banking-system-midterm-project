package com.ironhack.midtermproject.repository;

import com.ironhack.midtermproject.dao.owners.Owner;
import com.ironhack.midtermproject.dao.owners.ThirdPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Long> {
    Optional<ThirdPart> findByHashedKey(String hashedKey);
}
