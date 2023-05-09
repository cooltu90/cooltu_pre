package com.codingtu.cooltu_pre;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import core.tools.ActStart;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ActStart.welcomeActivity(this);
        ActStart.formActivity(this);
//        ActStart.viewActivity(this);
    }
}