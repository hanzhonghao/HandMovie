package com.exercise.handmovie;

import android.os.Bundle;

import com.exercise.handmovie.base.BaseActivity;

public class MainActivity extends BaseActivity {
    public final static String Banner_Stop = "Banner_Stop";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
