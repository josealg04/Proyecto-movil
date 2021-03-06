package com.example.proyectozesari.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.proyectozesari.Model.Historias;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "zesari";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SitioBD.CREATE_TABLE_SITIO);
        db.execSQL(MunicipioBD.CREATE_TABLE_MUNICIPIOS);
        db.execSQL(HistoriaBD.CREATE_TABLE_HISTORIA);
        db.execSQL(ActividadBD.CREATE_TABLE_ACTIVIDAD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
