package com.example.pizzeria;

import android.os.Bundle;
import android.view.View;

public class PizzaFavorita extends MainActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_pizza_favorita;
    }

    @Override
    public void onClick(View v) {

    }
}