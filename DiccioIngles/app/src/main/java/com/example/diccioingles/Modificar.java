package com.example.diccioingles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class Modificar extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_modificar;
    }

    @Override
    public void onClick(View v) {

    }
}