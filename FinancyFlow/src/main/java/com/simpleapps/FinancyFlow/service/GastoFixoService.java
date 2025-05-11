package com.simpleapps.FinancyFlow.service;

import com.simpleapps.FinancyFlow.dto.gastoFixo.GastoFixoRequestDTO;
import com.simpleapps.FinancyFlow.dto.gastoFixo.GastoFixoResponseDTO;
import com.simpleapps.FinancyFlow.model.Categoria;
import com.simpleapps.FinancyFlow.model.GastoFixo;
import com.simpleapps.FinancyFlow.model.Usuario;
import com.simpleapps.FinancyFlow.repository.CategoriaRepository;
import com.simpleapps.FinancyFlow.repository.GastoFixoRepository;
import com.simpleapps.FinancyFlow.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GastoFixoService {

    @Autowired
    private GastoFixoRepository gastoFixoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public GastoFixoResponseDTO criarGastoFixo(GastoFixoRequestDTO dto) {
        GastoFixo gasto = fromRequestDTO(dto);
        GastoFixo salvo = gastoFixoRepository.save(gasto);
        return toResponseDTO(salvo);
    }

    public List<GastoFixoResponseDTO> listarTodos(String usuarioId) {
        Usuario usuario = usuarioRepository.findById(Long.parseLong(usuarioId))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return gastoFixoRepository.findByUsuarioAndAtivoTrue(usuario)
                .stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public GastoFixoResponseDTO atualizarGastoFixo(Long id, GastoFixoRequestDTO dto) {
        GastoFixo gasto = gastoFixoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gasto fixo não encontrado"));

        gasto.setValor(new BigDecimal(dto.getValor()));
        gasto.setDescricao(dto.getDescricao());
        gasto.setDiaRecorrente(Integer.parseInt(dto.getDiaRecorrente()));
        gasto.setAtivo(Boolean.parseBoolean(dto.getAtivo()));

        Categoria categoria = categoriaRepository.findById(Long.parseLong(dto.getCategoriaId()))
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        gasto.setCategoria(categoria);

        GastoFixo atualizado = gastoFixoRepository.save(gasto);
        return toResponseDTO(atualizado);
    }

    public void deletarGastoFixo(Long id) {
        if (!gastoFixoRepository.existsById(id)) {
            throw new RuntimeException("Gasto fixo não encontrado");
        }
        gastoFixoRepository.deleteById(id);
    }

    private GastoFixoResponseDTO toResponseDTO(GastoFixo gasto) {
        GastoFixoResponseDTO dto = new GastoFixoResponseDTO();
        dto.setId(gasto.getId().toString());
        dto.setValor(gasto.getValor().toString());
        dto.setDescricao(gasto.getDescricao());
        dto.setDiaRecorrente(gasto.getDiaRecorrente().toString());
        dto.setCategoriaId(gasto.getCategoria().getId().toString());
        dto.setUsuarioId(gasto.getUsuario().getId().toString());
        dto.setAtivo(gasto.getAtivo().toString());
        return dto;
    }

    private GastoFixo fromRequestDTO(GastoFixoRequestDTO dto) {
        GastoFixo gasto = new GastoFixo();
        gasto.setValor(new BigDecimal(dto.getValor()));
        gasto.setDescricao(dto.getDescricao());
        gasto.setDiaRecorrente(Integer.parseInt(dto.getDiaRecorrente()));
        gasto.setAtivo(Boolean.parseBoolean(dto.getAtivo()));

        Categoria categoria = categoriaRepository.findById(Long.parseLong(dto.getCategoriaId()))
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        Usuario usuario = usuarioRepository.findById(Long.parseLong(dto.getUsuarioId()))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        gasto.setCategoria(categoria);
        gasto.setUsuario(usuario);
        return gasto;
    }
}