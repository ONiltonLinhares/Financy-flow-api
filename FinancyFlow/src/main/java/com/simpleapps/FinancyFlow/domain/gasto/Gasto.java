package com.simpleapps.FinancyFlow.domain.gasto;

import com.simpleapps.FinancyFlow.domain.categoria.Categoria;
import com.simpleapps.FinancyFlow.domain.usuario.Usuario;
import com.simpleapps.FinancyFlow.enums.TipoGasto;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "gastos")
public class Gasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Column(name = "data_gasto")
    private LocalDate dataGasto;

    @Enumerated(EnumType.STRING)
    private TipoGasto tipo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "origem_id")
    private Long origemId; // Referência opcional
}