package com.example.examenacdat;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Principal extends BaseActivity {

    ListView listView;
    Spinner spinner;
    List<String> listaClientes;
    TextView txtLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        listView = findViewById(R.id.listViewC);
        //listaClientes = db.getClientes(dbRead);
        /*setListView();
        setSpinner();*/
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_principal;
    }

    /*public void setListView() {
        ArrayAdapter<Usuario> adapter = new ArrayAdapter<Usuario>(this, android.R.layout.simple_list_item_1, listaClientes) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView txtLista = view.findViewById(android.R.id.text1);

                txtLista.setTextSize(20);
                txtLista.setText(listaClientes.get(position).getNombre() + "\n" + listaClientes.get(position).getPasswd());
                return view;
            }
        };
        listView.setAdapter(adapter);
    }

    public void setSpinner() {
        ArrayAdapter<Usuario> adapter = new ArrayAdapter<Usuario>(this, android.R.layout.simple_spinner_item, listaClientes) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                Usuario usuario = getItem(position);
                txtLista = view.findViewById(android.R.id.text1);

                txtLista.setTextSize(20);
                txtLista.setText(usuario.getNombre());
                return view;
            }
        };
        spinner.setAdapter(adapter);
    }*/


}