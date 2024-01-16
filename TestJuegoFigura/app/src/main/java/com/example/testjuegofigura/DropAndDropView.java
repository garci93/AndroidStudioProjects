package com.example.testjuegofigura;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class DropAndDropView extends SurfaceView implements SurfaceHolder.Callback {

    HiloPintar thread;
    Rectangulo rectangulo;
    public DropAndDropView(Context context) {

        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        boolean retry = true;
        thread.setRunning(false);

        while (retry){
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) { }
        }
    }

    @Override
    public void onDraw(Canvas canvas){

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        return false;
    }


}