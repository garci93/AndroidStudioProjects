package com.example.examenacdat;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Inicio extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_inicio;
    }

    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "btJugar":
                Intent jugar = new Intent(this, Jugar.class);
                startActivity(jugar);
                break;
            case "btPuntuacion":
                Intent punt = new Intent(this, Puntuacion.class);
                startActivity(punt);
                break;
            case "btConf":
                Intent conf = new Intent(this, Configurar.class);
                startActivity(conf);
                break;
            case "btSalir":
                finish();
                break;
        }
    }
}