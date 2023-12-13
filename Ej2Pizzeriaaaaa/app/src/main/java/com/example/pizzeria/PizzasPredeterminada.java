package com.example.pizzeria;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class PizzasPredeterminada extends MainActivity implements View.OnClickListener {

    ArrayList<String> ingredientes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_pizzas_predeterminada;
    }

    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "btnJalapeño":
                ingredientes.add("Tomate");
                ingredientes.add("Chedar");
                ingredientes.add("Jalapeño");
                ingredientes.add("Carne Picada");
                Intent confirm = new Intent(this, Confirmacion.class);
                confirm.putExtra("size", "Mediana");
                confirm.putStringArrayListExtra("ingredients", ingredientes);
                startActivity(confirm);
                break;
            case "btnJamonQueso":
                ingredientes.add("Tomate");
                ingredientes.add("Queso");
                ingredientes.add("Jamon York");
                Intent confirm1 = new Intent(this, Confirmacion.class);
                confirm1.putExtra("size", "Mediana");
                confirm1.putStringArrayListExtra("ingredients", ingredientes);
                startActivity(confirm1);
                break;
            case "btnCuatroQueso":
                ingredientes.add("Tomate");
                ingredientes.add("Chedar");
                ingredientes.add("Queso oveja");
                ingredientes.add("Queso Cabra");
                ingredientes.add("Queso Payoyo");
                Intent confirm2 = new Intent(this, Confirmacion.class);
                confirm2.putExtra("size", "Mediana");
                confirm2.putStringArrayListExtra("ingredients", ingredientes);
                startActivity(confirm2);
                break;

        }
    }

    private void savePizzaToDatabase(String nombrePizza, ArrayList<String> ingredientes) {
        ContentValues values = new ContentValues();
        values.put("nombre", nombrePizza);
        db.insert("pizzas", null, values);

        dbHelper.close();
    }
}