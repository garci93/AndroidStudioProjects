package com.example.testjuego;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class SingleTouchTest extends Activity implements View.OnTouchListener {
    TextView textView;
    StringBuilder builder = new StringBuilder();

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        textView = new TextView(this);

        textView.setText("Touch and drag (one finger only)");
        setContentView(textView);

        textView.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        builder.setLength(0);
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (rectan.esDentro()){
                    dentro=true;
                    iniX=event.getX();
                    iniY=event.getY();
                }
                builder.append("down, ");
                break;
            case MotionEvent.ACTION_MOVE:
                if (dentro)
                { rectan.setX(rectan.getX() + iniX - event.getX());
                    rectan.setY(rectan.getY() + iniY - event.getY());

                    iniX = event.getX();
                    iniY = event.getY();
                builder.append("move, ");
                break;
            case MotionEvent.ACTION_CANCEL:
                builder.append("cancel, ");
                break;
            case MotionEvent.ACTION_UP:
                dentro=false;
                builder.append("up, ");
                break;
        }
        builder.append(event.getX());
        builder.append(", ");
        builder.append(event.getY());
        String text = builder.toString();
        Log.d("TouchTest", text);
        textView.setText(text);
        return true;
    }
}
