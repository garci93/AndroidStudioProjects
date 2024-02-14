package com.example.juegosinsprite;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MoverFiguras extends SurfaceView implements SurfaceHolder.Callback {
    private final int altoSurface = 1620;
    private GameThread gameThread;
    private Paint paint;
    private List<Figura> figurasRellenas = new ArrayList<>();
    private List<Figura> figurasVacias = new ArrayList<>();
    private int altoPantalla;
    private int anchoPantalla;
    private int radio = 100;
    private int anchoRectangulo = 150;
    private int altoRectangulo = 200;

    private int puntuacion = 0;
    private TextView txtPuntos;



    public MoverFiguras(Context context, AttributeSet attrs, TextView txtPuntos){
        super(context, attrs);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        altoPantalla = displayMetrics.heightPixels;
        anchoPantalla = displayMetrics.widthPixels;
        this.txtPuntos = txtPuntos;

        paint = new Paint();
        setBackgroundColor(Color.BLACK);

        generarFigurasRandom();
    }

    public void incrementarPuntuacion() {
        puntuacion++;
        actualizarPuntuacion();
    }

    public void setTxtPuntos(TextView txtPuntos) {
        this.txtPuntos = txtPuntos;
        actualizarPuntuacion();
    }

    public void actualizarPuntuacion() {
        ((Activity) getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (txtPuntos != null) {
                    txtPuntos.setText("Puntuación: " + puntuacion);
                }
            }
        });
    }

    public void generarFigurasRandom() {
        int NumeroFiguras = (int) (Math.random() * 2);

        if (NumeroFiguras == 0) {
            figurasRellenas.add(new Circulo((float) ((Math.random() * (anchoPantalla - radio*2)+radio)), (float) ((Math.random() * (altoSurface - radio*2)+radio)), radio, true));
            figurasVacias.add(new Circulo((float) ((Math.random() * (anchoPantalla - radio*2)+radio)), (float) ((Math.random() * (altoSurface - radio*2)+radio)), radio, false));
        } else {
            figurasRellenas.add(new Rectangulo((float) ((Math.random() * (anchoPantalla - anchoRectangulo*2)+anchoRectangulo)), (float) ((Math.random() * (altoSurface - altoRectangulo*2)+altoRectangulo)), anchoRectangulo, altoRectangulo, true));
            figurasVacias.add(new Rectangulo((float) ((Math.random() * (anchoPantalla - anchoRectangulo*2)+anchoRectangulo)), (float) ((Math.random() * (altoSurface - altoRectangulo*2)+altoRectangulo)), anchoRectangulo, altoRectangulo, false));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        for (Figura figura : figurasRellenas) {
            figura.onDraw(canvas, paint);
        }

        for (Figura figura : figurasVacias) {
            figura.onDraw(canvas, paint);
        }

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (Figura figura : figurasRellenas) {
                    if (figura.isHovered(event.getX(), event.getY())) {
                        figura.setInitialX(event.getX());
                        figura.setInitialY(event.getY());
                    }
                }

                break;
            case MotionEvent.ACTION_MOVE:
                for (Figura figura : figurasRellenas) {
                    figura.mover(event.getX(), event.getY());
                }

                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_UP:
                for (Figura figura : figurasRellenas) {
                    if (figura.getInitialX() != null && figura.getInitialY() != null) {
                        int index = figurasRellenas.indexOf(figura);
                        Figura figuraVacia = figurasVacias.get(index);

                        if (figuraVacia.isNear(figura.getX(), figura.getY())) {
                            figura.setX(figuraVacia.getX());
                            figura.setY(figuraVacia.getY());
                            incrementarPuntuacion();
                        }
                    }

                    figura.setInitialX(null);
                    figura.setInitialY(null);
                }
                break;
        }

        return true;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameThread = new GameThread(getHolder(), this);
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
