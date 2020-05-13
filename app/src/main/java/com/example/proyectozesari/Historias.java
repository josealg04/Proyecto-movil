package com.example.proyectozesari;

import java.io.Serializable;

public class Historias implements Serializable {

    private String historiaName;
    private String historiaMunicipio;
    private String historiaDescripcion;
    private int historiaImageId;

    public Historias(String historiaName, String historiaMunicipio, String historiaDescripcion, int historiaImageId) {
        this.historiaName = historiaName;
        this.historiaMunicipio = historiaMunicipio;
        this.historiaDescripcion = historiaDescripcion;
        this.historiaImageId = historiaImageId;
    }

    public String getHistoriaName() {
        return historiaName;
    }

    public void setHistoriaName(String historiaName) {
        this.historiaName = historiaName;
    }

    public String getHistoriaMunicipio() {
        return historiaMunicipio;
    }

    public void setHistoriaMunicipio(String historiaMunicipio) {
        this.historiaMunicipio = historiaMunicipio;
    }

    public String getHistoriaDescripcion() {
        return historiaDescripcion;
    }

    public void setHistoriaDescripcion(String historiaDescripcion) {
        this.historiaDescripcion = historiaDescripcion;
    }

    public int getHistoriaImageId() {
        return historiaImageId;
    }

    public void setHistoriaImageId(int historiaImageId) {
        this.historiaImageId = historiaImageId;
    }

}
