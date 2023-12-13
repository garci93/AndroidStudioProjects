package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Pedidos extends MainActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_pedidos;
    }

    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "pizzaFavorita":
                if (getFavPreference()) {
                    Intent favorita = new Intent(this, PizzaFavorita.class);
                    startActivity(favorita);
                }
                break;
            case "pizza_personalizada":
                Intent pizzapersonalizada = new Intent(this, PizzaPersonalizada.class);
                startActivity(pizzapersonalizada);
                break;
            case "pizzaPredeterminada":
                Intent pizzapredeterminada = new Intent(this, PizzasPredeterminada.class);
                startActivity(pizzapredeterminada);
                break;
            case "botonVolver":
                Intent botonVolver = new Intent(this, Pagina_Principal.class);
                startActivity(botonVolver);
                break;
        }
    }
}