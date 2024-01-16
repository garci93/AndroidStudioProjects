package com.example.testjuego;

public class Rectangulo extends Figura {
    private Integer ancho, alto;

    Rectangulo (int xpos, int ypos, int ancho, int alto){
        super(xpos, ypos);
    }

    public boolean estaDentro(int x, int y){
        if (xpos <= x && x <= xpos+ancho && ypos <= y && y <= ypos+alto){
            return true;
        } else {
            return false;
        }
    }
}
