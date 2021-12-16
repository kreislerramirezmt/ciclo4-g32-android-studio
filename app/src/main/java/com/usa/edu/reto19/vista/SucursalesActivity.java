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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.usa.edu.reto19.Helpers;
import com.usa.edu.reto19.R;
import com.usa.edu.reto19.controlador.AdaptadorFavoritos;
import com.usa.edu.reto19.controlador.AdaptadorSucursales;
import com.usa.edu.reto19.controlador.Contenedor;
import com.usa.edu.reto19.controlador.MyOpenHelper;
import com.usa.edu.reto19.modelo.ProductoModels;
import com.usa.edu.reto19.modelo.Sucursal;

import java.util.ArrayList;
import java.util.Objects;

public class SucursalesActivity extends AppCompatActivity {
    RecyclerView rcvSucursales;
    ArrayList<Sucursal> sucursales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucursales);
        Helpers.actionBar(Objects.requireNonNull(getSupportActionBar()));

        TextView txtHome = findViewById(R.id.txtHomeSucursales);
        txtHome.setText("¡Sucursales!");
        rcvSucursales = (RecyclerView) findViewById(R.id.rcvSucursales);
        rcvSucursales.setLayoutManager(new LinearLayoutManager(this));
        new SucursalesActivity.cconsultarSucursalesAsync(SucursalesActivity.this).execute();

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

    public void consultarSucursales(){
        sucursales = new ArrayList<>();
        ArrayList<Sucursal> sucur = Contenedor.getSucursales();
        for(Sucursal s : sucur){
            sucursales.add(new Sucursal(s.getNombre(),s.getLongitud(),s.getLatitud(),s.getImagen()));
        }
    }
    public class cconsultarSucursalesAsync extends AsyncTask<Void, Void, Void> {


        Context context;

        public cconsultarSucursalesAsync(Context context) {
            this.context = context;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                consultarSucursales();
            }  catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            AdaptadorSucursales adapter = new AdaptadorSucursales(sucursales);
            rcvSucursales.setAdapter(adapter);
            Toast.makeText(SucursalesActivity.this, "Sucursales cargadas exitosamente...", Toast.LENGTH_LONG).show();
        }
    }
}