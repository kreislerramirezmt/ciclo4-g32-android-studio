package com.usa.edu.reto19.vista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.usa.edu.reto19.Helpers;
import com.usa.edu.reto19.R;

import java.util.Objects;

public class ServiciosActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);
        Helpers.actionBar(Objects.requireNonNull(getSupportActionBar()));

        TextView txtHome = findViewById(R.id.txtHomeServicios);
        txtHome.setText("¡Servicios!");
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
}