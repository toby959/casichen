package com.urbannext.api.dto;


public record LoginRequestDTO(
        String email,
        String password,
        String tipoUsuario
        ) {}