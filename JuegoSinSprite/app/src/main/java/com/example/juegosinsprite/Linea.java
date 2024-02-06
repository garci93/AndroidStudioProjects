package com.example.juegosinsprite;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import java.util.HashMap;
import java.util.Map;

public class Linea {
    private Map<Float, Float> puntos = new HashMap<>();
    private Path path = new Path();
    public Linea() {

    }

    public void guardarPuntoInicial(Float x, Float y) {
        path.moveTo(x, y);
        puntos.put(x, y);
    }

    public void guardarPunto(Float x, Float y) {
        path.lineTo(x, y);
    }

    public void onDraw(Canvas canvas, Paint paint) {
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        for (Map.Entry<Float, Float> entry : puntos.entrySet()) {
            canvas.drawCircle(entry.getKey(), entry.getValue(), 10, paint);
        }

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        canvas.drawPath(path, paint);
    }
}

