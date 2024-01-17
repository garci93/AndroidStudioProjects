package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Objects;

public class PizzasPredet extends BaseActivity implements View.OnClickListener {

    ArrayList<String> ingredientes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_pizzas_predet;
    }

    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "btnCarbonara":
                ingredientes.add("Salsa Carbonara");
                ingredientes.add("Doble Bacon");
                ingredientes.add("Champiños");
                ingredientes.add("5 Quesos");
                Intent confirm = new Intent(this, Confirmacion.class);
                confirm.putExtra("size", "Mediana");
                confirm.putStringArrayListExtra("ingredients", ingredientes);
                startActivity(confirm);
                break;
            case "btnCarnivora":
                ingredientes.add("Pollo");
                ingredientes.add("Bacon");
                ingredientes.add("Pepperoni");
                Intent confirm1 = new Intent(this, Confirmacion.class);
                confirm1.putExtra("size", "Mediana");
                confirm1.putStringArrayListExtra("ingredients", ingredientes);
                startActivity(confirm1);
                break;
            case "btnHawai":
                ingredientes.add("Jamón York");
                ingredientes.add("Piña");
                Intent confirm2 = new Intent(this, Confirmacion.class);
                confirm2.putExtra("size", "Mediana");
                confirm2.putStringArrayListExtra("ingredients", ingredientes);
                startActivity(confirm2);
                break;
            case "btnMarga":
                ingredientes.add("Tomate");
                ingredientes.add("Queso");
                Intent confirm3 = new Intent(this, Confirmacion.class);
                confirm3.putExtra("size", "Mediana");
                confirm3.putStringArrayListExtra("ingredients", ingredientes);
                startActivity(confirm3);
                break;
        }
    }
}