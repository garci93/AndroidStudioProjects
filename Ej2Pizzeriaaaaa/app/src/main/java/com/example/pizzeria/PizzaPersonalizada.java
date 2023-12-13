package com.example.pizzeria;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class PizzaPersonalizada extends MainActivity implements View.OnClickListener {
    String tamañoPizza;
    ArrayList<String> ingredientes = new ArrayList<String>();
    RadioGroup tamaño;
    LinearLayout list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        tamaño = findViewById(R.id.radioGroup);

        list = findViewById(R.id.lista);

        for (int i = 0; i < list.getChildCount(); i++) {
            View childView = list.getChildAt(i);
            if (childView instanceof CheckBox) {
                final CheckBox checkBox = (CheckBox) childView;
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            System.out.println("Añadido: " + buttonView.getText());
                            ingredientes.add((String) buttonView.getText());
                        } else {
                            System.out.println("Quitado: " + buttonView.getText());
                            ingredientes.remove((String) buttonView.getText());
                        }
                    }
                });
            }
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_pizza_personalizada;
    }

    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "pequeña":
                tamañoPizza = "Pequeña";
                break;
            case "mediana":
                tamañoPizza = "Mediana";
                break;
            case "familiar":
                tamañoPizza = "Grande";
                break;
            case "Siguiente":
                Intent confirmar = new Intent(this, Confirmacion.class);
                confirmar.putExtra("size", tamañoPizza);
                if(ingredientes.size() <= 3) {
                    confirmar.putStringArrayListExtra("ingredients", ingredientes);
                    startActivity(confirmar);
                }else{
                    new AlertDialog.Builder(this)
                        .setMessage("Maximos Ingredientes: 3")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();
                }
                break;
            case "botonAtras":
                Intent botonAtras = new Intent(this, Pedidos.class);
                startActivity(botonAtras);
                break;
        }
    }
}