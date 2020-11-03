package com.liceolapaz.dam.dgm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Connection;

public class Conexion extends SQLiteOpenHelper {

    final String CREAR_TABLA_JUGADORES = "CREATE TABLE jugadores (codigo INTEGER, nombre TEXT, precio REAL," +
            "posicion TEXT, puntos INTEGER)";

    public Conexion(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_JUGADORES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS jugadores");
        onCreate(db);
    }
}
