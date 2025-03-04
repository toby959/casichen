package com.urbannext.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.urbannext.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

public String generateToken(Usuario usuario) {
    try {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        String token;
        token = JWT.create()
                .withIssuer("urbannext-api") // name API
                .withSubject(usuario.getEmail()) // email User (Subject)
                .withExpiresAt(generateExpirationDate()) // Time expiration
                .sign(algorithm);
        return token;
     } catch (JWTCreationException exception) {
        throw new RuntimeException("Error al generar el token de autenticación");
    }
}

public String validateToken(String token) {
    try {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .withIssuer("urbannext-api")
                .build()
                .verify(token)
                .getSubject();

    }catch (JWTVerificationException exception) {
        throw new RuntimeException("Token de autenticación inválido", exception);
    }

}

   private Instant generateExpirationDate() {
    return LocalDateTime.now().plusMonths(1)
            .toInstant(ZoneOffset.of("-03:00"));
   }
}
