package com.simpleapps.FinancyFlow.controller;

import com.simpleapps.FinancyFlow.dto.categoria.CategoriaRequestDTO;
import com.simpleapps.FinancyFlow.dto.categoria.CategoriaResponseDTO;
import com.simpleapps.FinancyFlow.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> criar(@RequestBody CategoriaRequestDTO dto) {
        return ResponseEntity.ok(categoriaService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listar(
            @RequestParam("usuarioId") String usuarioId,
            @RequestParam(value = "tipo", required = false) String tipo) {
        return ResponseEntity.ok(categoriaService.listar(usuarioId, tipo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable Long id, @RequestBody CategoriaRequestDTO dto) {
        return ResponseEntity.ok(categoriaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}