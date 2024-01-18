package com.example.appcitas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    int numCitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        mostrarDialogoSalida();
    }

    private void mostrarDialogoSalida() {
        new AlertDialog.Builder(this)
                .setMessage("¿Deseas salir?")
                .setCancelable(false)
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    private void mostrarDialogoBorrar() {
        new AlertDialog.Builder(this)
                .setMessage("¿Deseas borrar las citas?")
                .setCancelable(false)
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.borrarCitas(dbWrite);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    private void mostrarDialogoCitaDia() {
        new AlertDialog.Builder(this)
                .setMessage("Cita del día:"+db.getCitasN(dbRead, numCitas))
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "btnBorrarTodo":
                mostrarDialogoBorrar();
                break;
            case "btnNueva":
                Intent crud = new Intent(this, NuevaCita.class);
                startActivity(crud);
                break;
            case "btnListar":
                Intent listar = new Intent(this, ListarCitas.class);
                startActivity(listar);
                break;
            case "btnCitaDia":
                mostrarDialogoCitaDia();
                break;
            case "btnConfigurar":
                Intent conf = new Intent(this, Configuracion.class);
                startActivity(conf);
                break;
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("num_citas",0);
        int numCitasMostradas = sharedPreferences.getInt("numCitasClave", 1);
        switch (numCitasMostradas) {
            case 3:
                numCitas=3;
                break;
            case 2:
                numCitas=2;
                break;
            default:
                numCitas=1;
                break;
        }
    }
}