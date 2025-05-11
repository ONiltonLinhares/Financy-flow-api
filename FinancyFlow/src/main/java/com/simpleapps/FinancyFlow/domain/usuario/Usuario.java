package com.simpleapps.FinancyFlow.domain.usuario;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senhaHash;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm = LocalDateTime.now();
}