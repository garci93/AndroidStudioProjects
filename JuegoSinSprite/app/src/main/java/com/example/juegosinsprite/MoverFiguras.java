package com.example.juegosinsprite;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MoverFiguras extends SurfaceView implements SurfaceHolder.Callback {
    private GameThread gameThread;
    private Rectangulo rectangulo;
    private int radioCirculo=100;
    private int anchoRectangulo=500;
    private int altoRectangulo=700;
    private Circulo circulo;
    private Paint paint;
    private Paint linePaint;
    private List<Figura> figuras = new ArrayList<>();


    public MoverFiguras(Context context) {
        super(context);
        Random random = new Random();
        setBackgroundColor(Color.WHITE);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        int x = random.nextInt(width - anchoRectangulo - 1);
        int y = random.nextInt(height - altoRectangulo - 1);
        rectangulo = new Rectangulo(x, y, anchoRectangulo, altoRectangulo);
        figuras.add(rectangulo);
        x = random.nextInt(width - radioCirculo - 1);
        circulo = new Circulo(x, x, radioCirculo);
        figuras.add(circulo);

        paint = new Paint();
        linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        for (Figura figura : figuras) {
            figura.onDraw(canvas, paint);
        }

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (Figura figura : figuras) {
                    if (figura.isHovered(event.getX(), event.getY())) {
                        figura.setxInicial(event.getX());
                        figura.setyInicial(event.getY());
                    }
                }

                break;
            case MotionEvent.ACTION_MOVE:
                for (Figura figura : figuras) {
                    figura.mover(event.getX(), event.getY());
                }

                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_UP:
                for (Figura figura : figuras) {
                    figura.setxInicial(null);
                    figura.setyInicial(null);
                }

                break;
        }

        return true;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameThread = new GameThread(getHolder(), this);
        setBackgroundColor(Color.WHITE);
        gameThread.setRunning(true);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        boolean retry = true;
        gameThread.setRunning(false);

        while(retry) {
            try {
                gameThread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
    }
}
