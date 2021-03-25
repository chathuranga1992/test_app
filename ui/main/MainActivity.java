package com.example.sample_log_app.ui.main;

import android.os.Bundle;

import com.example.sample_log_app.GenericActivity;
import com.example.sample_log_app.R;

import butterknife.ButterKnife;

public class MainActivity extends GenericActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }
}

