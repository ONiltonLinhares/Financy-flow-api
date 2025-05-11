package com.simpleapps.FinancyFlow.repositories;

import com.simpleapps.FinancyFlow.domain.categoria.Categoria;
import com.simpleapps.FinancyFlow.domain.usuario.Usuario;
import com.simpleapps.FinancyFlow.enums.TipoCategoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByUsuario(Usuario usuario);
    List<Categoria> findByUsuarioAndTipo(Usuario usuario, TipoCategoria tipo);
}
