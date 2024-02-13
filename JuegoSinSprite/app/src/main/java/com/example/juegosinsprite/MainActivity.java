package com.example.juegosinsprite;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private SurfaceView surfaceView;
    private Button btnGenerar;
    private MoverFiguras moverFiguras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        surfaceView = findViewById(R.id.surfaceView);
        btnGenerar = findViewById(R.id.btnGenerar);

        surfaceView.setBackgroundColor(Color.WHITE);
        surfaceView = new MoverFiguras(this);

        btnGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moverFiguras.generarFiguras();
            }
        });


    }

    @Override
    public void onClick(View v) {
        /*switch (getResources().getResourceEntryName(v.getId())) {
            case "btnGenerar":
                moverFiguras.generarFiguras();
                break;
        }*/
    }
}
