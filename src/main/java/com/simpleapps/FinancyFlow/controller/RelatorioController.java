package com.simpleapps.FinancyFlow.controller;

import com.simpleapps.FinancyFlow.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/mensal")
    public ResponseEntity<Map<String, Object>> relatorioAtual(@RequestParam("usuarioId") String usuarioId) {
        LocalDate hoje = LocalDate.now();
        Map<String, Object> response = relatorioService.gerarResumoMensal(usuarioId, hoje.getYear(), hoje.getMonthValue());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/mensal/{ano}/{mes}")
    public ResponseEntity<Map<String, Object>> relatorioMensal(@PathVariable int ano, @PathVariable int mes,
                                                               @RequestParam("usuarioId") String usuarioId) {
        Map<String, Object> response = relatorioService.gerarResumoMensal(usuarioId, ano, mes);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/saldo/{ano}/{mes}")
    public ResponseEntity<String> saldoMensal(@PathVariable int ano, @PathVariable int mes,
                                              @RequestParam("usuarioId") String usuarioId) {
        String saldo = relatorioService.obterSaldoSemEconomias(usuarioId, ano, mes);
        return ResponseEntity.ok(saldo);
    }
}