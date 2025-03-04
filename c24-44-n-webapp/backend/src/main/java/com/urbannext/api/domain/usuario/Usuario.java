package com.urbannext.api.domain.usuario;


import com.urbannext.api.dto.RegisterRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar en blanco")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    @Column(length = 100)
    private String name;

    @NotBlank(message = "El email no puede estar en blanco")
    @Email(message = "El email debe ser válido")
    @Size(max = 100, message = "El email no puede tener más de 100 caracteres")
    @Column(length = 100, unique = true)
    private String email;

    @NotBlank(message = "La contraseña no puede estar en blanco")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
            message = "La contraseña debe tener al menos 8 caracteres, incluir al menos un número," +
                    " una letra mayúscula, una letra minúscula y un carácter especial")
    @Column(length = 255)
    private String password;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message =
            "El número de teléfono debe ser válido y contener entre 10 y 15 dígitos")
    private String phone;

    @NotBlank(message = "El documento no puede estar en blanco")
    @Pattern(regexp = "^[0-9]+$", message = "El documento debe contener solo números")
    private String document;

    @Column(columnDefinition = "BIT(1)")
    private Boolean active;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @Embedded
    private Address address;


    public Usuario(RegisterRequestDTO registerRequestDTO) {
        this.name = registerRequestDTO.name();
        this.email = registerRequestDTO.email();
        this.password = registerRequestDTO.password();
        this.phone = registerRequestDTO.phone();
        this.document = registerRequestDTO.document();
        this.active = registerRequestDTO.active();
        this.tipoUsuario = registerRequestDTO.tipoUsuario();
        this.address = new Address(registerRequestDTO.address());
    }



}