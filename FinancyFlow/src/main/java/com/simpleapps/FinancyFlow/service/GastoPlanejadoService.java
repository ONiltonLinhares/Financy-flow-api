package com.simpleapps.FinancyFlow.service;

import com.simpleapps.FinancyFlow.dto.gastoPlanejado.GastoPlanejadoRequestDTO;
import com.simpleapps.FinancyFlow.dto.gastoPlanejado.GastoPlanejadoResponseDTO;
import com.simpleapps.FinancyFlow.model.Categoria;
import com.simpleapps.FinancyFlow.model.GastoPlanejado;
import com.simpleapps.FinancyFlow.model.Usuario;
import com.simpleapps.FinancyFlow.repository.CategoriaRepository;
import com.simpleapps.FinancyFlow.repository.GastoPlanejadoRepository;
import com.simpleapps.FinancyFlow.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GastoPlanejadoService {

    @Autowired
    private GastoPlanejadoRepository gastoPlanejadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public GastoPlanejadoResponseDTO criar(GastoPlanejadoRequestDTO dto) {
        GastoPlanejado gasto = fromRequestDTO(dto);
        GastoPlanejado salvo = gastoPlanejadoRepository.save(gasto);
        return toResponseDTO(salvo);
    }

    public List<GastoPlanejadoResponseDTO> listarTodos(String usuarioId) {
        Usuario usuario = usuarioRepository.findById(Long.parseLong(usuarioId))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return gastoPlanejadoRepository.findByUsuario(usuario)
                .stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public GastoPlanejadoResponseDTO atualizar(Long id, GastoPlanejadoRequestDTO dto) {
        GastoPlanejado gasto = gastoPlanejadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gasto planejado não encontrado"));

        gasto.setDescricao(dto.getDescricao());
        gasto.setDataInicio(LocalDate.parse(dto.getDataInicio()));
        gasto.setDuracaoMeses(Integer.parseInt(dto.getDuracaoMeses()));

        Categoria categoria = categoriaRepository.findById(Long.parseLong(dto.getCategoriaId()))
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        gasto.setCategoria(categoria);

        GastoPlanejado atualizado = gastoPlanejadoRepository.save(gasto);
        return toResponseDTO(atualizado);
    }

    public void deletar(Long id) {
        if (!gastoPlanejadoRepository.existsById(id)) {
            throw new RuntimeException("Gasto planejado não encontrado");
        }
        gastoPlanejadoRepository.deleteById(id);
    }

    private GastoPlanejadoResponseDTO toResponseDTO(GastoPlanejado gasto) {
        GastoPlanejadoResponseDTO dto = new GastoPlanejadoResponseDTO();
        dto.setId(gasto.getId().toString());
        dto.setDescricao(gasto.getDescricao());
        dto.setDataInicio(gasto.getDataInicio().toString());
        dto.setDuracaoMeses(gasto.getDuracaoMeses().toString());
        dto.setCategoriaId(gasto.getCategoria().getId().toString());
        dto.setUsuarioId(gasto.getUsuario().getId().toString());
        return dto;
    }

    private GastoPlanejado fromRequestDTO(GastoPlanejadoRequestDTO dto) {
        GastoPlanejado gasto = new GastoPlanejado();
        gasto.setDescricao(dto.getDescricao());
        gasto.setDataInicio(LocalDate.parse(dto.getDataInicio()));
        gasto.setDuracaoMeses(Integer.parseInt(dto.getDuracaoMeses()));

        Categoria categoria = categoriaRepository.findById(Long.parseLong(dto.getCategoriaId()))
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        Usuario usuario = usuarioRepository.findById(Long.parseLong(dto.getUsuarioId()))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        gasto.setCategoria(categoria);
        gasto.setUsuario(usuario);
        return gasto;
    }
}