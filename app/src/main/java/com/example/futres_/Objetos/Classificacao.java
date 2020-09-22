package com.example.futres_.Objetos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Classificacao {
    @SerializedName("Id")
    @Expose
        private int Id;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
