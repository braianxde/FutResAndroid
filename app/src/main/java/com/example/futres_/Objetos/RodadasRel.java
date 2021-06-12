package com.example.futres_.Objetos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RodadasRel {


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nome_rodada")
    @Expose
    private String nome_rodada;

    public RodadasRel(String id, String nome_rodada) {
        this.id = id;
        this.nome_rodada = nome_rodada;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome_rodada() {
        return nome_rodada;
    }

    public void setNome_rodada(String nome_rodada) {
        this.nome_rodada = nome_rodada;
    }
}
