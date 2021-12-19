package com.usa.edu.reto19.controlador;

import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usa.edu.reto19.Helpers;
import com.usa.edu.reto19.R;
import com.usa.edu.reto19.modelo.ProductoModels;

import java.util.ArrayList;

public class AdaptadorFavoritos extends RecyclerView.Adapter<AdaptadorFavoritos.ViewHolderFavoritos> {

    ArrayList<ProductoModels> productos;

    public AdaptadorFavoritos(ArrayList<ProductoModels> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public ViewHolderFavoritos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorito, null, false);
        return new ViewHolderFavoritos(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFavoritos holder, int position) {
        holder.itemNombre.setText(productos.get(position).getNombre());
        holder.itemImagen.setImageResource(productos.get(position).getImagen());
        holder.itemPrecio.setText(Helpers.moneda(productos.get(position).getPrecio()));
        holder.itemBtn.setOnClickListener(v -> {
            MyOpenHelper moh = new MyOpenHelper(v.getContext());
            SQLiteDatabase db = moh.getWritableDatabase();

            ProductoController.removerFavorito(productos.get(position).getId(), moh, db, v.getContext());

        });
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public class ViewHolderFavoritos extends RecyclerView.ViewHolder {

        TextView itemNombre;
        ImageView itemImagen;
        TextView itemPrecio;
        Button itemBtn;

        public ViewHolderFavoritos(@NonNull View itemView) {
            super(itemView);

            itemNombre = (TextView) itemView.findViewById(R.id.itemNombreFavorito);
            itemImagen = (ImageView) itemView.findViewById(R.id.itemImagenFavorito);
            itemPrecio = (TextView) itemView.findViewById(R.id.itemPrecioFavorito);
            itemBtn = (Button) itemView.findViewById(R.id.itemBtnFavorito);
        }
    }
}