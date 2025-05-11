package com.simpleapps.FinancyFlow.domain.saldoMensal;

import com.simpleapps.FinancyFlow.domain.usuario.Usuario;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "saldos_mensais", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"usuario_id", "mes_ano"})
})
public class SaldoMensal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "mes_ano")
    private LocalDate mesAno;

    @Column(name = "total_rendas")
    private BigDecimal totalRendas = BigDecimal.ZERO;

    @Column(name = "total_gastos")
    private BigDecimal totalGastos = BigDecimal.ZERO;

    @Column(name = "saldo_final")
    private BigDecimal saldoFinal = BigDecimal.ZERO;

    @Column(name = "economia_gerada")
    private BigDecimal economiaGerada = BigDecimal.ZERO;
}