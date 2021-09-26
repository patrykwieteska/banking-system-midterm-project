package com.ironhack.midtermproject.repository;

import com.ironhack.midtermproject.dao.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;
    
    Address address;

    @BeforeEach
    void setUp() {
        address = new Address("Poland","Wroclaw","52-900","Robotnicza", "11");
        addressRepository.save(address);
    }

    @AfterEach
    void tearDown() {

        addressRepository.deleteAll();
    }

    @Test
    public void AddressRepository_findAllAddresses() {
        Assertions.assertEquals(1,addressRepository.findAll().size());
    }

    @Test
    public void AddressRepository_AddAndFindAnAddress() {
        addressRepository.save(new Address("Germany", "Munich","348333","Saebener Strasse","51"));

        Optional<Address> storedAddress = addressRepository.findById(2L);

        Assertions.assertTrue(storedAddress.isPresent());
        Assertions.assertEquals(2,storedAddress.get().getId());
        Assertions.assertEquals("Germany",storedAddress.get().getCountry());
        Assertions.assertEquals("Munich",storedAddress.get().getCity());
        Assertions.assertEquals("348333",storedAddress.get().getPostalCode());
        Assertions.assertEquals("Saebener Strasse",storedAddress.get().getStreet());
        Assertions.assertEquals("51",storedAddress.get().getStreetNumber());

    }
}