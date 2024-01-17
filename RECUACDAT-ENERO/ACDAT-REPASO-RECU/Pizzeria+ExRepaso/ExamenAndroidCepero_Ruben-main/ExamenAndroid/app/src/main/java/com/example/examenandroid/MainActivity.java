package com.example.examenandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener   {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonJugar){
            Intent i = new Intent(this, JugarActivity.class);
            startActivity(i);
            this.finish();
        }else if(v.getId() == R.id.buttonPuntuaciones){
            Intent i = new Intent(this, PuntuacionActivity.class);
            startActivity(i);
            this.finish();

        }else if(v.getId() == R.id.buttonConfigurar){
            Intent intent = new Intent(this, ConfigurarActivity.class);
            startActivity(intent);
            this.finish();

        }else if(v.getId() == R.id.buttonSalir){
            this.finish();
        }
    }
}