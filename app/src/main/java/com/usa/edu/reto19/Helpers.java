package com.usa.edu.reto19;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;

import com.usa.edu.reto19.vista.FavoritosActivity;
import com.usa.edu.reto19.vista.ProductosActivity;
import com.usa.edu.reto19.vista.ServiciosActivity;
import com.usa.edu.reto19.vista.SucursalesActivity;


public class Helpers{
    public static String defaultTxt = "Esta función se implementará en la siguiente versión :D";
    public static void actionBar(@NonNull ActionBar context) {
        context.setDisplayShowHomeEnabled(true);
        context.setTitle(R.string.app_title);
        context.setSubtitle(R.string.app_subtitle);

        context.setLogo(R.mipmap.ic_launcher_round);
        context.setDisplayUseLogoEnabled(true);
    }
    public static void actionBar(@NonNull ActionBar context, String title){
        context.setDisplayShowHomeEnabled(true);
        context.setTitle(title);

        context.setLogo(R.mipmap.ic_launcher_round);
        context.setDisplayUseLogoEnabled(true);
    }
    public static void actionBar(@NonNull ActionBar context, String title, String Subtitle){
        context.setDisplayShowHomeEnabled(true);
        context.setTitle(title);
        context.setSubtitle(Subtitle);

        context.setLogo(R.mipmap.ic_launcher_round);
        context.setDisplayUseLogoEnabled(true);
    }
    public static void toastMod(Context context){
        Toast.makeText(context,defaultTxt,Toast.LENGTH_LONG).show();
    }

    @SuppressLint("NonConstantResourceId")
    public static void menuSelected(@NonNull Context context,@NonNull MenuItem item){
        switch (item.getItemId()) {
            case R.id.op0favoritos:
                context.startActivity(new Intent(context, FavoritosActivity.class));
                break;
            case R.id.op1Productos:
                context.startActivity(new Intent(context, ProductosActivity.class));
                break;
            case R.id.op2Servicios:
                context.startActivity(new Intent(context, ServiciosActivity.class));
                break;
            case R.id.op3Sucursales:
                context.startActivity(new Intent(context, SucursalesActivity.class));
                break;
            default:
                break;
        }
    }
}
