package com.example.juegoconsprite;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class MoverFiguras extends SurfaceView implements SurfaceHolder.Callback {
    private HiloJuego hiloJuego;
    private Rectangulo rectangulo;
    private Circulo circulo;
    private Linea linea;
    private Paint paint;
    private Paint linePaint;
    private List<Figura> figuras = new ArrayList<>();


    public MoverFiguras(Context context) {
        super(context);
        rectangulo = new Rectangulo(200, 400, 700, 500);
        figuras.add(rectangulo);

        circulo = new Circulo(100, 100, 100);
        figuras.add(circulo);

        linea = new Linea();

        paint = new Paint();
        linePaint = new Paint();
        setBackgroundColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        for (Figura figura : figuras) {
            figura.onDraw(canvas, paint);
        }

        linea.onDraw(canvas, linePaint);
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

                linea.guardarPuntoInicial(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                for (Figura figura : figuras) {
                    figura.mover(event.getX(), event.getY());
                }

                linea.guardarPunto(event.getX(), event.getY());
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
        hiloJuego = new HiloJuego(getHolder(), this);
        hiloJuego.setRunning(true);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        boolean retry = true;
        hiloJuego.setRunning(false);

        while (retry) {
            try {
                hiloJuego.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
    }
}