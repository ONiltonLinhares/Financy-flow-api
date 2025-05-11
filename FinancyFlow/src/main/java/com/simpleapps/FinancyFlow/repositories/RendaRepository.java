package com.simpleapps.FinancyFlow.repositories;

import com.simpleapps.FinancyFlow.domain.renda.Renda;
import com.simpleapps.FinancyFlow.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RendaRepository extends JpaRepository<Renda, Long> {
    List<Renda> findByUsuarioAndDataRecebimentoBetween(Usuario usuario, LocalDate start, LocalDate end);
}