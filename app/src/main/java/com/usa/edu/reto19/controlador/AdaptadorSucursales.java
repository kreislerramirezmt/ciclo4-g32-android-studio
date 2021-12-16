package com.usa.edu.reto19.controlador;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usa.edu.reto19.Helpers;
import com.usa.edu.reto19.R;
import com.usa.edu.reto19.modelo.Sucursal;
import com.usa.edu.reto19.vista.MapsActivity;

import java.util.ArrayList;

public class AdaptadorSucursales extends RecyclerView.Adapter<AdaptadorSucursales.ViewHolderSucursales> {
    ArrayList<Sucursal> sucursales;

    public AdaptadorSucursales(ArrayList<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    @NonNull
    @Override
    public ViewHolderSucursales onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sucursales, null, false);
        return new ViewHolderSucursales(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSucursales holder, int position) {
        holder.itemNombre.setText(sucursales.get(position).getNombre());
        holder.itemImagen.setImageResource(sucursales.get(position).getImagen());
        holder.itemBtn.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), MapsActivity.class);
            //Bundle bundle = new Bundle();
            //bundle.putParcelableArrayList("sucursales", (ArrayList)sucursales);
            //intent.putExtras(bundle);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return sucursales.size();
    }

    public class ViewHolderSucursales extends RecyclerView.ViewHolder {

        TextView itemNombre;
        ImageView itemImagen;
        Button itemBtn;

        public ViewHolderSucursales(@NonNull View itemView) {
            super(itemView);

            itemNombre = (TextView) itemView.findViewById(R.id.itemNombreSucursal);
            itemImagen = (ImageView) itemView.findViewById(R.id.itemImagenSucursal);
            itemBtn = (Button) itemView.findViewById(R.id.itemBtnSucursal);
        }
    }
}
