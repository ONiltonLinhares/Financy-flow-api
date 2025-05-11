package com.simpleapps.FinancyFlow.dto.categoria;

public class CategoriaRequestDTO {
    private String nome;
    private String tipo; // GASTO ou RENDA
    private String usuarioId;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
}