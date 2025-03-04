package com.urbannext.api.dto;

import com.urbannext.api.domain.usuario.TipoUsuario;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequestDTO(
        @NotBlank
        String name,
        @NotBlank @Email
        String email,
        @NotBlank
        String password,
        @NotBlank
        TipoUsuario tipoUsuario,
        @NotBlank
        String phone,
        @NotBlank
        String document,
        Boolean active,
        @NotBlank @Valid
        DataAddress address
) {}