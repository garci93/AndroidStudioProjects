package com.example.pizzeria;

import java.util.ArrayList;

public class Pizza {
    private String nombre;
    private ArrayList<String> ingredientes;
    private TipoTamano tamano;
    private double precio;

    public Pizza(String nombre, ArrayList<String> ingredientes) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public TipoTamano getTamano() {
        return tamano;
    }

    public void setTamano(TipoTamano tamano) {
        this.tamano = tamano;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "nombre='" + nombre + '\'' +
                ", ingredientes=" + ingredientes +
                ", tamano=" + tamano +
                ", precio=" + precio +
                '}';
    }
}
