package com.ironhack.midtermproject.common;

import com.ironhack.midtermproject.dao.Address;

import java.util.List;

public class Validator {

    public static Long checkExistingAddress(List<Address> storedAddressed, Address address) {

        for (Address a: storedAddressed
             ) {
            if(a.equals(address))
                return a.getId();
        }

        return 0L;
    }
}
