package com.example.proyectozesari.Helpers;

public class SitioBD {
    public static String TABLE_SITIOS = "sitios";
    public static String SITIO_ID = "id";
    public static String SITIO_NAME = "name";
    public static String SITIO_DESCRIPCION = "descripcion";
    public static String SITIO_MUNICIPIO = "municipio";
    public static String SITIO_DIRECCION = "direccion";
    public static String SITIO_TIPO = "tipo";
    public static String SITIO_IMAGE = "image";

    public static final String CREATE_TABLE_SITIO=
            "CREATE TABLE IF NOT EXISTS "+TABLE_SITIOS+" ("+
                    SITIO_ID+" INTEGER PRIMARY KEY, "+
                    SITIO_NAME+" TEXT, "+
                    SITIO_DESCRIPCION+" TEXT, "+
                    SITIO_MUNICIPIO+" TEXT, "+
                    SITIO_DIRECCION+" TEXT, "+
                    SITIO_TIPO+" TEXT, "+
                    SITIO_IMAGE+" INTEGER)";
}
