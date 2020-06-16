package com.example.proyectozesari.Helpers;

public class HistoriaBD {
    public static String TABLE_HISTORIAS = "historias";
    public static String HISTORIA_ID = "id";
    public static String HISTORIA_NAME = "name";
    public static String HISTORIA_DESCRIPCION = "descripcion";
    public static String HISTORIA_MUNICIPIO = "municipio";
    public static String HISTORIA_TIPO = "tipo";
    public static String HISTORIA_IMAGE = "image";

    public static final String CREATE_TABLE_HISTORIA=
            "CREATE TABLE IF NOT EXISTS "+TABLE_HISTORIAS+" ("+
                    HISTORIA_ID+" INTEGER PRIMARY KEY, "+
                    HISTORIA_NAME+" TEXT, "+
                    HISTORIA_DESCRIPCION+" TEXT, "+
                    HISTORIA_MUNICIPIO+" TEXT, "+
                    HISTORIA_TIPO+" TEXT, "+
                    HISTORIA_IMAGE+" INTEGER)";
}
