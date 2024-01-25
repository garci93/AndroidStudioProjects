package com.example.requetepasoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class CRUD extends BaseActivity implements View.OnClickListener {
    ListView listView;
    List<String> listaPersonas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        listView = findViewById(R.id.listView);

        listaPersonas = db.getPersonas(dbRead);

        setListView();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_crud;
    }
    public void setListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaPersonas) ;
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "btnAnadir":
                Intent crud = new Intent(this, CRUD.class);
                startActivity(crud);
                break;
            case "btnModificar":
                Intent configurar = new Intent(this, Configurar.class);
                startActivity(configurar);
                break;
            case "btnVolver":
                finish();
                break;

        }
    }
}