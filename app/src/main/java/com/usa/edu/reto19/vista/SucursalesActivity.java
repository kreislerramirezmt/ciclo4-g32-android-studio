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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.usa.edu.reto19.Helpers;
import com.usa.edu.reto19.R;
import com.usa.edu.reto19.controlador.AdaptadorFavoritos;
import com.usa.edu.reto19.controlador.AdaptadorSucursales;
import com.usa.edu.reto19.controlador.Contenedor;
import com.usa.edu.reto19.controlador.MyOpenHelper;
import com.usa.edu.reto19.controlador.WEBSERVICE;
import com.usa.edu.reto19.modelo.MySingleton;
import com.usa.edu.reto19.modelo.ProductoModels;
import com.usa.edu.reto19.modelo.Sucursal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        rcvSucursales = findViewById(R.id.rcvSucursales);
        rcvSucursales.setLayoutManager(new LinearLayoutManager(this));
        getWebserviceSucursales();
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
    public void getWebserviceSucursales() {
        String url = WEBSERVICE.CRUD_SUCURSALES;
        sucursales = new ArrayList<>();
        ProgressDialog mProgressBar = new ProgressDialog(SucursalesActivity.this);
        mProgressBar.setCancelable(true);
        mProgressBar.setTitle("Consultando sucursales...");
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressBar.show();

        JsonObjectRequest jsonObject = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    Log.w("TAG", "RESPONSE: " + response.toString());
                    Sucursal sucTemp = null;
                    try {
                        JSONArray arraySucursal = response.getJSONArray("items");
                        Log.w("TAG", "RESPONSE SUC: " + arraySucursal.toString());
                        for (int i = 0; i < arraySucursal.length(); i++) {

                            JSONObject jsonObject1 = arraySucursal.getJSONObject(i);
                            int id = jsonObject1.getInt("id");
                            String nombre = jsonObject1.getString("nombre");
                            String direccion = jsonObject1.getString("direccion");
                            double latitud = jsonObject1.getDouble("latitud");
                            double longitud = jsonObject1.getDouble("longitud");
                            String imagen = jsonObject1.getString("imagen");
                            sucTemp = new Sucursal(id, nombre, direccion, latitud, longitud, imagen);
                            sucursales.add(sucTemp);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    AdaptadorSucursales adapter = new AdaptadorSucursales(sucursales);
                    rcvSucursales.setAdapter(adapter);
                    mProgressBar.cancel();
                }, error -> {
                    Helpers.toastMod(getApplicationContext(),"Algo salio mal" + error.toString());
                    Log.e("TAG_ERR", "Algo salio mal" + error.toString());
                    mProgressBar.cancel();
                });

        MySingleton.getInstance(this).addToRequestQueue(jsonObject);
    }
}