package com.liceolapaz.dam.dgm;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.ResultSet;
import java.util.ArrayList;

public class JugadoresActivity extends AppCompatActivity {

    private RecyclerView recView;
    public ArrayList<Jugadores> jugadores;
    private TextView txtNumeroJugadores;
    private ImageButton nuevoJugador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jugadores_activity);

        jugadores = new ArrayList<Jugadores>();
        recView =  findViewById(R.id.RecView);
        recView.setHasFixedSize(true);

        final AdaptadorJugadores adaptador = new AdaptadorJugadores(jugadores);

        Conexion conexion = new Conexion(this, "jugadores", null, 1);

        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM jugadores", null);

        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                int codigo = c.getInt(0);
                String nombre= c.getString(1);
                float precio = c.getFloat(2);
                String posicion = c.getString(3);
                int puntos = c.getInt(4);

                jugadores.add(new Jugadores(codigo, nombre, precio, posicion, puntos));
            } while(c.moveToNext());
        }

        txtNumeroJugadores = (TextView)findViewById(R.id.numeroJugadores);
        txtNumeroJugadores.setText(adaptador.getItemCount()+"");
        nuevoJugador = (ImageButton) findViewById(R.id.botonMas);
        nuevoJugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NuevoJugadorActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                b.putInt("codigo",  jugadores.get(recView.getChildAdapterPosition(v)).getCodigo());
                b.putString("nombre", jugadores.get(recView.getChildAdapterPosition(v)).getNombre());
                b.putFloat("precio", jugadores.get(recView.getChildAdapterPosition(v)).getPrecio());
                b.putString("posicion", jugadores.get(recView.getChildAdapterPosition(v)).getPosicion());
                b.putInt("puntos",  jugadores.get(recView.getChildAdapterPosition(v)).getPuntos());

                Intent intent = new Intent(v.getContext(), NuevoJugadorActivity.class);

                intent.putExtras(b);

                startActivityForResult(intent, 0);
            }
        });
        recView.setAdapter(adaptador);
        recView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));

    }
}
