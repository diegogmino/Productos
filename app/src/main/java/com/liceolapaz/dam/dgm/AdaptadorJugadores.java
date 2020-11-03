package com.liceolapaz.dam.dgm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorJugadores
        extends RecyclerView.Adapter<AdaptadorJugadores.JugadoresViewHolder>
        implements View.OnClickListener {

    private View.OnClickListener listener;
    private ArrayList<Jugadores> datos;

    public static class JugadoresViewHolder
            extends RecyclerView.ViewHolder {

        private TextView txtNombre;
        private TextView txtPrecio;
        private TextView txtPosicion;
        private TextView txtPuntos;

        public JugadoresViewHolder(View itemView) {
            super(itemView);

            txtNombre = (TextView)itemView.findViewById(R.id.LblNombre);
            txtPrecio = (TextView)itemView.findViewById(R.id.LblPrecio);
            txtPosicion = (TextView)itemView.findViewById(R.id.LblPosicion);
            txtPuntos = (TextView)itemView.findViewById(R.id.LblPuntos);
        }

        public void bindJugador(Jugadores t) {
            txtNombre.setText(t.getNombre());
            txtPrecio.setText(""+t.getPrecio()+ " €");
            txtPosicion.setText(t.getPosicion());
            txtPuntos.setText(""+t.getPuntos()+" puntos");
        }
    }

    public AdaptadorJugadores(ArrayList<Jugadores> datos) {
        this.datos = datos;
    }

    @Override
    public JugadoresViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.lista_jugadores, viewGroup, false);

        itemView.setOnClickListener(this);
        //android:background="?android:attr/selectableItemBackground"

        JugadoresViewHolder jvh = new JugadoresViewHolder(itemView);

        return jvh;
    }

    @Override
    public void onBindViewHolder(JugadoresViewHolder viewHolder, int pos) {
        Jugadores item = datos.get(pos);

        viewHolder.bindJugador(item);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }
}
