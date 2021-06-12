package com.example.futres_.Objetos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RodadasRel {
    @SerializedName("id")
    @Expose
    private String id;

    public RodadasRel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getId(); // You can add anything else like maybe getDrinkType()
    }
}
