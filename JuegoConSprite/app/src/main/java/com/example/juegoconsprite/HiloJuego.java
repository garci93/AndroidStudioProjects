package com.example.juegoconsprite;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class HiloJuego extends Thread{
    private SurfaceHolder surfaceHolder;
    private MoverFiguras moverFiguras;
    private boolean run;
    private long frames = 0;


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

        while (run) {
            canvas = null;
            startTime = System.currentTimeMillis();
            try {

                canvas = surfaceHolder.lockCanvas();
                if (canvas != null) {
                    synchronized (surfaceHolder) {

                        if (moverFiguras.jugador != null) {
                            moverFiguras.update();
                            frames++;
                        }
                        moverFiguras.postInvalidate();
                    }
                }
            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime);
                } else {
                    Thread.sleep(10);
                }
            } catch (Exception e) {
            }
        }

        moverFiguras.postInvalidate();
    }

    public long frames() {
        return frames;
    }

}