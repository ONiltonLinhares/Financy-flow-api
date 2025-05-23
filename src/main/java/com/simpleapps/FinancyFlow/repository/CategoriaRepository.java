package com.simpleapps.FinancyFlow.repository;

import com.simpleapps.FinancyFlow.model.Categoria;
import com.simpleapps.FinancyFlow.model.Usuario;
import com.simpleapps.FinancyFlow.model.enums.TipoCategoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByUsuario(Usuario usuario);
    List<Categoria> findByUsuarioAndTipo(Usuario usuario, TipoCategoria tipo);
}
