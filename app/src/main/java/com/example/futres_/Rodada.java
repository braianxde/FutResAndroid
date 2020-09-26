package com.example.futres_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;


public class Rodada extends AppCompatActivity {
    String token = "";
    private Spinner escollha;
    private ListView listView;
    String de [] = {"dtJogo","golMandante","golVisitante","nomeMandante","nomeVisitante"};
    int para [] = {R.id.txtData, R.id.txtGol_Mandante, R.id.txtGol_Visitante,R.id.txtNome_mandante, R.id.txtNome_visitante};
    List<Map<String, String >> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rodada);
        token = getIntent().getExtras().getString("token");

        listView = findViewById(R.id.listView1);

        escollha = findViewById(R.id.escolha);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.escolhaRod,
              R.layout.meu_spinner_item);
        escollha.setAdapter(adapter1);

        escollha.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                lista = new ArrayList<>();

                AsyncHttpClient client = new AsyncHttpClient();
                client.get("https://projetointegrador4a.azurewebsites.net/api/partida/ConsultaPartidasPorRodada/" + R.array.escolhaRod[i], new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                        String data = new String(response);
                        try {
                            loadData(data);
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void loadData(String data) throws  JSONException {
        JsonArray array = new JsonArray(data);

        for (int i = 0; i < array.length(); i++) {
            JSONObject json = array.getJSONObject(i);
            String dtjogo = json.get("Data_hora").toString();
            String golM = json.get("Gols_mandante").toString();
            String golV = json.get("Gols_visitante").toString();
            String nomeM = json.get("Nome_mandante").toString();
            String nomeV = json.get("Nome_visitante").toString();

            Map<String, String> mapa = new HashMap<>();
            mapa.put("dtJogo", dtjogo);
            mapa.put("golMandante", golM);
            mapa.put("golVisitante", golV);
            mapa.put("nomeMandante", nomeM);
            mapa.put("nomeVisitante", nomeV);
            lista.add(mapa);

        }
        SimpleAdapter adapter2 = new SimpleAdapter(this, lista, R.layout.linha_rodada, de, para);
        listView.setAdapter(adapter2);

    }


}
