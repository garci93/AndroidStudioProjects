package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Confirmacion extends MainActivity implements View.OnClickListener {

    ArrayList<String> ingredientes = new ArrayList<>();
    String size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        Intent datos = getIntent();
        size = datos.getStringExtra("size");
        ingredientes = datos.getStringArrayListExtra("ingredients");

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        layoutParams.gravity = Gravity.CENTER;


        LinearLayout listIngredients = findViewById(R.id.listIngredients);
        listIngredients.setGravity(Gravity.CENTER);
        for(String ingredients : ingredientes) {
            TextView textView = new TextView(this);
            textView.setText(ingredients);
            textView.setLayoutParams(layoutParams);
            listIngredients.addView(textView);
        }

        TextView sizeText = findViewById(R.id.size);
        TextView priceText = findViewById(R.id.price);
        sizeText.setText(size);
        String price = String.valueOf(getPrice(size, ingredientes.size()));
        priceText.setText(price + "€");
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_confirmacion;
    }

    @Override
    public void onClick(View v) {
        Intent confirmar = new Intent(this, Pagina_Principal.class);
        startActivity(confirmar);
    }

    public int getPrice(String size, int nIngredients) {
        int price = 0;
        switch (size) {
            case "Pequeña":
                price = 8;
                break;
            case "Mediana":
                price = 12;
                break;
            case "Grande":
                price = 16;
                break;
        }
        price += nIngredients > 2 ? 3 : 0;
        return price;
    }
}