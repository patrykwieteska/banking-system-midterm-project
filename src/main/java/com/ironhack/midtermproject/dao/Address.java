package com.ironhack.midtermproject.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String country;
    private String city;
    private String postalCode;
    private String street;
    private String streetNumber;

    public Address(String country, String city, String postalCode, String street, String streetNumber) {
        this.country = country;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.streetNumber = streetNumber;
    }


    @Override
    public boolean equals(Object o) {
        if(o == this)
            return true;

        if(!(o instanceof Address)) {
            return false;
        }

        Address address = (Address) o;

        return address.getCountry().trim().compareTo(this.getCountry()) == 0 &&
                address.getCity().trim().compareTo(this.getCity()) == 0 &&
                address.getStreet().trim().compareTo(this.getStreet()) == 0 &&
                address.getPostalCode().trim().compareTo(this.getPostalCode()) == 0 &&
                address.getStreetNumber().trim().compareTo(this.getStreetNumber()) == 0;
    }
}
