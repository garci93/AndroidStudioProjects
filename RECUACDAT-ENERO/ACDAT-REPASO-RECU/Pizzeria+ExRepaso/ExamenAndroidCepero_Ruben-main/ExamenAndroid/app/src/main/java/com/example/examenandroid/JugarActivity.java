package com.example.examenandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.examenandroid.DAOPalabras.DAOPalabras;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JugarActivity extends AppCompatActivity  implements View.OnClickListener{


    List<String> palabras;

    List<String> palabrasAparecen;

    List<String> palabrasAcertadas;

    List<TextView> textViewsPalabras;
    TextView palabra1, palabra2, palabra3,palabra4, palabra5, palabra6,palabra7, palabra8, palabra9,palabra10, palabra11, palabra12, palabra13, palabra14, palabra15;

    ListView listViewPalabras;
    ConstraintLayout layoutPalabras;

    ProgressBar progressBar;

    int numeroPalabrasAparecen;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);

        numeroPalabrasAparecen = NumeroPalabras.getNumeroPalabra();
        DAOPalabras dao = new DAOPalabras(this);
        layoutPalabras = findViewById(R.id.layoutPalabras);
        palabras = dao.getListaDePalabras();
        textViewsPalabras = new ArrayList<TextView>();
        palabrasAcertadas = new ArrayList<String>();
        palabrasAparecen = new ArrayList<String>();
        inicializacionTextviews();
        asignarPalabras();




    }


    public void inicializacionTextviews(){
            textViewsPalabras.add(palabra1 = findViewById(R.id.txtPalabra1));
            textViewsPalabras.add(palabra2 = findViewById(R.id.txtPalabra2));
            textViewsPalabras.add(palabra3 = findViewById(R.id.txtPalabra3));
            textViewsPalabras.add(palabra4 = findViewById(R.id.txtPalabra4));
            textViewsPalabras.add(palabra5 = findViewById(R.id.txtPalabra5));
            textViewsPalabras.add(palabra6 = findViewById(R.id.txtPalabra6));
            textViewsPalabras.add(palabra7 = findViewById(R.id.txtPalabra7));
            textViewsPalabras.add(palabra8 = findViewById(R.id.txtPalabra8));
            textViewsPalabras.add(palabra9 = findViewById(R.id.txtPalabra9));
            textViewsPalabras.add(palabra10 = findViewById(R.id.txtPalabra10));
            textViewsPalabras.add(palabra11 = findViewById(R.id.txtPalabra11));
            textViewsPalabras.add(palabra12 = findViewById(R.id.txtPalabra12));
            textViewsPalabras.add(palabra13 = findViewById(R.id.txtPalabra13));
            textViewsPalabras.add(palabra14 = findViewById(R.id.txtPalabra14));
            textViewsPalabras.add(palabra15 = findViewById(R.id.txtPalabra15));


    }


    public void adivinarPalabras() throws InterruptedException {
        Thread.sleep(20000);
        for (TextView txtview: textViewsPalabras) {
            txtview.setText("");
        }


    }
    public void asignarPalabras(){

        Random random = new Random();

        int contador = 1;
        for (TextView txtview: textViewsPalabras) {
            int numeroRandom = random.nextInt(20);
            String palabraRandom = palabras.get(numeroRandom);
            palabrasAparecen.add(palabraRandom);
            txtview.setText(palabraRandom);
            contador ++;

            if(contador == numeroPalabrasAparecen){
                break;
            }
        }


    }


    public void comprobarResultados(){

        //if(palabrasAcertadas.size() == palabrasAparecen.size()){

        if(palabrasAcertadas.size() == 1){
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Has ganado");
            alertDialog.setMessage("Porcentaje de aciertos: " + (progressBar.getProgress() * 100) / progressBar.getMax() + "%");

            alertDialog.show();

            SharedPreferences sharedPreferences = getSharedPreferences("preferencias", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("maximaPuntuacion", progressBar.getProgress()+"");
        }

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonAdivinarPalabras){

            setContentView(R.layout.activity_adivinar);
            configurarListView();
           Button boton = findViewById(R.id.buttonAdivinar);
            boton.setOnClickListener(this);
            Button botonSalir = findViewById(R.id.buttonSalirAdivinar);
            botonSalir.setOnClickListener(this);
            progressBar = findViewById(R.id.progressBar);
            progressBar.setMax(numeroPalabrasAparecen);
        }else if(v.getId() == R.id.buttonAdivinar){
            TextView palabraintroducida = findViewById(R.id.editTextText);
            for (String palabra: palabrasAparecen)  {
                if(palabraintroducida.getText().toString().equals(palabra)){
                    progressBar.setProgress(progressBar.getProgress() + 1);
                    palabrasAcertadas.add(palabra);
                    configurarListView();
                    break;
                }
            }
            comprobarResultados();

        }else if(v.getId() == R.id.buttonSalirJugar || v.getId() == R.id.buttonSalirAdivinar){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


    public void configurarListView(){
        listViewPalabras = findViewById(R.id.listViewPalabras);
        listViewPalabras.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, palabrasAcertadas));

    }
}