package com.simpleapps.FinancyFlow.repository;

import com.simpleapps.FinancyFlow.model.SaldoMensal;
import com.simpleapps.FinancyFlow.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SaldoMensalRepository extends JpaRepository<SaldoMensal, Long> {
    Optional<SaldoMensal> findByUsuarioAndMesAno(Usuario usuario, LocalDate mesAno);
    List<SaldoMensal> findAllByUsuarioOrderByMesAnoDesc(Usuario usuario);
    boolean existsByUsuarioAndMesAno(Usuario usuario, LocalDate mesAno);
}