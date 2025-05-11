package com.simpleapps.FinancyFlow.repositories;

import com.simpleapps.FinancyFlow.domain.gastoPlanejado.GastoPlanejado;
import com.simpleapps.FinancyFlow.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GastoPlanejadoRepository extends JpaRepository<GastoPlanejado, Long> {
    List<GastoPlanejado> findByUsuario(Usuario usuario);
}