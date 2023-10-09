package com.example.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TestEjemplos extends MainActivity {

    String tests[] = { "LifeCycleTest", "SingleTouchTest", "MultiTouchTest",
            "KeyTest", "Acelerometro", "AssetsTest",
            "ExternalStorageTest", "SoundPoolTest", "MediaPlayerTest",
            "FullScreenTest", "WakeLockTest", "RenderViewTest", "ShapeTest", "BitmapTest",
            "FontTest", "SurfaceViewTest" };

//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tests));
//    }


//    @Override
//    protected void onListItemClick(ListView list, View view, int position,long id) {
//        super.onListItemClick(list, view, position, id);
//
//        String testName = tests[position];
//
//        try {
//
//            Class clazz = Class.forName("com.dam.luis." + testName); //cambiar nombre al nuestro
//            Intent intent = new Intent(this, clazz);
//            startActivity(intent);
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}