package com.example.futres_;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usuario {
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("contador")
    @Expose
    private String contador;
    @SerializedName("senha")
    @Expose
    private String senha;

    public Usuario(String token) {
        this.token = token;
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContador() {
        return contador;
    }

    public void setContador(String contador) {
        this.contador = contador;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
