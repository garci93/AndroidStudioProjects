package com.example.juegosinsprite;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Figura {
    protected float x;
    protected float y;
    protected Float xInicial;
    protected Float yInicial;

    public Figura(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Float getxInicial() {
        return xInicial;
    }

    public void setxInicial(Float xInicial) {
        this.xInicial = xInicial;
    }

    public Float getyInicial() {
        return yInicial;
    }

    public void setyInicial(Float yInicial) {
        this.yInicial = yInicial;
    }

    public void mover(float xActual, float yActual) {
        if (xInicial != null && yInicial != null) {
            setX(getX() + xActual - xInicial);
            setY(getY() + yActual - yInicial);
            xInicial = xActual;
            yInicial = yActual;
        }
    }

    public abstract boolean isHovered(float x, float y);

    public abstract void onDraw(Canvas canvas, Paint paint);
}
