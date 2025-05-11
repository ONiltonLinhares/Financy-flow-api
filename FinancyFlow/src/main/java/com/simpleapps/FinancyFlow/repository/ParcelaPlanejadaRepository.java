package com.simpleapps.FinancyFlow.repository;

import com.simpleapps.FinancyFlow.model.GastoPlanejado;
import com.simpleapps.FinancyFlow.model.ParcelaPlanejada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ParcelaPlanejadaRepository extends JpaRepository<ParcelaPlanejada, Long> {
    List<ParcelaPlanejada> findByGastoPlanejado(GastoPlanejado gastoPlanejado);
    List<ParcelaPlanejada> findByMesAno(LocalDate mesAno);
}