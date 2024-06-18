package com.example.examenjunio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuOpciones extends BaseActivity implements View.OnClickListener {
    String jugadorActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_opciones);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        jugadorActual = getIntent().getStringExtra("jugadorActual");
    }

    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "btnJugar":
                Intent jugar = new Intent(this, Jugar.class);
                jugar.putExtra("preguntaActual", 1);
                startActivity(jugar);
                break;
            case "btnMejores":
                Intent mejores = new Intent(this, ConsultarMejores.class);
                startActivity(mejores);
                break;
            case "btnMisPuntos":
                Intent mias = new Intent(this, ConsultarMias.class);
                mias.putExtra("jugadorActual", jugadorActual);
                startActivity(mias);
                break;
            case "btnConfigurar":
                Intent conf = new Intent(this, Configurar.class);
                startActivity(conf);
                break;
        }
    }
}