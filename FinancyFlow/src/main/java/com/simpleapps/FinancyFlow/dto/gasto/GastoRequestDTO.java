package com.simpleapps.FinancyFlow.dto.gasto;

public class GastoRequestDTO {
    private String valor;
    private String descricao;
    private String categoriaId;
    private String dataGasto;
    private String tipo;
    private String usuarioId;
    private String origemId;

    public String getValor() { return valor; }
    public void setValor(String valor) { this.valor = valor; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getCategoriaId() { return categoriaId; }
    public void setCategoriaId(String categoriaId) { this.categoriaId = categoriaId; }

    public String getDataGasto() { return dataGasto; }
    public void setDataGasto(String dataGasto) { this.dataGasto = dataGasto; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }

    public String getOrigemId() { return origemId; }
    public void setOrigemId(String origemId) { this.origemId = origemId; }
}