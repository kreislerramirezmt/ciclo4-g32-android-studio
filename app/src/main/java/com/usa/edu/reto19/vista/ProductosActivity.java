package com.usa.edu.reto19.vista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.usa.edu.reto19.Helpers;
import com.usa.edu.reto19.R;
import com.usa.edu.reto19.controlador.AdaptadorProductos;
import com.usa.edu.reto19.controlador.MyOpenHelper;
import com.usa.edu.reto19.modelo.ProductoModels;

import java.util.ArrayList;
import java.util.Objects;

public class ProductosActivity extends AppCompatActivity {
    RecyclerView rcvProductos;
    ArrayList<ProductoModels> productos;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Helpers.actionBar(Objects.requireNonNull(getSupportActionBar()));

        TextView txtHome = findViewById(R.id.txtHomeProducto);
        txtHome.setText("¡Productos!");
        rcvProductos = (RecyclerView) findViewById(R.id.rcvProductos);
        rcvProductos.setLayoutManager(new LinearLayoutManager(this));

        progress = new ProgressDialog(ProductosActivity.this);
        progress.setMessage("Se está cargando la información");
        progress.setTitle("Progreso en curso");
        progress.setIcon(R.drawable.ic_baseline_timer_24);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        new consultarProductosAsync(ProductosActivity.this).execute();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuopciones, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Helpers.menuSelected(this,item);
        return super.onOptionsItemSelected(item);
    }

    public void consultarProductos(){
        productos = new ArrayList<>();
        MyOpenHelper dataBase = new MyOpenHelper(ProductosActivity.this);
        SQLiteDatabase db = dataBase.getReadableDatabase();

        Cursor c = dataBase.consultarProductos(db);

        while (c.moveToNext()) {
            @SuppressLint("Range") int id = c.getInt(c.getColumnIndex("id"));
            @SuppressLint("Range") String nombre = c.getString(c.getColumnIndex("nombre"));
            @SuppressLint("Range") int precio = c.getInt(c.getColumnIndex("precio"));
            @SuppressLint("Range") int imagen = c.getInt(c.getColumnIndex("imagen"));
            @SuppressLint("Range") int favorito = c.getInt(c.getColumnIndex("favorito"));

            productos.add(new ProductoModels(id, nombre, precio, imagen, favorito));
        }
    }
    public class consultarProductosAsync extends AsyncTask<Void, Void, Void> {


        Context context;
        //ProgressDialog progressDialog;

        public consultarProductosAsync(Context context) {
            this.context = context;
            //this.progressDialog = progressDialog;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                //Thread.sleep(4000);
                consultarProductos();
            }  catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progress.dismiss();

            AdaptadorProductos adapter = new AdaptadorProductos(productos);
            rcvProductos.setAdapter(adapter);

            Toast.makeText(ProductosActivity.this, "Productos cargados exitosamente...", Toast.LENGTH_LONG).show();
        }
    }
}