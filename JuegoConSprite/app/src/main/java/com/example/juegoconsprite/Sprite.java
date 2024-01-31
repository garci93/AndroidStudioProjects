package com.example.juegoconsprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {

    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 3;
    private int x = 0;
    private int y = 0;
    private int xSpeed = 20;
    private int ySpeed = 25;
    private MoverFiguras moverFiguras;
    private Bitmap bmp;
    private int currentFrame = 0;
    private int width;
    private int height;

    private int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 0, 2 };


    public Sprite (MoverFiguras moverFiguras, Bitmap bmp) {
        this.moverFiguras = moverFiguras;
        this.bmp = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
    }

    public Sprite (MoverFiguras moverFiguras, int xSpeed, int ySpeed, Bitmap bmp) {
        this.moverFiguras = moverFiguras;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
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
        currentFrame = ++currentFrame % BMP_COLUMNS;
    }

    public void onDraw(Canvas canvas) {

        update();

        int srcX = currentFrame * width;
        int srcY = getAnimationRow() * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + width, y + height);

        canvas.drawBitmap(bmp, src, dst, null);
    }

    private int getAnimationRow() {
        double dirDouble = (Math.atan2(xSpeed, ySpeed) / (Math.PI / 2) + 2);
        int direction = (int) Math.round(dirDouble) % BMP_ROWS;
        direction = DIRECTION_TO_ANIMATION_MAP[direction];
        return direction;
    }
}
