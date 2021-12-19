package com.usa.edu.reto19.controlador;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.usa.edu.reto19.R;
import com.usa.edu.reto19.modelo.Servicio;
import com.usa.edu.reto19.modelo.Sucursal;
import com.usa.edu.reto19.vista.MapActivity;

import java.util.ArrayList;

public class AdaptadorServicios extends RecyclerView.Adapter<AdaptadorServicios.ViewHolderServicios> {
    ArrayList<Servicio> servicios;

    public AdaptadorServicios(ArrayList<Servicio> servicios) {
        this.servicios = servicios;
    }

    @NonNull
    @Override
    public ViewHolderServicios onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_servicios, null, false);
        return new ViewHolderServicios(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderServicios holder, int position) {
        holder.itemNombre.setText(servicios.get(position).getNombre());
        Picasso.get().load(servicios.get(position).getImagen()).into(holder.itemImagen);
    }

    @Override
    public int getItemCount() {
        return servicios.size();
    }

    public class ViewHolderServicios extends RecyclerView.ViewHolder {

        TextView itemNombre;
        ImageView itemImagen;

        public ViewHolderServicios(@NonNull View itemView) {
            super(itemView);

            itemNombre = itemView.findViewById(R.id.itemNombreServicio);
            itemImagen = itemView.findViewById(R.id.itemImagenServicio);
        }
    }
}
