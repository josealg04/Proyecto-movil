package com.example.proyectozesari.Model;

import java.io.Serializable;

public class Actividades implements Serializable {

    private int actividadId;
    private String actividadName;
    private String actividadMunicipio;
    private String actividadDescripcion;
    private String actividadDireccion;
    private String actividadTipo;
    private int actividadImageId;

    public Actividades(){}

    public Actividades(int actividadId, String actividadName, String actividadMunicipio, String actividadDescripcion, String actividadDireccion, String actividadTipo, int actividadImageId) {
        this.actividadId = actividadId;
        this.actividadName = actividadName;
        this.actividadMunicipio = actividadMunicipio;
        this.actividadDescripcion = actividadDescripcion;
        this.actividadDireccion = actividadDireccion;
        this.actividadTipo = actividadTipo;
        this.actividadImageId = actividadImageId;
    }

    public int getActividadId() {
        return actividadId;
    }

    public void setActividadId(int actividadId) {
        this.actividadId = actividadId;
    }

    public String getActividadName() {
        return actividadName;
    }

    public void setActividadName(String actividadName) {
        this.actividadName = actividadName;
    }

    public String getActividadMunicipio() {
        return actividadMunicipio;
    }

    public void setActividadMunicipio(String actividadMunicipio) {
        this.actividadMunicipio = actividadMunicipio;
    }

    public String getActividadDescripcion() {
        return actividadDescripcion;
    }

    public void setActividadDescripcion(String actividadDescripcion) {
        this.actividadDescripcion = actividadDescripcion;
    }

    public String getActividadDireccion() {
        return actividadDireccion;
    }

    public void setActividadDireccion(String actividadDireccion) {
        this.actividadDireccion = actividadDireccion;
    }

    public String getActividadTipo() {
        return actividadTipo;
    }

    public void setActividadTipo(String actividadTipo) {
        this.actividadTipo = actividadTipo;
    }

    public int getActividadImageId() {
        return actividadImageId;
    }

    public void setActividadImageId(int actividadImageId) {
        this.actividadImageId = actividadImageId;
    }
}
