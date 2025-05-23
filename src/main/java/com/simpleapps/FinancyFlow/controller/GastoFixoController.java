package com.simpleapps.FinancyFlow.controller;

import com.simpleapps.FinancyFlow.dto.gastoFixo.GastoFixoRequestDTO;
import com.simpleapps.FinancyFlow.dto.gastoFixo.GastoFixoResponseDTO;
import com.simpleapps.FinancyFlow.service.GastoFixoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gastos-fixos")
public class GastoFixoController {

    @Autowired
    private GastoFixoService gastoFixoService;

    @PostMapping
    public ResponseEntity<GastoFixoResponseDTO> criar(@RequestBody GastoFixoRequestDTO dto) {
        GastoFixoResponseDTO response = gastoFixoService.criarGastoFixo(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<GastoFixoResponseDTO>> listar(@RequestParam("usuarioId") String usuarioId) {
        List<GastoFixoResponseDTO> response = gastoFixoService.listarTodos(usuarioId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GastoFixoResponseDTO> atualizar(@PathVariable Long id, @RequestBody GastoFixoRequestDTO dto) {
        GastoFixoResponseDTO response = gastoFixoService.atualizarGastoFixo(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        gastoFixoService.deletarGastoFixo(id);
        return ResponseEntity.noContent().build();
    }
}