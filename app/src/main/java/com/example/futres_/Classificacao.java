package com.example.futres_;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.futres_.R;

public class Classificacao extends AppCompatActivity {
    String token = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_classificacao);
        token = getIntent().getExtras().getString("token");

    }
}