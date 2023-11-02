package com.blog.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Builder
@Table(name = "usuarios", uniqueConstraints = { @UniqueConstraint(columnNames = "correo"), @UniqueConstraint(columnNames = "nombre_usuario") })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", length = 45)
    private String firstName;
    @Column(name = "apellido", length = 45)
    private String lastName;
    @Column(name = "nombre_usuario")
    private String userName;
    @Column(name = "correo", length = 45)
    private String email;
    @Column(name = "contraseña")
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
