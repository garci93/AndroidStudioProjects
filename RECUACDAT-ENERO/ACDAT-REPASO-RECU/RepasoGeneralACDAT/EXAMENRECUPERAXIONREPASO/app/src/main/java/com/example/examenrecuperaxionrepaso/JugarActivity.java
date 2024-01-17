package com.example.examenrecuperaxionrepaso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JugarActivity extends BaseActivity implements View.OnClickListener {
    ListView listView;
    List<String> listaPalabras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        listView = findViewById(R.id.listView);

        listaPalabras = db.getPalabrasN(dbRead, dificultad);

        setListView();
        //mostrarPalabras();
    }

    protected int getLayoutRes() {
        return R.layout.activity_jugar;
    }

    public void setListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaPalabras) ;
        listView.setAdapter(adapter);
    }

    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "buttonSiguiente":
                Intent sig = new Intent(this, JugarActivity2.class);
                sig.putStringArrayListExtra("lista", (ArrayList<String>) listaPalabras);
                startActivity(sig);
                break;
            case "buttonAtras":
                Intent atras = new Intent(this, MainActivity.class);
                startActivity(atras);
                break;
        }
    }

    /*
    private void mostrarPalabras() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    for (String palabra: listaPalabras) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(JugarActivity.this, palabra, Toast.LENGTH_SHORT).show();
                            }
                        });
                        Thread.sleep(3000);
                    }
                }catch (Exception e) {
                    throw new RuntimeException(e);
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(JugarActivity.this,JugarActivity2.class));
                    }
                });
            }
        });
    }

     */
}