package com.simpleapps.FinancyFlow.dto.gasto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class GastoRequestDTO {
    @NotBlank(message = "Valor é obrigatório")
    @Pattern(regexp = "^\\d{1,10}(\\.\\d{1,2})?$", message = "Valor deve ser decimal válido com até 2 casas decimais")
    private String valor;
    private String descricao;

    @NotBlank(message = "Categoria é obrigatória")
    private String categoriaId;

    @NotBlank(message = "Data do gasto é obrigatória")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Data deve estar no formato yyyy-MM-dd")
    private String dataGasto;

    @NotBlank(message = "Tipo é obrigatório")
    @Pattern(regexp = "COMUM|FIXO|PLANEJADO", message = "Tipo inválido")
    private String tipo;

    @NotBlank(message = "Usuário é obrigatório")
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