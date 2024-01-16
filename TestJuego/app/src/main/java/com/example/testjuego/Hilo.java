package com.example.testjuego;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class Hilo extends Thread {

    private SurfaceHolder sh;

    private MoverFiguras view;

    private boolean run;

    public Hilo(SurfaceHolder sh, MoverFiguras view){
        this.sh = sh;
        this.view = view;
        run = false;
    }

    public void setRunning(boolean run){this.run = run;}

    public void run(){
        Canvas canvas;
        while(run) {
            canvas = null;
            try {
                canvas = sh.lockCanvas(null);
                synchronized (sh){
                    view.onDraw(canvas);
                }
            } finally {
                if(canvas != null)
                    sh.unlockCanvasAndPost(canvas);
            }
        }
    }
}
