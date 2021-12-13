package com.usa.edu.reto19.modelo;

public class ProductoModels {

    public int id;
    public String nombre;
    public int precio;
    public int imagen;
    public String descripcion;
    public int favorito;

    public ProductoModels(int id, String nombre, int precio, int imagen, int favorito) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.favorito = favorito;
    }

    public ProductoModels(String nombre, int precio, int imagen, int favorito) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
        this.favorito = favorito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getFavorito() {
        return favorito;
    }

    public void setFavorito(int favorito) {
        this.favorito = favorito;
    }
}
