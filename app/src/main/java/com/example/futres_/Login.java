package com.example.futres_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.futres_.Objetos.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    private Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonLogin = (Button)findViewById(R.id.login);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logar();
            }
        });

    }

    private void logar () {
        EditText editTextEmail = findViewById(R.id.email);
        EditText editTextSenha = findViewById(R.id.senha);
        String email = editTextEmail.getText().toString();
        String senha = editTextSenha.getText().toString();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        String BASE_URL = "https://projetointegrador4a20210527235624.azurewebsites.net/api/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Usuario user = new Usuario(email, senha);

        GetWebservices webservices = retrofit.create(GetWebservices.class);

        Call<String[]> call = webservices.logar(user);
        call.enqueue(new Callback<String[]>() {
            @Override
            public void onResponse(Call<String[]> call, Response<String[]> response) {
                String[] arrayResponse = response.body();

                if(arrayResponse[0].length()>1) {
                    //informa a tela que eu estou e pra onde eu vou
                    Intent intentClassificacao = new Intent(Login.this, Classificacao.class);
                    //coloco uma variavel pra passar entre as telas
                    intentClassificacao.putExtra("token", arrayResponse[0]);
                    //vou pra a tela
                    startActivity(intentClassificacao);
                } else {
                    //primeiro parametro eh a tela, segundo paramentro e a string a ser mostrada (cuidar com o tipo)
                    Toast.makeText(Login.this, "Usuario ou senha incorretos", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String[]> call, Throwable t) {
                Toast.makeText(Login.this, "Falha ao conectar no servidor", Toast.LENGTH_LONG).show();
            }
        });
    }
}