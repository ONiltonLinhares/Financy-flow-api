package com.simpleapps.FinancyFlow.controller;

import com.simpleapps.FinancyFlow.service.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> listar(@RequestParam("usuarioId") String usuarioId) {
        return ResponseEntity.ok(historicoService.listarHistorico(usuarioId));
    }

    @GetMapping("/{ano}/{mes}")
    public ResponseEntity<Map<String, Object>> detalhar(@PathVariable int ano, @PathVariable int mes,
                                                        @RequestParam("usuarioId") String usuarioId) {
        return ResponseEntity.ok(historicoService.detalharMes(usuarioId, ano, mes));
    }
}