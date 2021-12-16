package com.usa.edu.reto19.controlador;

import com.usa.edu.reto19.R;
import com.usa.edu.reto19.modelo.ProductoModels;
import com.usa.edu.reto19.modelo.Sucursal;

import java.util.ArrayList;

public class Contenedor {
    public static ArrayList<ProductoModels> getProductos(){
        ArrayList<ProductoModels> productos = new ArrayList<>();

        productos.add(new ProductoModels("Postre de galleta", 14000, R.drawable.postregalleta, 0));
        productos.add(new ProductoModels("Postre frío de mango", 15000, R.drawable.postrepina, 0));
        productos.add(new ProductoModels("Postre Napoleón", 16000, R.drawable.postrenapoleon, 0));
        productos.add(new ProductoModels("Postre de Naranja", 18000, R.drawable.postrenaranja, 0));
        productos.add(new ProductoModels("Postre de Manzana", 17000, R.drawable.postremanzana, 0));

        return productos;
    }
    public static ArrayList<Sucursal> getSucursales(){
        ArrayList<Sucursal> sucursales = new ArrayList<>();

        sucursales.add(new Sucursal("Sucursal de Bogotá", 9.3152103,-75.4018506));
        sucursales.add(new Sucursal("Sucursal de Sincelejo", 9.3152103,-75.4018506));

        return sucursales;
    }
}
