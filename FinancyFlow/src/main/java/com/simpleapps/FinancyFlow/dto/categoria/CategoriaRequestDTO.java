package com.simpleapps.FinancyFlow.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CategoriaRequestDTO {
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 1, max = 100, message = "Nome deve ter até 100 caracteres")
    @Pattern(regexp = "^[A-Za-zÀ-ú0-9 &\\-]+$", message = "Nome contém caracteres inválidos")
    private String nome;

    @NotBlank(message = "Tipo é obrigatório")
    @Pattern(regexp = "GASTO|RENDA", message = "Tipo deve ser GASTO ou RENDA")
    private String tipo; // GASTO ou RENDA

    @NotBlank(message = "Usuário é obrigatório")
    private String usuarioId;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getUsuarioId() { return usuarioId; }
    public void setUsuarioId(String usuarioId) { this.usuarioId = usuarioId; }
}