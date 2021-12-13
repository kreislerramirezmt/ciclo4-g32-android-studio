package com.usa.edu.reto19.controlador;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class ProductoController {

    public static void anadirFavorito(int id, MyOpenHelper dataBase, SQLiteDatabase db, Context context){

        try {
            dataBase.seleccionarFavorito(id, db);
            Log.e("TAG_FAVORITO", "Se añadio como favorito.");
            Toast.makeText(context,"Se añadio como favorito.",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            dataBase.close();
        }
    }
    public static void removerFavorito(int id, MyOpenHelper dataBase, SQLiteDatabase db, Context context){

        try {
            dataBase.removerFavorito(id, db);
            Log.e("TAG_FAVORITO", "Se removio de favorito.");
            Toast.makeText(context,"Se removio de favorito.",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            dataBase.close();
        }
    }
}
