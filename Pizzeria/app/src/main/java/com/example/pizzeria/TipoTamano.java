package com.example.pizzeria;

public enum TipoTamano {
    S("Pequeña"), M("Mediana"), L("Grande");
    private String nombre;

    TipoTamano(String nombre) {
        this.nombre = nombre;
    }
}
