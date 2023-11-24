package com.blog.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Table(name = "publicaciones")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titulo", length = 200)
    @NotBlank
    private String title;
    @Column(name = "articulo", columnDefinition = "LONGTEXT")
    @NotBlank
    private String body;
    @Column(name = "imagen")
    private String image;
    @Column(name = "fecha_creacion")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime createdDate;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User user;
}
