package com.example.juegoconsprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Sprite {
    private int x = 0;
    private int xSpeed = 5;
    private MoverFiguras moverFiguras;
    private Bitmap bmp;

    public Sprite (MoverFiguras moverFiguras, Bitmap bmp) {
        this.moverFiguras = moverFiguras;
        this.bmp = bmp;
    }

    private void update() {
        x = x + xSpeed;
    }

    public void onDraw(Canvas canvas) {
        update();
        canvas.drawBitmap(bmp, x, 10, null);
    }
}
