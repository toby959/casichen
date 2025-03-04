package com.urbannext.api.domain.usuario;

import com.urbannext.api.dto.DataAddress;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String street;

    private String number;

    private String district;

    private String city;

    public Address(DataAddress address) {
        this.street = address.street();
        this.number = address.number();
        this.district = address.district();
        this.city = address.city();
    }

    public Address update(DataAddress address) {
        this.street = address.street();
        this.number = address.number();
        this.district = address.district();
        this.city = address.city();
        return this;
    }

}
