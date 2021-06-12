package com.example.futres_;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.futres_.Objetos.Partida;
import com.example.futres_.Objetos.RodadasRel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.regex.Pattern;

import cz.msebera.android.httpclient.Header;


public class Rodada extends AppCompatActivity {
    String token = "";
    private Spinner escollha;
    String de [] = {"dtJogo","golMandante","golVisitante","nomeMandante","nomeVisitante"};
    int para [] = {R.id.txtData, R.id.txtGol_Mandante, R.id.txtGol_Visitante,R.id.txtNome_mandante, R.id.txtNome_visitante};
    ArrayList<RodadasRel> rodadas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rodada);
        token = getIntent().getExtras().getString("token");
        escollha = findViewById(R.id.escolha);

        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.futreslogo);
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#A9A9A9"))
        );

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", token);
        client.get("https://projetointegrador4a20210527235624.azurewebsites.net/api/rodada/ListarRodadas", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String data = new String(response);
                try {
                    loadDataRodada(data);
                } catch (JSONException e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("").replace("-", "").toLowerCase().replace(" ", "");
    }

    private void loadData(String data) throws  JSONException {
        JSONArray array = new JSONArray(data);
        ArrayList<Partida> arrayList = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            JSONObject json = array.getJSONObject(i);
            String dtjogo = json.get("Data_hora").toString();
            String golM = json.get("Gols_mandante").toString();
            String golV = json.get("Gols_visitante").toString();
            String nomeM = json.get("Nome_mandante").toString();
            String nomeV = json.get("Nome_visitante").toString();

            String nomeSemAcentoM = deAccent(nomeM);
            String nomeSemAcentoV = deAccent(nomeV);

            int imgIdM = getResources().getIdentifier(nomeSemAcentoM, "drawable", getPackageName());
            int imgIdV = getResources().getIdentifier(nomeSemAcentoV, "drawable", getPackageName());

            arrayList.add(new  Partida( dtjogo,  imgIdM,  imgIdV, golM,  golV, nomeM, nomeV));
        }

        PartidaAdapter partidaAdapter = new PartidaAdapter(this, R.layout.linha_rodada, arrayList);
        ListView listView = findViewById(R.id.listView1);
        listView.setAdapter(partidaAdapter);

    }

    private void loadDataRodada(String data) throws  JSONException {
        JSONArray array = new JSONArray(data);
        ArrayList<RodadasRel> arrayList = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            JSONObject json = array.getJSONObject(i);
            String id = json.get("id").toString();

            arrayList.add(new RodadasRel(id));
        }

        rodadas = arrayList;

        RodadaAdapter adapter1 = new RodadaAdapter(this, R.layout.meu_spinner_item, rodadas);
        adapter1.notifyDataSetChanged();
        escollha.setAdapter(adapter1);

        escollha.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String[] arrayRodadas =  getResources().getStringArray(R.array.escolhaRod);

                AsyncHttpClient client = new AsyncHttpClient();
                client.addHeader("Authorization", token);
                client.get("https://projetointegrador4a20210527235624.azurewebsites.net/api/partida/ConsultaPartidasPorRodada/" + arrayRodadas[position], new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                        String data = new String(response);
                        try {
                            loadData(data);
                        } catch (JSONException e){
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

}
