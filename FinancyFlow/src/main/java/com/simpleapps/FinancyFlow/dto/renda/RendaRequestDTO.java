package com.simpleapps.FinancyFlow.dto.renda;

public class RendaRequestDTO {
    private String valor;
    private String descricao;
    private String categoriaId;
    private String dataRecebimento;
    private String tipo;
    private String usuarioId;

    public String getValor() { return valor; }
    public void setValor(String valor) { this.valor = valor; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getCategoriaId() { return categoriaId; }
    public void setCategoriaId(String categoriaId) { this.categoriaId = categoriaId; }

    public String getDataRecebimento() { return dataRecebimento; }
    public void setDataRecebimento(String dataRecebimento) { this.dataRecebimento = dataRecebimento; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
}