package com.usa.edu.reto19.modelo;

public class Sucursal {
    public int id;
    public String nombre;
    public double longitud;
    public double latitud;
    public int imagen;
    public String descripcion;

    public Sucursal(String nombre, double longitud, double latitud) {
        this.nombre = nombre;
        this.longitud = longitud;
        this.latitud = latitud;
    }
}
