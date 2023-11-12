package com.example.pizzeria;

import java.util.ArrayList;

public class DAOPizzeria {
    private ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
    private static DAOPizzeria daopizzeria = null;

    private DAOPizzeria() {
        pizzas.add(new Pizza("Margarita",
                new ArrayList<String>(){{add("Queso");add("Tomate");}}));
        pizzas.add(new Pizza("Barbacoa",
                new ArrayList<String>(){{add("Queso");add("Tomate");add("Salsa barbacoa");add("Bacon");}}));
        pizzas.add(new Pizza("Carbonara",
                new ArrayList<String>(){{add("Queso");add("Tomate");add("Bacon");add("Champiñones");add("Salsa carbonara");}}));
    }

    public static DAOPizzeria getInstance(){
        if (daopizzeria == null){
            return daopizzeria = new DAOPizzeria();
        } else {
            return daopizzeria;
        }
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }
}
