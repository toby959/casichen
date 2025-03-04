package com.urbannext.api.dto;

import com.urbannext.api.domain.usuario.TipoUsuario;

public record ResponseDTO(String name, String token, TipoUsuario tipoUsuario) {}