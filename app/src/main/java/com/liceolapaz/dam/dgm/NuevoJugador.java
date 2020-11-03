package com.liceolapaz.dam.dgm;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;


public class NuevoJugador extends FrameLayout {

    private EditText txtCodigo;
    private EditText txtNombre;
    private EditText txtPrecio;
    private Spinner txtPosicion;
    private EditText txtPuntos;
    public Button btnAceptar;
    public Button btnCancelar;
    public Button btnEliminar;


    public NuevoJugador(Context context) {
        super(context);
        inicializar();
    }

    private void inicializar()
    {
        //Utilizamos el layout 'nuevo_jugador_activity' como interfaz del control
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li = (LayoutInflater)getContext().getSystemService(infService);
        li.inflate(R.layout.nuevo_jugador_activity, this, true);

        //Obtenemos las referencias a los distintos control

        txtCodigo = (EditText)findViewById(R.id.codigoJugador);
        txtNombre = (EditText)findViewById(R.id.nombreJugador);
        txtPrecio = (EditText)findViewById(R.id.precioJugador);
        txtPuntos = (EditText)findViewById(R.id.puntosJugador);
        txtPosicion = (Spinner)findViewById(R.id.posicionJugador);
        btnAceptar = (Button)findViewById(R.id.aceptar);
        btnCancelar = (Button)findViewById(R.id.cancelar);
        btnEliminar = (Button)findViewById(R.id.eliminar);


    }




}
