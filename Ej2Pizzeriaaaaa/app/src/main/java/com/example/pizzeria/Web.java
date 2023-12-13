package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Web extends MainActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_web;
    }
    @Override
    public void onClick(View v) {
        Intent volver = new Intent(this, Pagina_Principal.class);
        startActivity(volver);
    }
}