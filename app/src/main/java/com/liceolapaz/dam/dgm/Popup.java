package com.liceolapaz.dam.dgm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.DialogFragment;

public class Popup extends DialogFragment {

    Context context;
    String mensaje;
    String titulo;
    Conexion conexion;
    String sql;

    public Popup (Context context, String mensaje, String titulo, Conexion conexion, String sql) {
        this.context = context;
        this.mensaje = mensaje;
        this.titulo = titulo;
        this.conexion = conexion;
        this.sql = sql;

    }

    public Popup (Context context, String mensaje, String titulo) {
        this.context = context;
        this.mensaje = mensaje;
        this.titulo = titulo;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        if(titulo.equals("Error")) {
            builder.setMessage(mensaje)
                    .setTitle(titulo);
        } else {

            builder.setMessage(mensaje)
                    .setTitle(titulo)
                    .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            if(titulo.equals("Aceptar") && sql != null) {
                                SQLiteDatabase db = conexion.getWritableDatabase();
                                if(db != null) {
                                    db.execSQL(sql);
                                    db.close();
                                }
                                Log.i("Dialogos", "Confirmacion Aceptada.");
                                Intent intent = new Intent(context, JugadoresActivity.class);
                                startActivity(intent);
                            }

                            if(titulo.equals("Cancelar")){
                                Intent intent = new Intent(context, JugadoresActivity.class);
                                startActivity(intent);
                            }

                            if(titulo.equals("Eliminar")){
                                SQLiteDatabase db = conexion.getWritableDatabase();
                                if (db != null) {
                                    db.execSQL(sql);
                                    db.close();
                                }
                                Intent intent = new Intent(context, JugadoresActivity.class);
                                startActivity(intent);
                            }

                            if(titulo.equals("Error")){
                                Intent intent = new Intent(context, JugadoresActivity.class);
                                startActivity(intent);
                            }

                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            if(titulo.equals("Cancelar")){
                                Log.i("Dialogos", "Confirmacion Cancelada.");
                                dialog.cancel();
                            }

                            if(titulo.equals("Aceptar") || titulo.equals("Eliminar")) {
                                Intent intent = new Intent(context, JugadoresActivity.class);
                                startActivity(intent);
                            }


                        }
                    });




            if(titulo.equals("Aceptar") || titulo.equals("Eliminar")) {
                builder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i("Dialogos", "Confirmacion Cancelada.");
                        dialog.cancel();
                    }
                });
            }
        }

        return builder.create();
    }
    }

