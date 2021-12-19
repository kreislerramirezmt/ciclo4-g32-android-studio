package com.usa.edu.reto19.vista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.usa.edu.reto19.Helpers;
import com.usa.edu.reto19.R;
import com.usa.edu.reto19.controlador.AdaptadorServicios;
import com.usa.edu.reto19.controlador.WEBSERVICE;
import com.usa.edu.reto19.modelo.MySingleton;
import com.usa.edu.reto19.modelo.Servicio;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class ServiciosActivity extends AppCompatActivity {
    RecyclerView rcvServicios;
    ArrayList<Servicio> servicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);
        Helpers.actionBar(Objects.requireNonNull(getSupportActionBar()));

        TextView txtHome = findViewById(R.id.txtHomeServicios);
        txtHome.setText("¡Servicios!");
        rcvServicios = findViewById(R.id.rcvServicio);
        rcvServicios.setLayoutManager(new LinearLayoutManager(this));
        getWebserviceServicios();
    }

    public void getWebserviceServicios() {
        String url = WEBSERVICE.CRUD_SERVICIOS;
        servicios = new ArrayList<>();
        ProgressDialog mProgressBar = new ProgressDialog(ServiciosActivity.this);
        mProgressBar.setCancelable(true);
        mProgressBar.setTitle("Consultando información...");
        mProgressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressBar.show();

        JsonObjectRequest jsonObject = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    Log.w("TAG", "RESPONSE: " + response.toString());
                    try {
                        JSONArray arrayServicio = response.getJSONArray("items");
                        Log.w("TAG", "RESPONSE SUC: " + arrayServicio.toString());
                        for (int i = 0; i < arrayServicio.length(); i++) {

                            JSONObject jsonObject1 = arrayServicio.getJSONObject(i);
                            int id = jsonObject1.getInt("id");
                            String nombre = jsonObject1.getString("nombre");
                            String descripcion = jsonObject1.getString("descripcion");
                            String imagen = jsonObject1.getString("imagen");
                            servicios.add(new Servicio(id, nombre, imagen, descripcion));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    AdaptadorServicios adapter = new AdaptadorServicios(servicios);
                    rcvServicios.setAdapter(adapter);
                    mProgressBar.cancel();
                }, error -> {
                    Helpers.toastMod(getApplicationContext(), "Algo salio mal" + error.toString());
                    Log.e("TAG_ERR", "Algo salio mal" + error.toString());
                    mProgressBar.cancel();
                });

        MySingleton.getInstance(this).addToRequestQueue(jsonObject);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuopciones, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Helpers.menuSelected(this, item);
        return super.onOptionsItemSelected(item);
    }
}