package com.example.futres_;

import com.example.futres_.Objetos.Partida;
import com.example.futres_.Objetos.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GetWebservices {
    @Headers({
            "Content-Type: application/json"
    })

    @GET("partida/ConsultaPartidasPorRodadas/{idRodada}")
    Call<List<Partida>> getPartidasPorRodada(@Path("idRodada") int idRodada);

    //@GET("partida/ConsultaPartidasPorRodadas/{idRodada}")
    //Call<Partida> getPartidasPorRodada(@Path("idRodada") int idRodada);

    //@GET("partida/ConsultaPartidasPorRodadas/{idRodada}")
    //Call<Partida> getPartidasPorRodada(@Path("idRodada") int idRodada);

    @POST("Usuario/Logar")
    Call<String[]> logar(@Body Usuario usuario);

    //@POST("users/new")
    //Call<User> createUser(@Body User user);
}
