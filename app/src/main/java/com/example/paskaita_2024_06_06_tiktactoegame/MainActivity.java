package com.example.paskaita_2024_06_06_tiktactoegame;

import android.content.Context;
import android.os.Bundle;

import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_window);

        ViewGroup rootView = findViewById(android.R.id.content);
        Context context = getApplicationContext();

        UIManager uiManager = new UIManager(rootView, context);
        uiManager.run();


    }



}
