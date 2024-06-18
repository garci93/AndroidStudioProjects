package com.example.examenjunio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    String dificultad;
    String jugadorActual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutRes());
        OnBackPressedCallback atras = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finishAffinity();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, atras);
    }

    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "btnEntrar":
                Intent intent = new Intent(this, MenuOpciones.class);
                intent.putExtra("jugadorActual", jugadorActual);
                break;
        }
    }

    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("dificultad",0);
        String dificultadJuego = sharedPreferences.getString("dificultadClave", "baja");
        switch (dificultadJuego) {
            case "alta":
                dificultad="alta";
                break;
            case "media":
                dificultad="media";
                break;
            default:
                dificultad="baja";
                break;
        }
    }
}