package com.simpleapps.FinancyFlow.repository;

import com.simpleapps.FinancyFlow.model.Gasto;
import com.simpleapps.FinancyFlow.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface GastoRepository extends JpaRepository<Gasto, Long> {
    List<Gasto> findByUsuarioAndDataGastoBetween(Usuario usuario, LocalDate start, LocalDate end);
}
