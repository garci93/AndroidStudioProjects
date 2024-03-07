package com.example.appingles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "btnIntroducirMenu":
                Intent introducir = new Intent(this, Introducir.class);
                startActivity(introducir);
                break;
            case "btnModificarMenu":
                Intent modificar = new Intent(this, Modificar.class);
                startActivity(modificar);
                break;
            case "btnListarMenu":
                Intent listar = new Intent(this, Listar.class);
                startActivity(listar);
                break;
            case "btnTestMenu":
                Intent test = new Intent(this, Test.class);
                startActivity(test);
                break;
        }
    }
}