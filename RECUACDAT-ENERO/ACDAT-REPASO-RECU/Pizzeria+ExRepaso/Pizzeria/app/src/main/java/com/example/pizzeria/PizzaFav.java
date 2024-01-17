package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class PizzaFav extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_pizza_fav;
    }

    @Override
    public void onClick(View v) {
        Intent confirmacion = new Intent(this, Confirmacion.class);
        confirmacion.putExtra("size", "Mediana");
        ArrayList<String> ingredientes = new ArrayList<String>();
        ingredientes.add("Pollo");
        ingredientes.add("Bacon");
        ingredientes.add("Jamón");
        confirmacion.putExtra("ingredients", ingredientes);
        startActivity(confirmacion);
    }
}