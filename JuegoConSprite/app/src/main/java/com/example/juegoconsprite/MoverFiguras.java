package com.example.juegoconsprite;
import static android.app.PendingIntent.getActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MoverFiguras extends SurfaceView implements SurfaceHolder.Callback {
    private final MediaPlayer mediaPlayerCrash;
    private final MediaPlayer mediaPlayerBonus;
    private MediaPlayer mediaPlayer;
    private Bitmap bmp;
    private HiloJuego hiloJuego;
    private Paint paint;
    private Paint linePaint;
    private Bitmap bmpPoints;
    Sprite jugador;
    List<Sprite> sprites = new ArrayList<Sprite>();
    private List<Points> points = new ArrayList<Points>();

    private int score = 0;
    private long lastClick;
    private Context context;

    private void createSprites() {
        jugador = createSprite(R.drawable.ship,"PLAYER");
        sprites.add(jugador);
    }

    private Sprite createSprite (int resource) {
        bmp = BitmapFactory.decodeResource(getResources(), resource);
        return new Sprite(this, bmp);
    }

    public void update() {
        if (hiloJuego.frames() % 10 == 0) {
            Random rnd = new Random();
            switch (rnd.nextInt(5)) {
                case 0:
                    sprites.add(createSprite(R.drawable.item,"ITEM"));
                    break;
                case 1:
                    sprites.add(createSprite(R.drawable.asteroides1,"OBSTACLE"));
                    break;
                case 2:
                    sprites.add(createSprite(R.drawable.asteroides2,"OBSTACLE"));
                    break;
                case 3:
                    sprites.add(createSprite(R.drawable.asteroides3,"OBSTACLE"));
                    break;
                case 4:
                    sprites.add(createSprite(R.drawable.asteroides4,"OBSTACLE"));
                    break;
            }
            if (rnd.nextInt(5) == 3) {
                sprites.add(createSprite(R.drawable.item,"ITEM"));
            } else {
                sprites.add(createSprite(R.drawable.asteroides1,"OBSTACLE"));
            }
        }
    }

    private Sprite createSprite (int resource, String tipo) {
        bmp = BitmapFactory.decodeResource(getResources(), resource);
        return new Sprite(this, bmp, tipo);
    }
    public MoverFiguras(Context context) {
        super(context);
        getHolder().addCallback(this);

        mediaPlayer = MediaPlayer.create(context, R.raw.background_music);
        mediaPlayer.setLooping(true);
        mediaPlayerCrash = MediaPlayer.create(context, R.raw.crash);
        mediaPlayerBonus = MediaPlayer.create(context, R.raw.bonus);

        paint = new Paint();
        linePaint = new Paint();
        setBackgroundResource(R.drawable.spacebg);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = points.size()-1; i >= 0; i--) {
            points.get(i).onDraw(canvas);
        }

        for (Sprite sprite : sprites) {
            sprite.onDraw(canvas);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(System.currentTimeMillis() - lastClick > 100){
            lastClick = System.currentTimeMillis();
            int x = (int)event.getX();
            int y = (int)event.getY();
            synchronized (getHolder()) {
                for (int i = sprites.size() - 1; i >= 0; i--) {
                    Sprite sprite = sprites.get(i);
                    if (sprite.getTipo() == "PLAYER") {
                        jugador = sprite;
                    }
                    if (sprite.isOOB()) {
                        sprites.remove(sprite);
                        break;
                    }
                }
            }
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    for (Sprite sprite : sprites) {
                        if (sprite.getTipo() == "PLAYER" && sprite.isCollision(event.getX(), event.getY())) {
                            sprite.setX(x);
                            sprite.setY(y);
                        }
                    }

                    break;
                case MotionEvent.ACTION_MOVE:
                    jugador.mover(x, y);
                    for (Sprite sprite : sprites) {
                        if (sprite.isCollision(jugador.getX(), jugador.getY())) {
                            if (sprite.getTipo() == "OBSTACLE") {
                                sprites.remove(jugador);
                                gameOver();
                                break;
                            } else if (sprite.getTipo() == "ITEM") {
                                sprites.remove(sprite);
                                jugador.addBonus();
                                points.add(new Points(points, this, sprite.getX(), sprite.getY(), bmpPoints));
                                break;
                            }
                        }
                    }
                    break;
                case MotionEvent.ACTION_CANCEL:
                    break;
                case MotionEvent.ACTION_UP:
                    /*for (Sprite sprite : sprites) {
                        if (figura.getInitialX() != null && figura.getInitialY() != null) {
                            int index = figurasRellenas.indexOf(figura);
                            Figura figuraVacia = figurasVacias.get(index);

                            if (figuraVacia.isNear(figura.getX(), figura.getY())) {
                                figura.setX(figuraVacia.getX());
                                figura.setY(figuraVacia.getY());
                                incrementarPuntuacion();
                            }
                        }
                    }*/
                    break;
            }
        }
        return true;
    }

    private void reiniciarActividad() {
        if (getContext() instanceof Activity) {
            Activity actividad = (Activity) getContext();
            actividad.finish();
            actividad.startActivity(new Intent(actividad, actividad.getClass()));
        }
    }

    private void gameOver() {
        mediaPlayerCrash.start();
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle("Game Over");
        builder.setMessage("¡Has perdido!" +
                "\nPuntos obtenidos: "+jugador.getScore()+
        "\nPuntuación máxima: "+jugador.getMaxScore()+
                "\n¿Volver a jugar?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                hiloJuego.setRunning(false);
                reiniciarActividad();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                hiloJuego.setRunning(false);
                ((Activity) getContext()).finish();
            }
        });
        builder.setCancelable(false); // Evita que se cierre haciendo clic fuera del diálogo

        AlertDialog gameOverDialog = builder.create();
        gameOverDialog.show();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        hiloJuego = new HiloJuego(getHolder(), this);
        hiloJuego.setRunning(true);
        hiloJuego.start();
        bmpPoints = BitmapFactory.decodeResource(getResources(), R.drawable.points100);
        ImageView backgroundImageView = findViewById(R.id.imageView);
        createSprites();
        mediaPlayer.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayerCrash.release();
        mediaPlayerBonus.release();
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

    public void playBonus() {
        mediaPlayerBonus.start();
    }
}