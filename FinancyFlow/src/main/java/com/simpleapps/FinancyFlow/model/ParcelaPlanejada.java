package com.simpleapps.FinancyFlow.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "parcelas_planejadas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParcelaPlanejada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gasto_planejado_id")
    private GastoPlanejado gastoPlanejado;

    @Column(name = "mes_ano")
    private LocalDate mesAno;

    private BigDecimal valor;

    @Column(name = "foi_convertido_em_gasto")
    private Boolean foiConvertidoEmGasto = false;
}