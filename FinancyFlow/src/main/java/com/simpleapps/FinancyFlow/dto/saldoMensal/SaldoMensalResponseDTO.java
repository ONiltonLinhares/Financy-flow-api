package com.simpleapps.FinancyFlow.dto.saldoMensal;

public class SaldoMensalResponseDTO {
    private String id;
    private String usuarioId;
    private String mesAno;
    private String totalRendas;
    private String totalGastos;
    private String saldoFinal;
    private String economiaGerada;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }

    public String getMesAno() { return mesAno; }
    public void setMesAno(String mesAno) { this.mesAno = mesAno; }

    public String getTotalRendas() { return totalRendas; }
    public void setTotalRendas(String totalRendas) { this.totalRendas = totalRendas; }

    public String getTotalGastos() { return totalGastos; }
    public void setTotalGastos(String totalGastos) { this.totalGastos = totalGastos; }

    public String getSaldoFinal() { return saldoFinal; }
    public void setSaldoFinal(String saldoFinal) { this.saldoFinal = saldoFinal; }

    public String getEconomiaGerada() { return economiaGerada; }
    public void setEconomiaGerada(String economiaGerada) { this.economiaGerada = economiaGerada; }
}