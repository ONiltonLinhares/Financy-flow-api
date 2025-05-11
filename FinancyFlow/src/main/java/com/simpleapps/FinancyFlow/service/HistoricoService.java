package com.simpleapps.FinancyFlow.service;

import com.simpleapps.FinancyFlow.model.Gasto;
import com.simpleapps.FinancyFlow.model.Renda;
import com.simpleapps.FinancyFlow.model.Usuario;
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
public class HistoricoService {

    @Autowired
    private RendaRepository rendaRepository;

    @Autowired
    private GastoRepository gastoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Map<String, Object>> listarHistorico(String usuarioId) {
        Usuario usuario = getUsuario(usuarioId);

        Map<String, List<Renda>> rendasAgrupadas = rendaRepository.findByUsuarioAndDataRecebimentoBetween(usuario, LocalDate.of(2000, 1, 1), LocalDate.now())
                .stream().collect(Collectors.groupingBy(r ->
                        r.getDataRecebimento().getYear() + "-" + r.getDataRecebimento().getMonthValue()));

        Map<String, List<Gasto>> gastosAgrupados = gastoRepository.findByUsuarioAndDataGastoBetween(usuario, LocalDate.of(2000, 1, 1), LocalDate.now())
                .stream().collect(Collectors.groupingBy(g ->
                        g.getDataGasto().getYear() + "-" + g.getDataGasto().getMonthValue()));

        Set<String> chaves = new HashSet<>();
        chaves.addAll(rendasAgrupadas.keySet());
        chaves.addAll(gastosAgrupados.keySet());

        List<Map<String, Object>> historico = new ArrayList<>();

        for (String chave : chaves) {
            int ano = Integer.parseInt(chave.split("-")[0]);
            int mes = Integer.parseInt(chave.split("-")[1]);

            Map<String, Object> dados = gerarResumo(usuario, ano, mes);
            historico.add(dados);
        }

        historico.sort((a, b) -> {
            String dataA = (String) a.get("ano") + String.format("%02d", (int) a.get("mes"));
            String dataB = (String) b.get("ano") + String.format("%02d", (int) b.get("mes"));
            return dataB.compareTo(dataA); // ordena do mais recente para o mais antigo
        });

        return historico;
    }

    public Map<String, Object> detalharMes(String usuarioId, int ano, int mes) {
        Usuario usuario = getUsuario(usuarioId);
        return gerarResumo(usuario, ano, mes);
    }

    private Map<String, Object> gerarResumo(Usuario usuario, int ano, int mes) {
        LocalDate inicio = LocalDate.of(ano, mes, 1);
        LocalDate fim = inicio.withDayOfMonth(inicio.lengthOfMonth());

        List<Renda> rendas = rendaRepository.findByUsuarioAndDataRecebimentoBetween(usuario, inicio, fim);
        List<Gasto> gastos = gastoRepository.findByUsuarioAndDataGastoBetween(usuario, inicio, fim);

        BigDecimal totalRendas = rendas.stream().map(Renda::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalGastos = gastos.stream().map(Gasto::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal saldo = totalRendas.subtract(totalGastos);

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("ano", ano);
        resultado.put("mes", mes);
        resultado.put("totalRendas", totalRendas.toString());
        resultado.put("totalGastos", totalGastos.toString());
        resultado.put("saldo", saldo.toString());
        resultado.put("rendas", rendas);
        resultado.put("gastos", gastos);
        return resultado;
    }

    private Usuario getUsuario(String id) {
        return usuarioRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}