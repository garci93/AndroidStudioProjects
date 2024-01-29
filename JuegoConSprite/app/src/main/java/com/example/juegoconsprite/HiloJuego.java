package com.example.juegoconsprite;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class HiloJuego {
    private SurfaceHolder surfaceHolder;
    private MoverFiguras moverFiguras;
    private boolean run;

    static final long FPS = 10;

    public HiloJuego(SurfaceHolder surfaceHolder, MoverFiguras moverFiguras) {
        this.surfaceHolder = surfaceHolder;
        this.moverFiguras = moverFiguras;
        run = false;
    }

    public void setRunning(boolean run) {
        this.run = run;
    }

    public void run() {
        Canvas canvas;
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;

        while (run){
            canvas = null;
            startTime = System.currentTimeMillis();
            try {
                if (canvas != null){
                    canvas = surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder) {
                        moverFiguras.draw(canvas);
                    }
                }
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0){
                    Thread.sleep(sleepTime);
                } else {
                    Thread.sleep(10);
                }
            } catch (Exception e) {}
        }

        moverFiguras.postInvalidate();
    }
}