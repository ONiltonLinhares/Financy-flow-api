package com.simpleapps.FinancyFlow.service;


import com.simpleapps.FinancyFlow.dto.renda.RendaRequestDTO;
import com.simpleapps.FinancyFlow.dto.renda.RendaResponseDTO;
import com.simpleapps.FinancyFlow.model.Categoria;
import com.simpleapps.FinancyFlow.model.Renda;
import com.simpleapps.FinancyFlow.model.Usuario;
import com.simpleapps.FinancyFlow.model.enums.TipoRenda;
import com.simpleapps.FinancyFlow.repository.CategoriaRepository;
import com.simpleapps.FinancyFlow.repository.RendaRepository;
import com.simpleapps.FinancyFlow.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RendaService {

    @Autowired
    private RendaRepository rendaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public RendaResponseDTO criarRenda(RendaRequestDTO dto) {
        Renda renda = fromRequestDTO(dto);
        Renda salva = rendaRepository.save(renda);
        return toResponseDTO(salva);
    }

    public List<RendaResponseDTO> listarRendas(String usuarioId, String mes, String ano) {
        Usuario usuario = usuarioRepository.findById(Long.parseLong(usuarioId))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (mes != null && ano != null) {
            LocalDate inicio = LocalDate.of(Integer.parseInt(ano), Integer.parseInt(mes), 1);
            LocalDate fim = inicio.withDayOfMonth(inicio.lengthOfMonth());
            return rendaRepository.findByUsuarioAndDataRecebimentoBetween(usuario, inicio, fim)
                    .stream().map(this::toResponseDTO).collect(Collectors.toList());
        } else {
            return rendaRepository.findByUsuarioAndDataRecebimentoBetween(usuario, LocalDate.MIN, LocalDate.MAX)
                    .stream().map(this::toResponseDTO).collect(Collectors.toList());
        }
    }

    public RendaResponseDTO obterRendaPorId(Long id) {
        Renda renda = rendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Renda não encontrada"));
        return toResponseDTO(renda);
    }

    public RendaResponseDTO atualizarRenda(Long id, RendaRequestDTO dto) {
        Renda renda = rendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Renda não encontrada"));

        renda.setValor(new BigDecimal(dto.getValor()));
        renda.setDescricao(dto.getDescricao());
        renda.setDataRecebimento(LocalDate.parse(dto.getDataRecebimento()));
        renda.setTipo(TipoRenda.valueOf(dto.getTipo()));

        Categoria categoria = categoriaRepository.findById(Long.parseLong(dto.getCategoriaId()))
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        renda.setCategoria(categoria);

        Renda atualizada = rendaRepository.save(renda);
        return toResponseDTO(atualizada);
    }

    public void deletarRenda(Long id) {
        if (!rendaRepository.existsById(id)) {
            throw new RuntimeException("Renda não encontrada");
        }
        rendaRepository.deleteById(id);
    }

    private RendaResponseDTO toResponseDTO(Renda renda) {
        RendaResponseDTO dto = new RendaResponseDTO();
        dto.setId(renda.getId().toString());
        dto.setValor(renda.getValor().toString());
        dto.setDescricao(renda.getDescricao());
        dto.setDataRecebimento(renda.getDataRecebimento().toString());
        dto.setTipo(renda.getTipo().name());
        dto.setCategoriaId(renda.getCategoria().getId().toString());
        dto.setUsuarioId(renda.getUsuario().getId().toString());
        return dto;
    }

    private Renda fromRequestDTO(RendaRequestDTO dto) {
        Renda renda = new Renda();
        renda.setValor(new BigDecimal(dto.getValor()));
        renda.setDescricao(dto.getDescricao());
        renda.setDataRecebimento(LocalDate.parse(dto.getDataRecebimento()));
        renda.setTipo(TipoRenda.valueOf(dto.getTipo()));

        Categoria categoria = categoriaRepository.findById(Long.parseLong(dto.getCategoriaId()))
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        Usuario usuario = usuarioRepository.findById(Long.parseLong(dto.getUsuarioId()))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        renda.setCategoria(categoria);
        renda.setUsuario(usuario);
        return renda;
    }
}