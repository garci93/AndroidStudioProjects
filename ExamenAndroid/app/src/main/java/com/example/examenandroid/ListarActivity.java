package com.example.examenandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class ListarActivity extends BaseActivity {
    private Button btnAtrasDesdeListar;
    private ListView listViewListar;
    private List listaPalabras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listaPalabras= db.getPalabras(dbRead);
        setContentView(getLayoutRes());
        btnAtrasDesdeListar = findViewById(R.id.btnAtrasDesdeListar);
        listViewListar = findViewById(R.id.listViewListar);
        setListView();
        btnAtrasDesdeListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    protected int getLayoutRes() {
        return R.layout.activity_listar;
    }

    public void setListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaPalabras) ;
        listViewListar.setAdapter(adapter);
    }
}