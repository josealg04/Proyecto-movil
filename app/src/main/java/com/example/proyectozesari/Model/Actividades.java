package com.example.proyectozesari.Model;

import java.io.Serializable;

public class Actividades implements Serializable {

    private int actividadId;
    private String actividadName;
    private String actividadDescripcion;
    private String actividadContacto;
    private String actividadTipo;
    private int actividadImageId;

    public Actividades(){}

    public Actividades(int actividadId, String actividadName, String actividadDescripcion, String actividadContacto, String actividadTipo, int actividadImageId) {
        this.actividadId = actividadId;
        this.actividadName = actividadName;
        this.actividadDescripcion = actividadDescripcion;
        this.actividadContacto = actividadContacto;
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

    public String getActividadDescripcion() {
        return actividadDescripcion;
    }

    public void setActividadDescripcion(String actividadDescripcion) {
        this.actividadDescripcion = actividadDescripcion;
    }

    public String getActividadContacto() {
        return actividadContacto;
    }

    public void setActividadContacto(String actividadContacto) {
        this.actividadContacto = actividadContacto;
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
