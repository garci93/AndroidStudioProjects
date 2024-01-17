package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

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

public class PizzaPersonalizada extends BaseActivity implements View.OnClickListener {
    String pizzaSize;
    ArrayList<String> ingredientes = new ArrayList<String>();
    RadioGroup size;
    LinearLayout list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        size = findViewById(R.id.radioGroup);

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
            case "peque":
                pizzaSize = "Pequeña";
                break;
            case "mediana":
                pizzaSize = "Mediana";
                break;
            case "familiar":
                pizzaSize = "Grande";
                break;
            case "confirmarPiza":
                Intent confirmar = new Intent(this, Confirmacion.class);
                confirmar.putExtra("size", pizzaSize);
                if(ingredientes.size() <= 5) {
                    confirmar.putStringArrayListExtra("ingredients", ingredientes);
                    startActivity(confirmar);
                }else{
                    new AlertDialog.Builder(this)
                        .setMessage("Maximos Ingredientes: 5")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();
                }
                break;
        }
    }
}