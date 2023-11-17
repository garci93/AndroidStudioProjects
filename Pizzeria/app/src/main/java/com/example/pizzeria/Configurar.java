
package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Configurar extends AppCompatActivity {

    private RadioGroup radioColorGroup;
    private RadioButton radioColor1;
    private RadioButton radioColor2;
    private RadioButton radioColor3;
    private Button btnAtrasConfigurar;
    private int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar);
        radioColor1 = findViewById(R.id.radioColor1);
        radioColor2 = findViewById(R.id.radioColor2);
        radioColor3 = findViewById(R.id.radioColor3);
        radioColorGroup = findViewById(R.id.radioColorGroup);
        btnAtrasConfigurar = findViewById(R.id.btnAtrasConfigurar);

        radioColorGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);

                if (radioButton.getId() == radioColor1.getId())
                    color = getResources().getColor(R.color.lightRed);
                else if (radioButton.getId() == radioColor2.getId())
                    color = getResources().getColor(R.color.lightGreen);
                else if (radioButton.getId() == radioColor3.getId())
                    color = getResources().getColor(R.color.lightBlue);
                getWindow().getDecorView().setBackgroundColor(color);
                SharedPreferences sharedPreferences = getSharedPreferences("color_fondo",0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("colorFondoClave",color);
                editor.apply();
            }
        });

        btnAtrasConfigurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("color_fondo",0);
        int colorSeleccionado = sharedPreferences.getInt("colorFondoClave", R.color.lightRed);
        getWindow().getDecorView().setBackgroundColor(colorSeleccionado);
    }
}