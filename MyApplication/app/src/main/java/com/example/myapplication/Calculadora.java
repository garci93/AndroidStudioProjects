package com.example.myapplication;

import static android.text.TextUtils.lastIndexOf;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Calculadora extends AppCompatActivity implements View.OnClickListener {

    private Button btnIgual,btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnComa,btnSum,btnRes,btnMul,btnDiv,btnC,btnSigno;
    private TextView txtExp;
    private String cadena;

    public Calculadora() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btnIgual = findViewById(R.id.btnIgual);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        btnC = findViewById(R.id.btnC);
        btnComa = findViewById(R.id.btnComa);
        btnSum = findViewById(R.id.btnSum);
        btnRes = findViewById(R.id.btnRes);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        btnSigno = findViewById(R.id.btnSigno);

        btnIgual.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnComa.setOnClickListener(this);
        btnSum.setOnClickListener(this);
        btnRes.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnSigno.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnIgual){
            txtExp.setText("a");
//            try {
//                ExpressionBuilder expressionBuilder = new ExpressionBuilder(cadena);
//                Expression exp = expressionBuilder.build();
//                double res = exp.evaluate();
//                txtExp.setText("a");
//                cadena = ""+res;
//            } catch (Exception e) {
//                txtExp.setText("ERR");
//                cadena="";
//            }
        } else if (view.getId() == R.id.btn0){
            cadena += "0";
        } else if (view.getId() == R.id.btn1){
            cadena += "1";
        } else if (view.getId() == R.id.btn2){
            cadena += "2";
        } else if (view.getId() == R.id.btn3){
            cadena += "3";
        } else if (view.getId() == R.id.btn4){
            cadena += "4";
        } else if (view.getId() == R.id.btn5){
            cadena += "5";
        } else if (view.getId() == R.id.btn6){
            cadena += "6";
        } else if (view.getId() == R.id.btn7){
            cadena += "7";
        } else if (view.getId() == R.id.btn8){
            cadena += "8";
        } else if (view.getId() == R.id.btn9){
            cadena += "9";
        } else if (view.getId() == R.id.btnSigno){
            if(cadena.startsWith("-"))
                cadena = cadena.substring(1);
            else
                cadena = "-"+cadena;
        } else if (view.getId() == R.id.btnSum){
            if(cadena.endsWith("+")) cadena += "+";
        } else if (view.getId() == R.id.btnRes){
            if(cadena.endsWith("-")) cadena += "-";
        } else if (view.getId() == R.id.btnMul){
            if(cadena.endsWith("+") || cadena.endsWith("-") || cadena.endsWith("/"))
                cadena = cadena.substring(cadena.length()-1)+"*";
            else
                cadena += "*";
        } else if (view.getId() == R.id.btnDiv){
            if(cadena.endsWith("+") || cadena.endsWith("-") || cadena.endsWith("*"))
                cadena = cadena.substring(0,cadena.length()-2)+"/";
            else
                cadena += "/";
        } else if (view.getId() == R.id.btnComa) {
            if (cadena.substring(cadena.length()-1).matches("[0-9]"))
                cadena += ".";
            else
                cadena += "0.";
        } else if (view.getId() == R.id.btnC) {
            cadena = "";
        }
    }
}
