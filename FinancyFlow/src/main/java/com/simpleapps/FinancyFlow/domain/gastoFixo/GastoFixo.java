package com.simpleapps.FinancyFlow.domain.gastoFixo;

import com.simpleapps.FinancyFlow.domain.categoria.Categoria;
import com.simpleapps.FinancyFlow.domain.usuario.Usuario;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "gastos_fixos")
public class GastoFixo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Column(name = "dia_recorrente")
    private Integer diaRecorrente;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private Boolean ativo = true;
}