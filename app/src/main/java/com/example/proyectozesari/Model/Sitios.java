package com.example.proyectozesari.Model;

import java.io.Serializable;

public class Sitios implements Serializable {

    private int sitioId;
    private String sitioName;
    private String sitioMunicipio;
    private String sitioDescripcion;
    private String sitioDireccion;
    private String sitioTipo;
    private int sitioImageId;

    public Sitios(){}

    public Sitios(int sitioId, String sitioName, String sitioMunicipio, String sitioDescripcion, String sitioDireccion, String sitioTipo, int sitioImageId) {
        this.sitioId = sitioId;
        this.sitioName = sitioName;
        this.sitioMunicipio = sitioMunicipio;
        this.sitioDescripcion = sitioDescripcion;
        this.sitioDireccion = sitioDireccion;
        this.sitioTipo = sitioTipo;
        this.sitioImageId = sitioImageId;
    }

    /*public Sitios(String sitioName, String sitioMunicipio, int sitioImageId) {
        this.sitioName = sitioName;
        this.sitioMunicipio = sitioMunicipio;
        this.sitioImageId = sitioImageId;
    }*/

    public int getSitioId() {
        return sitioId;
    }

    public void setSitioId(int sitioId) {
        this.sitioId = sitioId;
    }

    public String getSitioName() {
        return sitioName;
    }

    public void setSitioName(String sitioName) {
        this.sitioName = sitioName;
    }

    public String getSitioMunicipio() {
        return sitioMunicipio;
    }

    public void setSitioMunicipio(String sitioMunicipio) {
        this.sitioMunicipio = sitioMunicipio;
    }

    public String getSitioDescripcion() {
        return sitioDescripcion;
    }

    public void setSitioDescripcion(String sitioDescripcion) {
        this.sitioDescripcion = sitioDescripcion;
    }

    public String getSitioDireccion() {
        return sitioDireccion;
    }

    public void setSitioDireccion(String sitioDireccion) {
        this.sitioDireccion = sitioDireccion;
    }

    public String getSitioTipo() {
        return sitioTipo;
    }

    public void setSitioTipo(String sitioTipo) {
        this.sitioTipo = sitioTipo;
    }

    public int getSitioImageId() {
        return sitioImageId;
    }

    public void setSitioImageId(int sitioImageId) {
        this.sitioImageId = sitioImageId;
    }
}
