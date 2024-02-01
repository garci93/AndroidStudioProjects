package com.example.juegoconsprite;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.os.LocaleListCompat;

import java.util.ArrayList;
import java.util.List;

public class MoverFiguras extends SurfaceView implements SurfaceHolder.Callback {
    private Bitmap bmp;
    private HiloJuego hiloJuego;
    private Paint paint;
    private Paint linePaint;
    private Bitmap bmpBlood;
    private List<Sprite> sprites = new ArrayList<Sprite>();

    private void createSprites() {
        sprites.add(createSprite(R.drawable.bad1));
        sprites.add(createSprite(R.drawable.bad2));
        sprites.add(createSprite(R.drawable.bad3));
        sprites.add(createSprite(R.drawable.bad4));
        sprites.add(createSprite(R.drawable.bad5));
        sprites.add(createSprite(R.drawable.bad6));
        sprites.add(createSprite(R.drawable.good1));
        sprites.add(createSprite(R.drawable.good2));
        sprites.add(createSprite(R.drawable.good3));
        sprites.add(createSprite(R.drawable.good4));
        sprites.add(createSprite(R.drawable.good5));
    }

    private Sprite createSprite (int resource) {
        bmp = BitmapFactory.decodeResource(getResources(), resource);
        return new Sprite(this, bmp);
    }
    public MoverFiguras(Context context) {
        super(context);
        getHolder().addCallback(this);

        paint = new Paint();
        linePaint = new Paint();
        setBackgroundColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Sangre sang;
        for (int i = sang.size(); i >= 0; i--) {
            sang.get(i).onDraw(canvas);
        }
        canvas.drawColor(Color.BLACK);
        for (Sprite sprite : sprites) {
            sprite.onDraw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        synchronized (getHolder()) {
            for (int i = sprites.size() - 1; i >= 0; i--) {
                Sprite sprite = sprites.get(i);
                if (sprite.isCollition(event.getX(), event.getY())) {
                    sprites.remove(sprite);
                    break;
                }
            }
        }
        return true;
    }



    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        hiloJuego = new HiloJuego(getHolder(), this);
        hiloJuego.setRunning(true);
        hiloJuego.start();
        bmpBlood = BitmapFactory.decodeResource(getResources(), R.drawable.blood1);
        createSprites();
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