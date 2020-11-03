package com.liceolapaz.dam.dgm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        Conexion conn = new Conexion(this, "jugadores", null, 1);

        jugadores = new ArrayList<Jugadores>();

        recView =  findViewById(R.id.RecView);
        recView.setHasFixedSize(true);

        final AdaptadorJugadores adaptador = new AdaptadorJugadores(jugadores);
        for(int i=0; i<10; i++)
            jugadores.add(new Jugadores(10, "Messi", 15000, "DL", 150));

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


        recView.setAdapter(adaptador);

        recView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        recView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));

    }
}
