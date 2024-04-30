package com.example.diccioingles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Consultar extends BaseActivity implements View.OnClickListener {

    RecyclerView listaPalabras;
    List<String> resultado;
    PalabrasAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        resultado = db.getPalabras(dbRead);
        listaPalabras = findViewById(R.id.listaPalabras);
        listaPalabras.setLayoutManager(new LinearLayoutManager(this));
        setListPalabras();
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

    public void setListPalabras() {
        PalabrasAdapter adapter = new PalabrasAdapter(resultado);
        listaPalabras.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

    }
}