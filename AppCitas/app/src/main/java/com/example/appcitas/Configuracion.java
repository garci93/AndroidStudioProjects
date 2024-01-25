package com.example.appcitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Configuracion extends BaseActivity implements View.OnClickListener {
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;

    private int numCitas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);

                if (radioButton.getId() == radioButton1.getId())
                    numCitas = 1;
                else if (radioButton.getId() == radioButton2.getId())
                    numCitas = 2;
                else if (radioButton.getId() == radioButton3.getId())
                    numCitas = 3;
                SharedPreferences sharedPreferences = getSharedPreferences("num_citas",0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("numCitasClave",numCitas);
                editor.apply();
            }
        });
    }



    @Override
    protected int getLayoutRes() {
        return R.layout.activity_configuracion;
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
        SharedPreferences sharedPreferences = getSharedPreferences("num_citas",0);
        int numCitasMostradas = sharedPreferences.getInt("numCitasClave", 1);
        switch (numCitasMostradas) {
            case 1:
                radioButton1.toggle();
                break;
            case 2:
                radioButton2.toggle();
                break;
            case 3:
                radioButton3.toggle();
                break;
        }
    }
}