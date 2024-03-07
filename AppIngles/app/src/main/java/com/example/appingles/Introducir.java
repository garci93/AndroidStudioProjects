package com.example.appingles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Introducir extends BaseActivity implements View.OnClickListener {

    EditText txtEspanol;
    EditText txtIngles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        txtEspanol = findViewById(R.id.txtEspanol);
        txtIngles = findViewById(R.id.txtIngles);
        setContentView(R.layout.activity_introducir);
    }

    @Override
    protected int getLayoutRes() {return R.layout.activity_introducir;}

    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "btnIntroducirPalabra":
                db.insertarPalabra(dbWrite, txtEspanol.getText().toString(), txtIngles.getText().toString());
                finish();
                break;
        }
    }
}