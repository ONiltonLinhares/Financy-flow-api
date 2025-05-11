package com.simpleapps.FinancyFlow.domain.renda;

import com.simpleapps.FinancyFlow.domain.categoria.Categoria;
import com.simpleapps.FinancyFlow.domain.usuario.Usuario;
import com.simpleapps.FinancyFlow.enums.TipoRenda;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "rendas")
public class Renda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Column(name = "data_recebimento")
    private LocalDate dataRecebimento;

    @Enumerated(EnumType.STRING)
    private TipoRenda tipo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}

