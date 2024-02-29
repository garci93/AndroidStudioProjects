package com.example.juegoconsprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.List;

public class Points
{
    private float x;
    private float y;
    private Bitmap bmp;
    private int life = 10;
    private List<Points> points;
    public Points(List<Points> points, MoverFiguras moverFiguras, float x, float y, Bitmap bmp) {
        this.x = Math.min(Math.max(x - bmp.getWidth() / 2, 0), moverFiguras.getWidth() - bmp.getWidth());
        this.y = Math.min(Math.max(y - bmp.getHeight() / 2, 0), moverFiguras.getHeight() - bmp.getHeight());
        this.bmp = bmp;
        this.points = points;
    }
    public void onDraw(Canvas canvas) {
        update();
        canvas.drawBitmap(bmp, x, y, null);
    }
    public void update() {
        if (--life < 1) {
            points.remove(this);
        }
    }
}
