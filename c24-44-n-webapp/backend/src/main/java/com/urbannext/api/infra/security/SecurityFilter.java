package com.urbannext.api.infra.security;


import com.urbannext.api.domain.usuario.Usuario;
import com.urbannext.api.repositories.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if(request.getRequestURI().equals("/auth/register")) {
            filterChain.doFilter(request, response); // Omite validaciones aquí.
            return;
        }
        var token = this.recoverToken(request);

        if (token == null || token.isEmpty()) {
            // Si no hay token o está vacío.
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return; // Detiene la ejecución aquí.
        }

        var login = tokenService.validateToken(token);

        if(login != null){
            Usuario usuario = usuarioRepository.findByEmail(login)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

            // Agrega autoridades basadas en el tipo de usuario.
            var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getTipoUsuario().name()));

            // Crea un objeto de autenticación para establecer en el contexto de seguridad.
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } else {
            // Si el token es inválido (login == null).
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return; // Detiene la ejecución aquí.

        }

        filterChain.doFilter(request, response); // Continúa solo si todo está bien.
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) return null;

        return authHeader.replace("Bearer ", "").trim(); // Elimina espacios al inicio/final del token

    }
}
