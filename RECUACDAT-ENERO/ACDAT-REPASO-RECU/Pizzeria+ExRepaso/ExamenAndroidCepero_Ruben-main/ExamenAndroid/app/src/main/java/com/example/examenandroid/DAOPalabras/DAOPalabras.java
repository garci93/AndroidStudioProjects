package com.example.examenandroid.DAOPalabras;

import android.content.Context;

import com.example.examenandroid.BD.BDPalabras;

import java.util.ArrayList;
import java.util.List;

public class DAOPalabras {


    private List<String> listaDePalabras;

    private BDPalabras bdPalabras;

    public DAOPalabras(Context context){
        bdPalabras = new BDPalabras(context);
        insertarTodasLasPalabras();
        listaDePalabras = bdPalabras.getPalabras();


    }


    public void insertarTodasLasPalabras(){

        bdPalabras.insertarPalabra("belleza");
        bdPalabras.insertarPalabra("sufrir");
        bdPalabras.insertarPalabra("hojaldre");
        bdPalabras.insertarPalabra("basket");
        bdPalabras.insertarPalabra("café");
        bdPalabras.insertarPalabra("normal");
        bdPalabras.insertarPalabra("alarma");
        bdPalabras.insertarPalabra("serenidad");
        bdPalabras.insertarPalabra("código");
        bdPalabras.insertarPalabra("fútbol");
        bdPalabras.insertarPalabra("manzana");
        bdPalabras.insertarPalabra("tomate");
        bdPalabras.insertarPalabra("lápiz");
        bdPalabras.insertarPalabra("piña");
        bdPalabras.insertarPalabra("boligrafo");
        bdPalabras.insertarPalabra("planeta");
        bdPalabras.insertarPalabra("letra");
        bdPalabras.insertarPalabra("numero");
        bdPalabras.insertarPalabra("padre");
        bdPalabras.insertarPalabra("madre");
    }

    public List<String> getListaDePalabras(){
        return this.listaDePalabras;
    }


    public  void borrarPalabra(String palabra){
        this.listaDePalabras.remove(palabra);
    }

    public void modificarPalabra(String palabra, String palabraNueva){
        this.listaDePalabras.remove(palabra);

        this.listaDePalabras.add(palabraNueva);
    }




}
