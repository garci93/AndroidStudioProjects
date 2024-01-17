package com.example.examenandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class JugarDos extends BaseActivity implements View.OnClickListener {
    EditText entTexto;
    ListView listView;
    List<String> listaPalabras;
    List<String> listaAciertos;
    int puntuacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        puntuacion=0;
        entTexto = findViewById(R.id.entTexto);
        listView = findViewById(R.id.listView);

        listaPalabras = db.getPalabrasN(dbRead, dificultad);

        setListView();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_jugar_dos;
    }

    @Override
    public void onClick(View v) {
        String respuesta = entTexto.getText().toString();

        if (respuesta!=""){
            comprobarRespuesta(respuesta);
        }
    }

    private void comprobarRespuesta(String respuesta) {
        if(listaPalabras.contains(respuesta) && !listaAciertos.contains(respuesta)){
            listaAciertos.add(respuesta);
            puntuacion++;
            setListView();
        }
    }

    public void setListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaAciertos) ;
        listView.setAdapter(adapter);
    }

}