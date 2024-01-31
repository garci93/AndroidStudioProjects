package com.example.juegoconsprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {

    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 3;
    private int x = 0;
    private int y = 0;
    private int xSpeed = 2;
    private int ySpeed = 0;
    private MoverFiguras moverFiguras;
    private Bitmap bmp;
    private int currentFrame = 0;
    private int width;
    private int height;


    public Sprite (MoverFiguras moverFiguras, Bitmap bmp) {
        this.moverFiguras = moverFiguras;
        this.bmp = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
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
        currentFrame = ++currentFrame % (BMP_COLUMNS+1);
    }

    public void onDraw(Canvas canvas) {
        update();
        int direction;
        if (xSpeed == 0) {
            if (ySpeed > 0) {
                direction = 0;
            } else {
                direction = 3;
            }
        } else {
            if (xSpeed > 0) {
                direction = 2;
            } else {
                direction = 1;
            }
        }
        int srcX;
        if (currentFrame == 3) {
            srcX = width;
        } else {
            srcX = currentFrame * width;
        }
        int srcY = direction * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + width, y + height);

        canvas.drawBitmap(bmp, src, dst, null);
    }
}
