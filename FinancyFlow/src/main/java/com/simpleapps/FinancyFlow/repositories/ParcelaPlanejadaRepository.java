package com.simpleapps.FinancyFlow.repositories;

import com.simpleapps.FinancyFlow.domain.gastoPlanejado.GastoPlanejado;
import com.simpleapps.FinancyFlow.domain.parcelaPlanejada.ParcelaPlanejada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ParcelaPlanejadaRepository extends JpaRepository<ParcelaPlanejada, Long> {
    List<ParcelaPlanejada> findByGastoPlanejado(GastoPlanejado gastoPlanejado);
    List<ParcelaPlanejada> findByMesAno(LocalDate mesAno);
}