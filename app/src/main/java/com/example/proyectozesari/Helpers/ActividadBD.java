package com.example.proyectozesari.Helpers;

public class ActividadBD {
    public static String TABLE_ACTIVIDADES = "actividades";
    public static String ACTIVIDAD_ID = "id";
    public static String ACTIVIDAD_NAME = "name";
    public static String ACTIVIDAD_DESCRIPCION = "descripcion";
    public static String ACTIVIDAD_CONTACTO = "contacto";
    public static String ACTIVIDAD_TIPO = "tipo";
    public static String ACTIVIDAD_IMAGE = "image";

    public static final String CREATE_TABLE_ACTIVIDAD=
            "CREATE TABLE IF NOT EXISTS "+TABLE_ACTIVIDADES+" ("+
                    ACTIVIDAD_ID+" INTEGER PRIMARY KEY, "+
                    ACTIVIDAD_NAME+" TEXT, "+
                    ACTIVIDAD_DESCRIPCION+" TEXT, "+
                    ACTIVIDAD_CONTACTO+" TEXT, "+
                    ACTIVIDAD_TIPO+" TEXT, "+
                    ACTIVIDAD_IMAGE+" INTEGER)";
}
