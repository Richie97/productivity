package com.dimensions.productivity.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dimensions.productivity.R;
import com.dimensions.productivity.view.impl.OnboardingActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, OnboardingActivity.class));
        finish();
    }
}
