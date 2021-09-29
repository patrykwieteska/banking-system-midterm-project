package com.ironhack.midtermproject.dao.owners;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThirdPart extends Owner {

    @JoinColumn(unique = true)
    private String hashedKey;

    public ThirdPart(String name, String hashedKey) {
        super(name);
        this.hashedKey = hashedKey;
    }
}
