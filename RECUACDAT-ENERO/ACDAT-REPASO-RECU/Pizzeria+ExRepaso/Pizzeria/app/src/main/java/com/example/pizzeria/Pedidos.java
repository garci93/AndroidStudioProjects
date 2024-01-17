package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Pedidos extends BaseActivity implements View.OnClickListener {

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
            case "pizzaFav":
                if (getFavPreference()) {
                    Intent fav = new Intent(this, PizzaFav.class);
                    startActivity(fav);
                }
                break;
            case "personalizada":
                Intent perso = new Intent(this, PizzaPersonalizada.class);
                startActivity(perso);
                break;
            case "pizzaPred":
                Intent prede = new Intent(this, PizzasPredet.class);
                startActivity(prede);
                break;
        }
    }
}