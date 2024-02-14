package com.example.juegosinsprite;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Rectangulo extends Figura {
    private double width;
    private double height;

    public Rectangulo(float x, float y, double width, double height, boolean relleno) {
        super(x, y, relleno);
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public boolean isHovered(float x, float y) {
        return x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + height;
    }

    @Override
    public void onDraw(Canvas canvas, Paint paint) {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        if (this.relleno) {
            canvas.drawRect(getX(), getY(), getX()+(float)getWidth(), getY()+(float)getHeight(), paint);
        } else {
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);
            canvas.drawRect(getX(), getY(), getX()+(float)getWidth(), getY()+(float)getHeight(), paint);
        }
    }
}

