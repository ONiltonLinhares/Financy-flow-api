package com.simpleapps.FinancyFlow.repositories;

import com.simpleapps.FinancyFlow.domain.gasto.Gasto;
import com.simpleapps.FinancyFlow.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface GastoRepository extends JpaRepository<Gasto, Long> {
    List<Gasto> findByUsuarioAndDataGastoBetween(Usuario usuario, LocalDate start, LocalDate end);
}
