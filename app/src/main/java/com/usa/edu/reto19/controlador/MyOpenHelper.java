package com.usa.edu.reto19.controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.usa.edu.reto19.modelo.ProductoModels;

import java.util.ArrayList;


public class MyOpenHelper extends SQLiteOpenHelper {
    public static int VERSION = 5;

    public MyOpenHelper(@Nullable Context context) {
        super(context, DBLocal.DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        crearTablaProductos(db);
        ArrayList<ProductoModels> productos = Contenedor.getProductos();
        for(ProductoModels p : productos){
            insertarProducto(p, db);
        }
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldversion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + DBLocal.TABLE_PRODUCTOS);
        onCreate(db);
    }

    public void crearTablaProductos(@NonNull SQLiteDatabase db){
        db.execSQL("CREATE TABLE productos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, precio INTEGER, imagen INTEGER, favorito INTEGER)");
        //db.execSQL("CREATE TABLE " +DBLocal.TABLE_PRODUCTOS + " (" + DBLocal.TB_PRODUCTOS.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DBLocal.TB_PRODUCTOS.ID +  " TEXT)");
    }

    public void insertarProducto(@NonNull ProductoModels p, @NonNull SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put(DBLocal.TB_PRODUCTOS.NOMBRE, p.getNombre());
        cv.put(DBLocal.TB_PRODUCTOS.PRECIO, p.getPrecio());
        cv.put(DBLocal.TB_PRODUCTOS.IMAGEN, p.getImagen());
        cv.put(DBLocal.TB_PRODUCTOS.FAVORITO, p.getFavorito());

        db.insert(DBLocal.TABLE_PRODUCTOS, null, cv);
    }

    public void seleccionarFavorito(int id, @NonNull SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put(DBLocal.TB_PRODUCTOS.FAVORITO, 1);
        cv.put(DBLocal.TB_PRODUCTOS.ID, id);
        String[] arg = new String[]{""+id};
        db.update(DBLocal.TABLE_PRODUCTOS, cv, "id=?", arg);
        //db.insert(DBLocal.TABLE_PRODUCTOS, null, cv);
    }
    public void removerFavorito(int id, @NonNull SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put(DBLocal.TB_PRODUCTOS.FAVORITO, 0);
        cv.put(DBLocal.TB_PRODUCTOS.ID, id);
        String[] arg = new String[]{""+id};
        db.update(DBLocal.TABLE_PRODUCTOS, cv, "id=?", arg);
        //db.insert(DBLocal.TABLE_PRODUCTOS, null, cv);
    }

    public Cursor consultarProductos(@NonNull SQLiteDatabase db){
        return db.query(DBLocal.TABLE_PRODUCTOS, null, null, null, null, null, null);
    }

    public Cursor leerProductosFavoritos(@NonNull SQLiteDatabase db){
        return db.rawQuery("SELECT * FROM productos WHERE favorito = 1;", null);
    }
}
