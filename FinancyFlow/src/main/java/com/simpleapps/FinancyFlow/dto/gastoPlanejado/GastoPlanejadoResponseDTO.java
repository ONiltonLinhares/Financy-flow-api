package com.simpleapps.FinancyFlow.dto.gastoPlanejado;

public class GastoPlanejadoResponseDTO {
    private String id;
    private String descricao;
    private String categoriaId;
    private String usuarioId;
    private String dataInicio;
    private String duracaoMeses;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getCategoriaId() { return categoriaId; }
    public void setCategoriaId(String categoriaId) { this.categoriaId = categoriaId; }

    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }

    public String getDataInicio() { return dataInicio; }
    public void setDataInicio(String dataInicio) { this.dataInicio = dataInicio; }

    public String getDuracaoMeses() { return duracaoMeses; }
    public void setDuracaoMeses(String duracaoMeses) { this.duracaoMeses = duracaoMeses; }
}