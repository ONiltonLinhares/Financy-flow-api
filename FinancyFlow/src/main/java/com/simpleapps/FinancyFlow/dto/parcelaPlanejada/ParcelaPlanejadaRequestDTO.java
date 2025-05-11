package com.simpleapps.FinancyFlow.dto.parcelaPlanejada;

public class ParcelaPlanejadaRequestDTO {
    private String gastoPlanejadoId;
    private String mesAno;
    private String valor;
    private String foiConvertidoEmGasto;

    public String getGastoPlanejadoId() { return gastoPlanejadoId; }
    public void setGastoPlanejadoId(String gastoPlanejadoId) { this.gastoPlanejadoId = gastoPlanejadoId; }

    public String getMesAno() { return mesAno; }
    public void setMesAno(String mesAno) { this.mesAno = mesAno; }

    public String getValor() { return valor; }
    public void setValor(String valor) { this.valor = valor; }

    public String getFoiConvertidoEmGasto() { return foiConvertidoEmGasto; }
    public void setFoiConvertidoEmGasto(String foiConvertidoEmGasto) { this.foiConvertidoEmGasto = foiConvertidoEmGasto; }
}