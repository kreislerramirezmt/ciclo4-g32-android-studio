package com.usa.edu.reto19.vista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.usa.edu.reto19.Helpers;
import com.usa.edu.reto19.R;
import com.usa.edu.reto19.controlador.AdaptadorFavoritos;
import com.usa.edu.reto19.controlador.DBLocal;
import com.usa.edu.reto19.controlador.MyOpenHelper;
import com.usa.edu.reto19.modelo.ProductoModels;

import java.util.ArrayList;
import java.util.Objects;

public class FavoritosActivity extends AppCompatActivity {

    RecyclerView rcvFavoritos;
    ArrayList<ProductoModels> productos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        Helpers.actionBar(Objects.requireNonNull(getSupportActionBar()));
        TextView txtHome = findViewById(R.id.txtHomeFavoritos);
        txtHome.setText("¡Favoritos ❤!");
        rcvFavoritos = (RecyclerView) findViewById(R.id.rcvFavoritos);
        rcvFavoritos.setLayoutManager(new LinearLayoutManager(this));
        new FavoritosActivity.consultarFavoritosAsync(FavoritosActivity.this).execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuopciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Helpers.menuSelected(this, item);
        return super.onOptionsItemSelected(item);
    }

    public void consultarProductos(){
        productos = new ArrayList<>();
        MyOpenHelper dataBase = new MyOpenHelper(FavoritosActivity.this);
        SQLiteDatabase db = dataBase.getReadableDatabase();

        Cursor c = dataBase.leerProductosFavoritos(db);

        while (c.moveToNext()) {
            @SuppressLint("Range") int id = c.getInt(c.getColumnIndex("id"));
            @SuppressLint("Range") String nombre = c.getString(c.getColumnIndex("nombre"));
            @SuppressLint("Range") int precio = c.getInt(c.getColumnIndex("precio"));
            @SuppressLint("Range") int imagen = c.getInt(c.getColumnIndex("imagen"));
            @SuppressLint("Range") int favorito = c.getInt(c.getColumnIndex("favorito"));

            productos.add(new ProductoModels(id, nombre, precio, imagen, favorito));
        }
    }
    public class consultarFavoritosAsync extends AsyncTask<Void, Void, Void> {


        Context context;

        public consultarFavoritosAsync(Context context) {
            this.context = context;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                consultarProductos();
            }  catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            AdaptadorFavoritos adapter = new AdaptadorFavoritos(productos);
            rcvFavoritos.setAdapter(adapter);
            Toast.makeText(FavoritosActivity.this, "Favoritos cargados exitosamente...", Toast.LENGTH_LONG).show();
        }
    }
}