package com.example.appcitas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NuevaCita extends BaseActivity implements View.OnClickListener {
    EditText txtCita;
    EditText txtAutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        txtCita = findViewById(R.id.txtCita);
        txtAutor = findViewById(R.id.txtAutor);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_nueva_cita;
    }


    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "btnInsertarCita":
                db.insertarCita(dbWrite, txtCita.getText().toString(), txtAutor.getText().toString());
                finish();
        }
    }
}