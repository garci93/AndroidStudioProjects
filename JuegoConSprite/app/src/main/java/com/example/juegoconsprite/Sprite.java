package com.example.juegoconsprite;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

public class Sprite {

    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 3;
    private static final int MAX_SPEED = 15;
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
        Random rnd = new Random();
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
        this.bmp = bmp;
        x = rnd.nextInt(moverFiguras.getWidth() - width);
        y = rnd.nextInt(moverFiguras.getHeight() - height);
        xSpeed = rnd.nextInt(MAX_SPEED*2) - MAX_SPEED;
        ySpeed = rnd.nextInt(MAX_SPEED*2) - MAX_SPEED;
    }

    public boolean isCollition(float x2, float y2) {
        return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
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
