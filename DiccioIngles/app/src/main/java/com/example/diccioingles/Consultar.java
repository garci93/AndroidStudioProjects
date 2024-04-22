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
        adapter = new PalabrasAdapter(resultado); //CONSTRUCTOR DE PalabrasAdapter?
        listaPalabras.setAdapter(adapter);
    }
    @Override
    protected int getLayoutRes()  {
        return R.layout.activity_consultar;
    }

    public void onItemClick(View v) {
        String palabra = listPalabras.getItemAtPosition(v.getId()).toString();
        mostrarDialogoPalabra(palabra);
    }

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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, resultado) ;
        listPalabras.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

    }
}