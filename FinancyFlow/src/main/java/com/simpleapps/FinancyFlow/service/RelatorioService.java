package com.simpleapps.FinancyFlow.service;

import com.simpleapps.FinancyFlow.model.Gasto;
import com.simpleapps.FinancyFlow.model.Renda;
import com.simpleapps.FinancyFlow.model.Usuario;
import com.simpleapps.FinancyFlow.model.enums.TipoRenda;
import com.simpleapps.FinancyFlow.repository.GastoRepository;
import com.simpleapps.FinancyFlow.repository.RendaRepository;
import com.simpleapps.FinancyFlow.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    @Autowired
    private GastoRepository gastoRepository;

    @Autowired
    private RendaRepository rendaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Map<String, Object> gerarResumoMensal(String usuarioId, int ano, int mes) {
        Usuario usuario = getUsuario(usuarioId);
        LocalDate inicio = LocalDate.of(ano, mes, 1);
        LocalDate fim = inicio.withDayOfMonth(inicio.lengthOfMonth());

        List<Gasto> gastos = gastoRepository.findByUsuarioAndDataGastoBetween(usuario, inicio, fim);
        List<Renda> rendas = rendaRepository.findByUsuarioAndDataRecebimentoBetween(usuario, inicio, fim);

        BigDecimal totalGastos = gastos.stream()
                .map(Gasto::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalRendas = rendas.stream()
                .map(Renda::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<String, BigDecimal> gastosPorCategoria = gastos.stream()
                .filter(g -> g.getCategoria() != null)
                .collect(Collectors.groupingBy(
                        g -> g.getCategoria().getNome(),
                        Collectors.reducing(BigDecimal.ZERO, Gasto::getValor, BigDecimal::add)
                ));

        BigDecimal saldo = totalRendas.subtract(totalGastos);

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("mes", mes);
        resultado.put("ano", ano);
        resultado.put("totalGastos", totalGastos.toString());
        resultado.put("totalRendas", totalRendas.toString());
        resultado.put("saldo", saldo.toString());

        Map<String, String> graficoPorCategoria = new HashMap<>();
        gastosPorCategoria.forEach((categoria, valor) ->
                graficoPorCategoria.put(categoria, valor.toString())
        );
        resultado.put("gastosPorCategoria", graficoPorCategoria);

        return resultado;
    }

    public String obterSaldoSemEconomias(String usuarioId, int ano, int mes) {
        Usuario usuario = getUsuario(usuarioId);
        LocalDate inicio = LocalDate.of(ano, mes, 1);
        LocalDate fim = inicio.withDayOfMonth(inicio.lengthOfMonth());

        BigDecimal totalRendasSemEconomias = rendaRepository.findByUsuarioAndDataRecebimentoBetween(usuario, inicio, fim)
                .stream()
                .filter(r -> r.getTipo() != TipoRenda.ECONOMIA_AUTOMATICA)
                .map(Renda::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalGastos = gastoRepository.findByUsuarioAndDataGastoBetween(usuario, inicio, fim)
                .stream()
                .map(Gasto::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalRendasSemEconomias.subtract(totalGastos).toString();
    }

    private Usuario getUsuario(String id) {
        return usuarioRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}