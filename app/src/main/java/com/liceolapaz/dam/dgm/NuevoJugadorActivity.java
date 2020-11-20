package com.liceolapaz.dam.dgm;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class NuevoJugadorActivity extends AppCompatActivity {

    boolean actualizar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_jugador_activity);
        TextView titulo = (TextView)findViewById(R.id.jugadorEditar);
        titulo.setText("Nuevo Jugador");

        Button aceptar = (Button)findViewById(R.id.aceptar);
        Button cancelar = (Button)findViewById(R.id.cancelar);
        Button eliminar = (Button)findViewById(R.id.eliminar);

        eliminar.setEnabled(false);
        eliminar.setBackground(getResources().getDrawable(R.drawable.disable_border));

        final Bundle b = this.getIntent().getExtras();
        if (b != null) {

            actualizar = true;
            eliminar.setEnabled(true);
            eliminar.setBackground(getResources().getDrawable(R.drawable.border));
            EditText codigo = (EditText)findViewById(R.id.codigoJugador);
            codigo.setEnabled(false);

            EditText nombre = (EditText)findViewById(R.id.nombreJugador);
            EditText precio = (EditText)findViewById(R.id.precioJugador);
            Spinner posicion = (Spinner) findViewById(R.id.posicionJugador);
            EditText puntos = (EditText)findViewById(R.id.puntosJugador);

            codigo.setText((b.getInt("codigo"))+"");
            nombre.setText(b.getString("nombre"));
            precio.setText((b.getFloat("precio"))+"");
            for (int i= 0; i < 4; i++) {
                String pos = (String) posicion.getItemAtPosition(i);
                if (pos.equals(b.getString("posicion"))) {
                    posicion.setSelection(i);
                    break;
                }
            }
            puntos.setText((b.getInt("puntos"))+"");

            titulo.setText(b.getString("nombre"));

        }


        final Context context = this;
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText txtCodigo = (EditText)findViewById(R.id.codigoJugador);
                EditText txtNombre = (EditText)findViewById(R.id.nombreJugador);
                EditText txtPrecio = (EditText)findViewById(R.id.precioJugador);
                Spinner txtPosicion = (Spinner)findViewById(R.id.posicionJugador);
                EditText txtPuntos = (EditText)findViewById(R.id.puntosJugador);

                String jugador = txtNombre.getText().toString();

                if (jugador.equals("")) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    Popup popup = new Popup(context, "No se han rellenado los campos del jugador", "Error");
                    popup.show(fragmentManager, "tagAlerta");
                } else {

                    int codigoJugador = Integer.parseInt(txtCodigo.getText().toString());

                    float precio = Float.parseFloat(txtPrecio.getText().toString());
                    String posicion = txtPosicion.getSelectedItem().toString();

                    int puntos = Integer.parseInt(txtPuntos.getText().toString());

                    String sql ="";
                    Conexion conexion = new Conexion(context, "jugadores", null, 1);

                    if (actualizar) {

                        sql = "UPDATE jugadores SET nombre='" + jugador + "', precio=" + precio +", posicion='" + posicion + "', puntos=" + puntos + " WHERE codigo=" + codigoJugador + ";";

                    } else {
                        sql = "INSERT into jugadores (codigo, nombre, precio, posicion, puntos) VALUES ("+codigoJugador + ",'" + jugador + "', " +
                                "" + precio + ", '" + posicion + "', " + puntos + ")";
                    }

                    FragmentManager fragmentManager = getSupportFragmentManager();
                    Popup popup = new Popup(context, "Los datos se guardarán en la base de datos ¿Está seguro?", "Aceptar", conexion, sql);
                    popup.show(fragmentManager, "tagAlerta");
                }




            }
        });


        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                Popup popup = new Popup(context, "Los datos no se guardarán ¿Está seguro de cancelar?", "Cancelar");
                popup.show(fragmentManager, "tagAlerta");

            }
        });




        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Conexion conexion = new Conexion(context, "jugadores", null, 1);
                int id = b.getInt("codigo");
                String sql = "DELETE FROM jugadores WHERE codigo ="+id+"";

                FragmentManager fragmentManager = getSupportFragmentManager();
                Popup popup = new Popup(context, "Los datos se borrarán de la base de datos ¿Está seguro?", "Eliminar", conexion, sql);
                popup.show(fragmentManager, "tagAlerta");






            }
        });

    }
}
