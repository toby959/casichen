package com.urbannext.api.dto;

import jakarta.validation.constraints.NotBlank;

public record DataAddress(
        @NotBlank
        String street,
        @NotBlank
        String district,
        @NotBlank
        String city,
        @NotBlank
        String number

) {
}
