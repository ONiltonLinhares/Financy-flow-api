package com.simpleapps.FinancyFlow.controller;

import com.simpleapps.FinancyFlow.dto.gasto.GastoRequestDTO;
import com.simpleapps.FinancyFlow.dto.gasto.GastoResponseDTO;
import com.simpleapps.FinancyFlow.service.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gastos")
public class GastoController {

    @Autowired
    private GastoService gastoService;

    @PostMapping
    public ResponseEntity<GastoResponseDTO> criarGasto(@RequestBody GastoRequestDTO dto) {
        GastoResponseDTO response = gastoService.criarGasto(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<GastoResponseDTO>> listarGastos(
            @RequestParam("usuarioId") String usuarioId,
            @RequestParam(value = "mes", required = false) String mes,
            @RequestParam(value = "ano", required = false) String ano,
            @RequestParam(value = "categoriaId", required = false) String categoriaId,
            @RequestParam(value = "tipo", required = false) String tipo
    ) {
        List<GastoResponseDTO> response = gastoService.listarGastos(usuarioId, mes, ano, categoriaId, tipo);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GastoResponseDTO> obterGasto(@PathVariable Long id) {
        GastoResponseDTO response = gastoService.obterGastoPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GastoResponseDTO> atualizarGasto(@PathVariable Long id, @RequestBody GastoRequestDTO dto) {
        GastoResponseDTO response = gastoService.atualizarGasto(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGasto(@PathVariable Long id) {
        gastoService.deletarGasto(id);
        return ResponseEntity.noContent().build();
    }
}