package com.example.pizzeria;

import java.util.ArrayList;

public class Servicio {
    private ArrayList<Pizza> pizzas;
    private ArrayList<Usuario> usuarios;
    private static Servicio servicio = null;

    public Servicio() {

        this.usuarios = DAOUsuario.getInstance().getUsuarios();
        this.pizzas = DAOPizzeria.getInstance().getPizzas();
    }

    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
    public static Servicio getInstance(){
        if (servicio == null){
            return servicio = new Servicio();
        } else {
            return servicio;
        }
    }

    public boolean existeUsuario(String nombre,String password){
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombre) && usuario.getPassword().equals(password))
                return true;
        }
        return false;
    }
}
