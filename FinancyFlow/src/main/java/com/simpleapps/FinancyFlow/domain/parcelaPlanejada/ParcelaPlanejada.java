package com.simpleapps.FinancyFlow.domain.parcelaPlanejada;

import com.simpleapps.FinancyFlow.domain.gastoPlanejado.GastoPlanejado;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "parcelas_planejadas")
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