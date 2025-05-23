package com.simpleapps.FinancyFlow.dto.gastoFixo;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class GastoFixoRequestDTO {
    @NotBlank(message = "Valor é obrigatório")
    @Pattern(regexp = "^\\d{1,10}(\\.\\d{1,2})?$", message = "Valor deve ser decimal válido com até 2 casas decimais")
    private String valor;
    private String descricao;

    @NotBlank(message = "Categoria é obrigatória")
    private String categoriaId;

    @NotBlank(message = "Dia recorrente é obrigatório")
    @Min(value = 1, message = "Dia recorrente deve ser entre 1 e 28")
    @Max(value = 28, message = "Dia recorrente deve ser entre 1 e 28")
    private String diaRecorrente;

    @NotBlank(message = "Usuário é obrigatório")
    private String usuarioId;

    @NotBlank(message = "Ativo é obrigatório")
    @Pattern(regexp = "true|false", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Ativo deve ser true ou false")
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