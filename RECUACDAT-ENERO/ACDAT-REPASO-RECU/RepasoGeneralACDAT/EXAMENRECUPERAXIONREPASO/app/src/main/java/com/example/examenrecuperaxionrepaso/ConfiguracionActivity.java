package com.example.examenrecuperaxionrepaso;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class ConfiguracionActivity extends BaseActivity implements View.OnClickListener {
    RadioButton facil;
    RadioButton medio;
    RadioButton dificil;

    Switch fondo;

    ListView listView;
    List listaPalabras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        facil = findViewById(R.id.rfacil);
        medio = findViewById(R.id.rMedio);
        dificil = findViewById(R.id.rDificil);
        fondo = findViewById(R.id.switchCambiarColor);
        listView = findViewById(R.id.listView);
        listaPalabras = db.getPalabras(dbRead);
        setListView();

        listView.setOnItemClickListener((parent, view, position, id) -> {
            String selected = (String) parent.getItemAtPosition(position);
            Toast.makeText(ConfiguracionActivity.this, "Se ha seleccionado:" + selected,Toast.LENGTH_SHORT).show();
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("¿Quieres borrar la palabra?");
            alertDialog.setMessage("Selecciona una opcion");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Borrar", (dialog, which) -> {
                borrarPalabra(listView.getItemAtPosition(position).toString());
                listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaPalabras));
            });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar", (dialog, which) -> {
                dialog.cancel();

            });
            alertDialog.show();
        });

        boolean esColor2 = isColorFondo2();
        fondo.setChecked(esColor2);


    }
    protected int getLayoutRes() {
        return R.layout.activity_configuracion;
    }

    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "buttonGuardar":
                if (facil.isChecked()) {
                    saveDificult(3);
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Dificultad guardada");
                    builder.setPositiveButton("Aceptar", null);
                    builder.create().show();
                } else if (medio.isChecked()) {
                    saveDificult(5);
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Dificultad guardada");
                    builder.setPositiveButton("Aceptar", null);
                    builder.create().show();
                } else if (dificil.isChecked()) {
                    saveDificult(10);
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Dificultad guardada");
                    builder.setPositiveButton("Aceptar", null);
                    builder.create().show();

                }

                break;
            case "buttonVolver":
                Intent volver = new Intent(this, MainActivity.class);
                startActivity(volver);
                break;
            case "switchCambiarColor":
                boolean esColor2 = fondo.isChecked();
                saveBackgroundColorPreference(esColor2);
                changeBackgroundColor(esColor2 ? color2 : color1);
                break;

        }
    }
    public void setListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaPalabras) ;
        listView.setAdapter(adapter);
    }

    public  void borrarPalabra(String palabra)
    {
        this.listaPalabras.remove(palabra);
        this.db.borrarPalabra(dbWrite,palabra);
    }


}