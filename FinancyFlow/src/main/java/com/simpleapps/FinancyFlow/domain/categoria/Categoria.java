package com.simpleapps.FinancyFlow.domain.categoria;

import com.simpleapps.FinancyFlow.domain.usuario.Usuario;
import com.simpleapps.FinancyFlow.enums.TipoCategoria;
import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoCategoria tipo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}