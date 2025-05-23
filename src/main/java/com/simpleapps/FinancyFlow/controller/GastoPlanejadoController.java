package com.simpleapps.FinancyFlow.controller;

import com.simpleapps.FinancyFlow.dto.gastoPlanejado.GastoPlanejadoRequestDTO;
import com.simpleapps.FinancyFlow.dto.gastoPlanejado.GastoPlanejadoResponseDTO;
import com.simpleapps.FinancyFlow.service.GastoPlanejadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gastos-planejados")
public class GastoPlanejadoController {

    @Autowired
    private GastoPlanejadoService service;

    @PostMapping
    public ResponseEntity<GastoPlanejadoResponseDTO> criar(@RequestBody GastoPlanejadoRequestDTO dto) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<GastoPlanejadoResponseDTO>> listar(@RequestParam("usuarioId") String usuarioId) {
        return ResponseEntity.ok(service.listarTodos(usuarioId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GastoPlanejadoResponseDTO> atualizar(@PathVariable Long id, @RequestBody GastoPlanejadoRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}