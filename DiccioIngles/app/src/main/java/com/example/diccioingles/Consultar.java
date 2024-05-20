package com.example.diccioingles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Consultar extends BaseActivity implements View.OnClickListener {

    ListView listaPalabras;
    List<String> resultado;
    PalabrasAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        resultado = db.getPalabras(dbRead);
        listaPalabras = findViewById(R.id.listaPalabras);
        listaPalabras.setClickable(true);
        listaPalabras.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                //Object o = listView.getItemAtPosition(position);
                // Realiza lo que deseas, al recibir clic en el elemento de tu listView determinado por su posicion.
                Log.i("Click", "click en el elemento " + position + " de mi ListView");

            }
        });
    }
    @Override
    protected int getLayoutRes()  {
        return R.layout.activity_consultar;
    }

//    public void onItemClick(View v) {
//        String palabra = listaPalabras.getItemAtPosition(v.getId()).toString();
//        mostrarDialogoPalabra(palabra);
//    }

    private void mostrarDialogoPalabra(String palabra) {
        String[] resultado = db.getUnaPalabra(dbRead, palabra);
        new AlertDialog.Builder(this)
                .setMessage("Palabra: " + resultado[0] + "\nTraducción: " + resultado[1] + "\nNúmero de aciertos: " + resultado[2] + "\nFecha de último test: " + resultado[3])
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    @Override
    public void onClick(View v) {

    }

    /*public void setListPalabras() {
        PalabrasAdapter adapter = new PalabrasAdapter(resultado);
        listaPalabras.setAdapter(adapter);
    }*/
}