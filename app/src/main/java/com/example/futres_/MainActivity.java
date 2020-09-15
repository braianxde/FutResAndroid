package com.example.futres_;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button botao01, botao02, botao03, botao04;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao01 = (Button) findViewById(R.id.log);
        botao02 = (Button) findViewById(R.id.Classif);
        botao03 = (Button) findViewById(R.id.ResRod);
        botao04 = (Button) findViewById(R.id.Sair);

        botao01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botao01Activity();
            }
        });
        }
    private void botao01Activity() {
    startActivity(new Intent(MainActivity.this,Login.class));

        botao02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botao02Activity();
            }
        });
        }
    private void botao02Activity() {
        startActivity(new Intent(MainActivity.this, Classificacao.class));


        botao03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botao03Activity();
            }
        });
    }
    private void botao03Activity() {
        startActivity(new Intent(MainActivity.this, Rodada.class));
        }
    }
