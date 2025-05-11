package com.simpleapps.FinancyFlow.repository;

import com.simpleapps.FinancyFlow.model.GastoPlanejado;
import com.simpleapps.FinancyFlow.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GastoPlanejadoRepository extends JpaRepository<GastoPlanejado, Long> {
    List<GastoPlanejado> findByUsuario(Usuario usuario);
}