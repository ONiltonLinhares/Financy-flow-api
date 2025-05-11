package com.simpleapps.FinancyFlow.domain.gastoPlanejado;

import com.simpleapps.FinancyFlow.domain.categoria.Categoria;
import com.simpleapps.FinancyFlow.domain.usuario.Usuario;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "gastos_planejados")
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