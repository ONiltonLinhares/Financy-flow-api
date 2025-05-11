package com.simpleapps.FinancyFlow.repository;

import com.simpleapps.FinancyFlow.model.Renda;
import com.simpleapps.FinancyFlow.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RendaRepository extends JpaRepository<Renda, Long> {
    List<Renda> findByUsuarioAndDataRecebimentoBetween(Usuario usuario, LocalDate start, LocalDate end);
}