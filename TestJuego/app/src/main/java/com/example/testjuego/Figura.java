package com.example.testjuego;

abstract class Figura {
    int xpos;
    int ypos;

    public Figura(int xpos, int ypos){
        this.xpos = xpos;
        this.ypos = ypos;
    }

    public int getXpos() {
        return xpos;
    }

    public void setXpos(int xpos) {
        xpos = xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public void setYpos(int ypos) {
        ypos = ypos;
    }

    abstract boolean estaDentro(int posx, int posy);
}
