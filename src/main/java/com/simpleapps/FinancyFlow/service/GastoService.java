package com.simpleapps.FinancyFlow.service;

import com.simpleapps.FinancyFlow.dto.gasto.GastoRequestDTO;
import com.simpleapps.FinancyFlow.dto.gasto.GastoResponseDTO;
import com.simpleapps.FinancyFlow.model.Categoria;
import com.simpleapps.FinancyFlow.model.Gasto;
import com.simpleapps.FinancyFlow.model.Usuario;
import com.simpleapps.FinancyFlow.model.enums.TipoGasto;
import com.simpleapps.FinancyFlow.repository.CategoriaRepository;
import com.simpleapps.FinancyFlow.repository.GastoRepository;
import com.simpleapps.FinancyFlow.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GastoService {

    @Autowired
    private GastoRepository gastoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public GastoResponseDTO criarGasto(GastoRequestDTO dto) {
        Gasto gasto = fromRequestDTO(dto);
        Gasto salvo = gastoRepository.save(gasto);
        return toResponseDTO(salvo);
    }

    public List<GastoResponseDTO> listarGastos(String usuarioId, String mes, String ano,
                                               String categoriaId, String tipo) {
        Usuario usuario = usuarioRepository.findById(Long.parseLong(usuarioId))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        LocalDate inicio = LocalDate.MIN;
        LocalDate fim = LocalDate.MAX;

        if (mes != null && ano != null) {
            inicio = LocalDate.of(Integer.parseInt(ano), Integer.parseInt(mes), 1);
            fim = inicio.withDayOfMonth(inicio.lengthOfMonth());
        }

        List<Gasto> gastos = gastoRepository.findByUsuarioAndDataGastoBetween(usuario, inicio, fim);

        if (categoriaId != null) {
            gastos = gastos.stream()
                    .filter(g -> g.getCategoria() != null &&
                            g.getCategoria().getId().equals(Long.parseLong(categoriaId)))
                    .collect(Collectors.toList());
        }

        if (tipo != null) {
            TipoGasto tipoGasto = TipoGasto.valueOf(tipo);
            gastos = gastos.stream()
                    .filter(g -> g.getTipo() == tipoGasto)
                    .collect(Collectors.toList());
        }

        return gastos.stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public GastoResponseDTO obterGastoPorId(Long id) {
        Gasto gasto = gastoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gasto não encontrado"));
        return toResponseDTO(gasto);
    }

    public GastoResponseDTO atualizarGasto(Long id, GastoRequestDTO dto) {
        Gasto gasto = gastoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gasto não encontrado"));

        gasto.setValor(new BigDecimal(dto.getValor()));
        gasto.setDescricao(dto.getDescricao());
        gasto.setDataGasto(LocalDate.parse(dto.getDataGasto()));
        gasto.setTipo(TipoGasto.valueOf(dto.getTipo()));

        Categoria categoria = categoriaRepository.findById(Long.parseLong(dto.getCategoriaId()))
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        gasto.setCategoria(categoria);

        gasto.setOrigemId(dto.getOrigemId() != null ? Long.parseLong(dto.getOrigemId()) : null);

        Gasto atualizado = gastoRepository.save(gasto);
        return toResponseDTO(atualizado);
    }

    public void deletarGasto(Long id) {
        if (!gastoRepository.existsById(id)) {
            throw new RuntimeException("Gasto não encontrado");
        }
        gastoRepository.deleteById(id);
    }

    private GastoResponseDTO toResponseDTO(Gasto gasto) {
        GastoResponseDTO dto = new GastoResponseDTO();
        dto.setId(gasto.getId().toString());
        dto.setValor(gasto.getValor().toString());
        dto.setDescricao(gasto.getDescricao());
        dto.setDataGasto(gasto.getDataGasto().toString());
        dto.setTipo(gasto.getTipo().name());
        dto.setCategoriaId(gasto.getCategoria() != null ? gasto.getCategoria().getId().toString() : null);
        dto.setUsuarioId(gasto.getUsuario().getId().toString());
        dto.setOrigemId(gasto.getOrigemId() != null ? gasto.getOrigemId().toString() : null);
        return dto;
    }

    private Gasto fromRequestDTO(GastoRequestDTO dto) {
        Gasto gasto = new Gasto();
        gasto.setValor(new BigDecimal(dto.getValor()));
        gasto.setDescricao(dto.getDescricao());
        gasto.setDataGasto(LocalDate.parse(dto.getDataGasto()));
        gasto.setTipo(TipoGasto.valueOf(dto.getTipo()));

        Categoria categoria = categoriaRepository.findById(Long.parseLong(dto.getCategoriaId()))
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        Usuario usuario = usuarioRepository.findById(Long.parseLong(dto.getUsuarioId()))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        gasto.setCategoria(categoria);
        gasto.setUsuario(usuario);
        gasto.setOrigemId(dto.getOrigemId() != null ? Long.parseLong(dto.getOrigemId()) : null);
        return gasto;
    }
}