package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Switch;

public class Configuracion extends BaseActivity implements View.OnClickListener {
    private Switch fondo;
    private Switch favS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        fondo = findViewById(R.id.fondo);
        favS = findViewById(R.id.fav);

        boolean esColor2 = isBackgroundColor2();
        fondo.setChecked(esColor2);
        if (getFavPreference()) {
            favS.setChecked(true);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_configuracion;
    }

    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "fondo":
                boolean esColor2 = fondo.isChecked();
                saveBackgroundColorPreference(esColor2);
                changeBackgroundColor(esColor2 ? color2 : color1);
                break;
            case"volverConf":
                Intent volver = new Intent(this, Principal.class);
                startActivity(volver);
                break;
            case "fav":
                boolean fav = favS.isChecked();
                saveFavPreference(fav);
                break;
        }
    }
}