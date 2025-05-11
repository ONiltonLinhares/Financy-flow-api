package com.simpleapps.FinancyFlow.service;

import com.simpleapps.FinancyFlow.dto.categoria.CategoriaRequestDTO;
import com.simpleapps.FinancyFlow.dto.categoria.CategoriaResponseDTO;
import com.simpleapps.FinancyFlow.model.Categoria;
import com.simpleapps.FinancyFlow.model.Usuario;
import com.simpleapps.FinancyFlow.model.enums.TipoCategoria;
import com.simpleapps.FinancyFlow.repository.CategoriaRepository;
import com.simpleapps.FinancyFlow.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public CategoriaResponseDTO criar(CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        categoria.setTipo(TipoCategoria.valueOf(dto.getTipo()));
        Usuario usuario = usuarioRepository.findById(Long.parseLong(dto.getUsuarioId()))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        categoria.setUsuario(usuario);
        return toResponseDTO(categoriaRepository.save(categoria));
    }

    public List<CategoriaResponseDTO> listar(String usuarioId, String tipo) {
        Usuario usuario = usuarioRepository.findById(Long.parseLong(usuarioId))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        List<Categoria> categorias;
        if (tipo != null) {
            TipoCategoria tipoEnum = TipoCategoria.valueOf(tipo.toUpperCase());
            categorias = categoriaRepository.findByUsuarioAndTipo(usuario, tipoEnum);
        } else {
            categorias = categoriaRepository.findByUsuario(usuario);
        }

        return categorias.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        categoria.setNome(dto.getNome());
        categoria.setTipo(TipoCategoria.valueOf(dto.getTipo()));

        return toResponseDTO(categoriaRepository.save(categoria));
    }

    public void deletar(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoria não encontrada");
        }
        categoriaRepository.deleteById(id);
    }

    private CategoriaResponseDTO toResponseDTO(Categoria categoria) {
        CategoriaResponseDTO dto = new CategoriaResponseDTO();
        dto.setId(categoria.getId().toString());
        dto.setNome(categoria.getNome());
        dto.setTipo(categoria.getTipo().name());
        dto.setUsuarioId(categoria.getUsuario().getId().toString());
        return dto;
    }
}