package com.example.juegoconsprite;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

public class Sprite {

    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 4;
    private static final int MAX_SPEED = 20;
    private static final int BASE_SPEED = 20;
    private boolean isOOB = false;
    private int time = 0;
    private int x = 0;
    private int y = 0;
    private String tipo;
    private int xSpeed = 0;
    private int ySpeed = 0;
    private MoverFiguras moverFiguras;
    private Bitmap bmp;
    private int currentFrame = 0;
    private int width;
    private int height;

    private int[] DIRECTION_TO_ANIMATION_MAP = { 3, 1, 0, 2 };
    private int bonus=0;
    private int maxScore=0;


    public Sprite (MoverFiguras moverFiguras, Bitmap bmp) {
        this.moverFiguras = moverFiguras;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
        this.bmp = bmp;
        tipo = "OBSTACLE";
        Random rnd = new Random();
        x = moverFiguras.getWidth();
        y = rnd.nextInt(moverFiguras.getHeight() - height);
        xSpeed = -BASE_SPEED;
    }

    public Sprite (MoverFiguras moverFiguras, Bitmap bmp, String tipo) {
        this.moverFiguras = moverFiguras;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;
        this.bmp = bmp;
        this.tipo = tipo;
        Random rnd = new Random();
        switch (tipo) {
            case "PLAYER":
                x = moverFiguras.getWidth() / 5;
                y = moverFiguras.getHeight() / 2;
                loadMaxScore(moverFiguras.getContext());
                break;
            case "ITEM":
                x = moverFiguras.getWidth();
                y = rnd.nextInt(moverFiguras.getHeight() - height);
                xSpeed = -BASE_SPEED+10;
                break;
            case "OBSTACLE":
                x = moverFiguras.getWidth();
                y = rnd.nextInt(moverFiguras.getHeight() - height);
                xSpeed = -BASE_SPEED;
                break;
        }
    }

    public boolean isCollision(float x2, float y2) {
        return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
    }

    public boolean isOOB() {
        return isOOB;
    }

    void update() {
        time++;
        x = x + xSpeed;
        y = y + ySpeed;
        if (tipo == "OBSTACLE" || tipo == "ITEM"){
            if (this.x < 0 - this.width){
                this.xSpeed = 0;
                this.isOOB = true;
            }
        }
        /*if (x > moverFiguras.getWidth() - bmp.getWidth() - xSpeed || x + xSpeed < 0) {
            xSpeed = -xSpeed;
        }
        if (y > moverFiguras.getHeight() - bmp.getHeight() - ySpeed || y + ySpeed < 0) {
            ySpeed = -ySpeed;
        }*/
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

    public String getTipo() {
        return tipo;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void mover(int xActual, int yActual) {
        setX(getX() + xActual - x);
        setY(getY() + yActual - y);
        x = xActual;
        y = yActual;
        if (x > moverFiguras.getWidth() - bmp.getWidth()/BMP_COLUMNS) {
            x = moverFiguras.getWidth() - bmp.getWidth()/BMP_COLUMNS;
        }
        if (y > moverFiguras.getHeight() - bmp.getHeight()/BMP_ROWS) {
            y = moverFiguras.getHeight() - bmp.getHeight()/BMP_ROWS;
        }
    }

    private int getAnimationRow() {
        double dirDouble = (Math.atan2(xSpeed, ySpeed) / (Math.PI / 2) + 2);
        int direction = (int) Math.round(dirDouble) % BMP_ROWS;
        direction = DIRECTION_TO_ANIMATION_MAP[direction];
        return direction;
    }

    public void addBonus() {
        moverFiguras.playBonus();
        bonus++;
    }

    public int getBonus() {
        return bonus;
    }

    public String getScore() {
        int res = bonus*100+time;
        if (res > maxScore) maxScore = res;
        saveMaxScore(moverFiguras.getContext(), maxScore);
        return String.valueOf(bonus*100+time);
    }

    public String getMaxScore() {
        return String.valueOf(maxScore);
    }

    public void loadMaxScore(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("GamePreferences", Context.MODE_PRIVATE);
        maxScore = preferences.getInt("maxScore", 0);
    }

    private void saveMaxScore(Context context, int score) {
        SharedPreferences preferences = context.getSharedPreferences("GamePreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("maxScore", score);
        editor.apply();
    }

    public int getTime() {
        return time;
    }
}
