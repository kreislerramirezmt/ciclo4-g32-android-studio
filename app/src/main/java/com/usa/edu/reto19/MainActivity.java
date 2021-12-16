package com.usa.edu.reto19;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.usa.edu.reto19.vista.ProductosActivity;

import java.util.Objects;

/**
 * @author Kreisler
 * @version 3.0
 */
public class MainActivity extends AppCompatActivity {
    ImageView imagenSplash;
    /**
     *
     * @param sInstanceState sInstanceState
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle sInstanceState) {
        super.onCreate(sInstanceState);
        //setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        try{
            imagenSplash = (ImageView)findViewById(R.id.imagenPresentacion);
            new Handler().postDelayed(() -> {
                startActivity(new Intent(MainActivity.this, ProductosActivity.class));
                finish();
            }, 3000);
        }catch (Exception e){
            Log.e("ERROR DE APP: ", e.getMessage());
            e.printStackTrace();
        }
        //Helpers.actionBar(Objects.requireNonNull(getSupportActionBar()));
        TextView txtHome = findViewById(R.id.txtHomeInicio);
        txtHome.setText("¡Cargando...!");
    }

    /**
     *
     * @param menu menu
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuopciones, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     *
     * @param item item
     * @return boolean
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Helpers.menuSelected(this,item);
        return super.onOptionsItemSelected(item);
    }
}