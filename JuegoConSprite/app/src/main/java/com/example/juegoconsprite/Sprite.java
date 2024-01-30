package com.example.juegoconsprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Sprite {
    private int x = 0;
    private int y = 0;
    private int xSpeed = 10;
    private int ySpeed = 10;
    private MoverFiguras moverFiguras;
    private Bitmap bmp;

    public Sprite (MoverFiguras moverFiguras, Bitmap bmp) {
        this.moverFiguras = moverFiguras;
        this.bmp = bmp;
    }

    private void update() {
        x = x + xSpeed;
        y = y + ySpeed;
        if (x > moverFiguras.getWidth() - bmp.getWidth() - xSpeed || x + xSpeed < 0) {
            xSpeed = -xSpeed;
        }
        if (y > moverFiguras.getHeight() - bmp.getHeight() - ySpeed || y + ySpeed < 0) {
            ySpeed = -ySpeed;
        }
    }

    public void onDraw(Canvas canvas) {
        update();
        canvas.drawBitmap(bmp, x, y, null);
    }
}
