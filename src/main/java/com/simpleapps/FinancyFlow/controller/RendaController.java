package com.simpleapps.FinancyFlow.controller;

import com.simpleapps.FinancyFlow.dto.renda.RendaRequestDTO;
import com.simpleapps.FinancyFlow.dto.renda.RendaResponseDTO;
import com.simpleapps.FinancyFlow.service.RendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rendas")
public class RendaController {

    @Autowired
    private RendaService rendaService;

    @PostMapping
    public ResponseEntity<RendaResponseDTO> criarRenda(@RequestBody RendaRequestDTO dto) {
        RendaResponseDTO response = rendaService.criarRenda(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<RendaResponseDTO>> listarRendas(
            @RequestParam("usuarioId") String usuarioId,
            @RequestParam(value = "mes", required = false) String mes,
            @RequestParam(value = "ano", required = false) String ano) {
        List<RendaResponseDTO> response = rendaService.listarRendas(usuarioId, mes, ano);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RendaResponseDTO> obterRenda(@PathVariable Long id) {
        RendaResponseDTO response = rendaService.obterRendaPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RendaResponseDTO> atualizarRenda(@PathVariable Long id, @RequestBody RendaRequestDTO dto) {
        RendaResponseDTO response = rendaService.atualizarRenda(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarRenda(@PathVariable Long id) {
        rendaService.deletarRenda(id);
        return ResponseEntity.noContent().build();
    }
}