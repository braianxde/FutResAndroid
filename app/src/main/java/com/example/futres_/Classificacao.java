package com.example.futres_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class Classificacao extends AppCompatActivity {
    String token = "";
    private ListView listView;
    String de [] = {"posicao","clube","pontos","vit","emp","der","gp","gc"};
    int para [] = {R.id.txtPosicao, R.id.txtClube, R.id.txtPontos, R.id.txtVitorias, R.id.txtEmpates, R.id.txtDerrotas, R.id.txtGol_pro, R.id.txtGol_contra};
    List<Map<String, String >> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classificacao);
        token = getIntent().getExtras().getString("token");


        listView = findViewById(R.id.listView);

        this.onClickBuscar();

    }

    public void onClickBuscar() {
        lista = new ArrayList<>();

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://projetointegrador4a.azurewebsites.net/api/clube/Listarclubes", new AsyncHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String data = new String(response);
               // Toast.makeText(getApplicationContext(), data, Toast.LENGTH_LONG).show();
                try {
                    loadData(data);
                } catch (JSONException e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }

                   });
    }

    private void loadData(String data) throws JSONException {


        JSONArray array = new JSONArray(data);

        for (int i = 0; i < array.length(); i++) {
            if (i == 0) {
                Map<String,String> mapa = new HashMap<>();
                mapa.put("posicao", "");
                mapa.put("clube", "CLUBE");
                mapa.put("pontos", "P");
                mapa.put("vit", "V");
                mapa.put("emp", "E");
                mapa.put("der", "D");
                mapa.put("gp", "GP");
                mapa.put("gc", "GC");
                lista.add(mapa);
            }
            JSONObject json = array.getJSONObject(i);
            String nome = json.get("Nome").toString();
            String pont = json.get("Pontos").toString();
            String vit  = json.get("Vitorias").toString();
            String emp = json.get("Empates").toString();
            String derr = json.get("Derrotas").toString();
            String gpro = json.get("Gols_pro").toString();
            String gcont = json.get("Gols_contra").toString();
            int posi = i + 1;

            Map<String,String> mapa = new HashMap<>();
            mapa.put("posicao", Integer.toString(posi));
            mapa.put("clube", nome);
            mapa.put("pontos", pont);
            mapa.put("vit", vit);
            mapa.put("emp", emp);
            mapa.put("der", derr);
            mapa.put("gp", gpro);
            mapa.put("gc", gcont);
            lista.add(mapa);

        }

        SimpleAdapter adapter = new SimpleAdapter(this, lista,R.layout.linha_class,de,para);
        listView.setAdapter(adapter);

    }

    public void onClickRodada(View view) {
        Intent intentRodada = new Intent(Classificacao.this, Rodada.class);
        intentRodada.putExtra("token", token);

        startActivity(intentRodada);
    }
}