package mx.betopartida.puppyplanet;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by beto on 30/06/2016.
 */
public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {

    private ArrayList<Mascota> mascotas;
    private Activity activity;

    public MascotaAdapter(ArrayList mascotas,Activity activity){
        this.mascotas=mascotas;
        this.activity=activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota,parent,false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder holder, final int position) {
        final Mascota mascota=mascotas.get(position);
        holder.tvNombre.setText(mascota.getNombre());
        holder.imgFoto.setImageResource(mascota.getFoto());
        holder.tvLikes.setText(String.valueOf(mascota.getLikes()));

        holder.imgHuesoBco.setOnClickListener(new View.OnClickListener() {
            @Override
            //Al hacer click en el hueso blanco se incrementa el contador de likes
            public void onClick(View v) {
                int cuenta=1;
                cuenta+=Integer.valueOf(holder.tvLikes.getText().toString());
                holder.tvLikes.setText(String.valueOf(cuenta));
                mascotas.get(position).setLikes(cuenta);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNombre;
        private ImageView imgFoto;
        private TextView tvLikes;
        private ImageView imgHuesoBco;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            tvNombre=(TextView) itemView.findViewById(R.id.tvNombre);
            imgFoto=(ImageView) itemView.findViewById(R.id.imgFoto);
            tvLikes=(TextView) itemView.findViewById(R.id.tvLikes);
            imgHuesoBco=(ImageView) itemView.findViewById(R.id.imgHuesoBco);
        }
    }
}
