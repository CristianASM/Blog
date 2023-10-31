package com.blog.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "correo"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", length = 45)
    @NotBlank
    private String firstName;
    @Column(name = "apellido", length = 45)
    @NotBlank
    private String lastName;
    @Column(name = "nombre_usuario")
    @NotBlank
    @Size(min = 4, max = 20)
    private String userName;
    @Column(name = "correo", length = 45)
    @NotBlank
    private String email;
    @Column(name = "contraseña")
    @NotBlank
    @Size(min = 8, max = 20)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "roles_usuarios",
            joinColumns = @JoinColumn(
                    name = "usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "rol_id", referencedColumnName = "id"))
    private Collection<Role> roles;
}
