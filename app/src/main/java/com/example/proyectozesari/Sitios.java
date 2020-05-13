package com.example.proyectozesari;

public class Sitios {

    private String sitioName;
    private String sitioMunicipio;
    private String sitioDescripcion;
    private String sitioDireccion;
    private int sitioImageId;

    public Sitios(){}

    public Sitios(String sitioName, String sitioMunicipio, String sitioDescripcion, String sitioDireccion, int sitioImageId) {
        this.sitioName = sitioName;
        this.sitioMunicipio = sitioMunicipio;
        this.sitioDescripcion = sitioDescripcion;
        this.sitioDireccion = sitioDireccion;
        this.sitioImageId = sitioImageId;
    }

    /*public Sitios(String sitioName, String sitioMunicipio, int sitioImageId) {
        this.sitioName = sitioName;
        this.sitioMunicipio = sitioMunicipio;
        this.sitioImageId = sitioImageId;
    }*/

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

    public int getSitioImageId() {
        return sitioImageId;
    }

    public void setSitioImageId(int sitioImageId) {
        this.sitioImageId = sitioImageId;
    }
}
