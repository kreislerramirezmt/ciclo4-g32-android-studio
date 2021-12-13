package com.usa.edu.reto19.vista;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.usa.edu.reto19.Helpers;
import com.usa.edu.reto19.R;

import java.util.Objects;

public class SucursalesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucursales);
        Helpers.actionBar(Objects.requireNonNull(getSupportActionBar()));

        TextView txtHome = findViewById(R.id.txtHomeSucursales);
        txtHome.setText("¡Sucursales!");

        Button btn1 = findViewById(R.id.itemBtnSucur1);
        btn1.setOnClickListener(v -> {
            Helpers.toastMod(v.getContext());
        });
        Button btn2 = findViewById(R.id.itemBtnSucur2);
        btn2.setOnClickListener(v -> {
            Helpers.toastMod(v.getContext());
        });
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