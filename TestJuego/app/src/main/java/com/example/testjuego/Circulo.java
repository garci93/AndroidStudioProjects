package com.example.testjuego;

import static java.lang.Math.abs;

import android.view.MotionEvent;

public class Circulo extends Figura{
    private float radio;
    public Circulo(float radio, int xpos, int ypos) {
        super(xpos, ypos);
        this.radio = radio;
    }

    @Override
    public boolean estaDentro(int x, int y){
        if (abs(x-xpos) <= radio && abs(y-ypos) <= radio){
            return true;
        } else {
            return false;
        }
    }

    public boolean onTouchEvent(MotionEvent event){
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                for(Figura f : figuras){
                    if(f instanceof Circulo) {
                        Circulo c = (Circulo) f;
                        if (c.estaDentro(x, y)) {
                            figuraActiva = c.getId();
                        }
                    }
                }
                break;
        }
    }
}
