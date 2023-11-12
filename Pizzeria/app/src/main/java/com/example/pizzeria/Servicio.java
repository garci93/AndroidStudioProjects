package com.example.pizzeria;

import java.util.ArrayList;

public class Servicio {
    private ArrayList<Pizza> pizzas;

    public Servicio() {
        this.pizzas = DAOPizzeria.getInstance().getPizzas();
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }
}
