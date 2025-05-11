package com.simpleapps.FinancyFlow.repository;

import com.simpleapps.FinancyFlow.model.GastoFixo;
import com.simpleapps.FinancyFlow.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GastoFixoRepository extends JpaRepository<GastoFixo, Long> {
    List<GastoFixo> findByUsuarioAndAtivoTrue(Usuario usuario);
}
