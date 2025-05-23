package com.simpleapps.FinancyFlow.dto.renda;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RendaRequestDTO {

    @NotBlank(message = "Valor é obrigatório")
    @Pattern(regexp = "^\\d{1,10}(\\.\\d{1,2})?$", message = "Valor deve ser decimal válido com até 2 casas decimais")
    private String valor;
    private String descricao;

    @NotBlank(message = "Categoria é obrigatória")
    private String categoriaId;

    @NotBlank(message = "Data de recebimento é obrigatória")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Data deve estar no formato yyyy-MM-dd")
    private String dataRecebimento;

    @NotBlank(message = "Tipo é obrigatório")
    @Pattern(regexp = "SALARIO|EXTRA|ECONOMIA_AUTOMATICA", message = "Tipo inválido")
    private String tipo;

    @NotBlank(message = "Usuário é obrigatório")
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