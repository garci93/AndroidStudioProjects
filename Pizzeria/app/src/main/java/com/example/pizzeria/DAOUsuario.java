package com.example.pizzeria;

import java.util.ArrayList;

public class DAOUsuario {
    private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    private static DAOUsuario daousuario = null;

    private DAOUsuario() {
        usuarios.add(new Usuario("Juanito","123abcd"));
    }

    public static DAOUsuario getInstance(){
        if (daousuario == null){
            return daousuario = new DAOUsuario();
        } else {
            return daousuario;
        }
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
}
