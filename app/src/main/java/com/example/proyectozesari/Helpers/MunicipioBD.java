package com.example.proyectozesari.Helpers;

public class MunicipioBD {
    public static String TABLE_MUNICIPIOS = "municipios";
    public static String MUNICIPIOS_ID = "id";
    public static String MUNICIPIOS_NAME = "name";

    public static final String CREATE_TABLE_MUNICIPIOS=
            "CREATE TABLE IF NOT EXISTS "+TABLE_MUNICIPIOS+" ("+
                    MUNICIPIOS_ID+" INTEGER PRIMARY KEY, "+
                    MUNICIPIOS_NAME+" TEXT)";
}
