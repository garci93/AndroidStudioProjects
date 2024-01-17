package com.example.examenacdat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Jugar extends BaseActivity implements View.OnClickListener {

    ListView listView;
    List<String> listaPalabras;
    TextView tiempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        listView = findViewById(R.id.listViewP);
        tiempo = findViewById(R.id.txtTiempo);

        for (int i = 1; i <= 5000; i++) {
            tiempo.setText("Tiempo:" + i/1000);
        }

        listaPalabras = db.getPalabrasN(dbRead, dificultad);

        setListView();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_jugar;
    }

    public void setListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaPalabras) ;
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "btSig":
                Intent sig = new Intent(this, Responder.class);
                sig.putStringArrayListExtra("lista", (ArrayList<String>) listaPalabras);
                startActivity(sig);
                break;
            case "btSalir":
                finish();
                break;
        }
    }
}

    /*ContentValues values = new ContentValues();
                values.put("nombre", user);
                        values.put("pass", pass);

                        db.insertarCliente(dbWrite, user, pass);
//dbWrite.insert(DB.TABLE_CLIENTES, null, values);*/