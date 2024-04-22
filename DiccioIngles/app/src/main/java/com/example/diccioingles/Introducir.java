package com.example.diccioingles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
    protected int getLayoutRes() {
        return R.layout.activity_introducir;
    }

    @Override
    public void onClick(View v) {
        String resourceName = getResources().getResourceEntryName(v.getId());
        switch (resourceName) {
            case "btnIntroducirPalabra":
                txtEspanol = findViewById(R.id.txtEspanol);
                txtIngles = findViewById(R.id.txtIngles);
                String tipo = txtEspanol.getText().toString().contains(" ") ? "expresion" : "palabra";
                db.insertarPalabra(dbWrite, txtEspanol.getText().toString(), txtIngles.getText().toString(), tipo, "palabra.mp3");
                break;
                //en consultar, al pinchar, se muestra un dialogo con la palabra, la traduccion, el numero de aciertos y la fecha del ultimo test
                //y tambien la opcion de modificar la palabra
        }
        finish();
    }
}