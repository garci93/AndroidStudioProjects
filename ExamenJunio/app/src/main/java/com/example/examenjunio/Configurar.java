package com.example.examenjunio;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Configurar extends BaseActivity implements View.OnClickListener {

    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;

    private String dificultad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        radioButton1 = findViewById(R.id.btnBaja);
        radioButton2 = findViewById(R.id.btnMedia);
        radioButton3 = findViewById(R.id.btnAlta);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);

                if (radioButton.getId() == radioButton1.getId())
                    dificultad = "baja";
                else if (radioButton.getId() == radioButton2.getId())
                    dificultad = "media";
                else if (radioButton.getId() == radioButton3.getId())
                    dificultad = "alta";
                SharedPreferences sharedPreferences = getSharedPreferences("dificultad",0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("dificultadClave",dificultad);
                editor.apply();
            }
        });
    }



    @Override
    protected int getLayoutRes() {
        return R.layout.activity_configurar;
    }

    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "btnVolverConf":
                finish();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("dificultad",0);
        String dificultadJuego = sharedPreferences.getString("dificultadClave", "baja");
        switch (dificultadJuego) {
            case "baja":
                radioButton1.toggle();
                break;
            case "media":
                radioButton2.toggle();
                break;
            case "alta":
                radioButton3.toggle();
                break;
        }
    }
}