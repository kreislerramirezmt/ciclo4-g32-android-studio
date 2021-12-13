package com.usa.edu.reto19.controlador;

import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usa.edu.reto19.Helpers;
import com.usa.edu.reto19.modelo.ProductoModels;

import java.util.ArrayList;

import com.usa.edu.reto19.R;
import com.usa.edu.reto19.modelo.ProductoModels;

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ViewHolderProductos> {

    ArrayList<ProductoModels> productos;

    public AdaptadorProductos(ArrayList<ProductoModels> productos) {
        this.productos = productos;
    }


    @Override
    public ViewHolderProductos onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, null, false);
        return new ViewHolderProductos(vista);
    }

    @Override
    public void onBindViewHolder(ViewHolderProductos holder, int position) {
        holder.itemNombre.setText(productos.get(position).getNombre());
        holder.itemImagen.setImageResource(productos.get(position).getImagen());
        holder.itemPrecio.setText("" + productos.get(position).getPrecio());
        holder.itemFavorito.setText("Añadir <3");

        holder.itemFavorito.setOnClickListener(v -> {
            MyOpenHelper moh = new MyOpenHelper(v.getContext());
            SQLiteDatabase db = moh.getWritableDatabase();
            if (productos.get(position).getFavorito() == 0) {
                ProductoController.anadirFavorito(productos.get(position).getId(), moh, db, v.getContext());
            }
        });


    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class ViewHolderProductos extends RecyclerView.ViewHolder {

        TextView itemNombre;
        ImageView itemImagen;
        TextView itemPrecio;
        Button itemFavorito;

        public ViewHolderProductos(@NonNull View itemView) {
            super(itemView);

            itemNombre = (TextView) itemView.findViewById(R.id.itemNombre);
            itemImagen = (ImageView) itemView.findViewById(R.id.itemImagen);
            itemPrecio = (TextView) itemView.findViewById(R.id.itemPrecio);
            itemFavorito = (Button) itemView.findViewById(R.id.itemFavorito);
        }
    }
}
