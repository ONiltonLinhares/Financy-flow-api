package com.simpleapps.FinancyFlow.repositories;

import com.simpleapps.FinancyFlow.domain.gastoFixo.GastoFixo;
import com.simpleapps.FinancyFlow.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GastoFixoRepository extends JpaRepository<GastoFixo, Long> {
    List<GastoFixo> findByUsuarioAndAtivoTrue(Usuario usuario);
}
