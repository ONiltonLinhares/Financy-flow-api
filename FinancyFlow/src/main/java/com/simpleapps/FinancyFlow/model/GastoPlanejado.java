package com.simpleapps.FinancyFlow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "gastos_planejados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GastoPlanejado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "duracao_meses")
    private Integer duracaoMeses;
}