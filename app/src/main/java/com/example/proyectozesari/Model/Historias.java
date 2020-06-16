package com.example.proyectozesari.Model;

import java.io.Serializable;

public class Historias implements Serializable {

    private int historiaId;
    private String historiaName;
    private String historiaMunicipio;
    private String historiaDescripcion;
    private String historiaTipo;
    private int historiaImageId;

    public Historias(){}

    public Historias(int historiaId, String historiaName, String historiaMunicipio, String historiaDescripcion, String historiaTipo, int historiaImageId) {
        this.historiaId = historiaId;
        this.historiaName = historiaName;
        this.historiaMunicipio = historiaMunicipio;
        this.historiaDescripcion = historiaDescripcion;
        this.historiaTipo = historiaTipo;
        this.historiaImageId = historiaImageId;
    }

    public int getHistoriaId() {
        return historiaId;
    }

    public void setHistoriaId(int historiaId) {
        this.historiaId = historiaId;
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

    public String getHistoriaTipo() {
        return historiaTipo;
    }

    public void setHistoriaTipo(String historiaTipo) {
        this.historiaTipo = historiaTipo;
    }

    public int getHistoriaImageId() {
        return historiaImageId;
    }

    public void setHistoriaImageId(int historiaImageId) {
        this.historiaImageId = historiaImageId;
    }

}
