package com.urbannext.api.controllers;


import com.urbannext.api.domain.usuario.Address;
import com.urbannext.api.domain.usuario.Usuario;
import com.urbannext.api.dto.LoginRequestDTO;
import com.urbannext.api.dto.RegisterRequestDTO;
import com.urbannext.api.dto.ResponseDTO;
import com.urbannext.api.infra.security.TokenService;
import com.urbannext.api.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO body){
        Usuario usuario = this.repository.findByEmail(body.email()).orElseThrow(()
                -> new RuntimeException("Usuario no encontrado"));
        if(passwordEncoder.matches(body.password(), usuario.getPassword())) {
            String token = this.tokenService.generateToken(usuario);
            return ResponseEntity.ok(new ResponseDTO(usuario.getName(),
                    token, usuario.getTipoUsuario()));
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO body){
        try {
            Optional<Usuario> usuario = this.repository.findByEmail(body.email());

            if (usuario.isEmpty()) {
                Usuario newUsuario = new Usuario();
                newUsuario.setPassword(passwordEncoder.encode(body.password()));
                newUsuario.setEmail(body.email());
                newUsuario.setName(body.name());
                newUsuario.setTipoUsuario(body.tipoUsuario());

                // Asignar campos adicionales
                newUsuario.setPhone(body.phone());
                newUsuario.setDocument(body.document());
                newUsuario.setActive(body.active() != null ? body.active() : true);
                newUsuario.setAddress(new Address(body.address())); // para cargar datos direccion
                this.repository.save(newUsuario);

                String token = this.tokenService.generateToken(newUsuario);
                return ResponseEntity.ok(new ResponseDTO(
                        newUsuario.getName(),
                        token,
                        newUsuario.getTipoUsuario()));
            }
            return ResponseEntity.badRequest().body("El usuario ya existe");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al registar el usuario: "
            + e.getMessage());
        }
    }
}
