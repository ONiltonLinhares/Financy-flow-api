package com.simpleapps.FinancyFlow.dto.gastoFixo;

public class GastoFixoRequestDTO {
    private String valor;
    private String descricao;
    private String categoriaId;
    private String diaRecorrente;
    private String usuarioId;
    private String ativo;

    public String getValor() { return valor; }
    public void setValor(String valor) { this.valor = valor; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getCategoriaId() { return categoriaId; }
    public void setCategoriaId(String categoriaId) { this.categoriaId = categoriaId; }

    public String getDiaRecorrente() { return diaRecorrente; }
    public void setDiaRecorrente(String diaRecorrente) { this.diaRecorrente = diaRecorrente; }

    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }

    public String getAtivo() { return ativo; }
    public void setAtivo(String ativo) { this.ativo = ativo; }
}