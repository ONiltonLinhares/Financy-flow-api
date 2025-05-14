package com.simpleapps.FinancyFlow.model.enums;

public enum TipoUsuario {
    ADMIM("admin"),
    GRATUITO("gratuito"),
    PAGANTE("pagante"),;

    private String Tipo;

    TipoUsuario(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getTipo() {
        return Tipo;
    }
}
