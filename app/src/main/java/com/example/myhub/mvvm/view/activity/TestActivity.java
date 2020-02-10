package com.example.myhub.mvvm.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Space;

import com.example.myhub.R;

public class TestActivity extends AppCompatActivity {

    private Button btnTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        btnTest = findViewById(R.id.btn_test);
        btnTest.setOnClickListener(v -> {
            Intent intent = new Intent(TestActivity.this, SplashActivity.class);
            startActivity(intent);
        });
    }
}
