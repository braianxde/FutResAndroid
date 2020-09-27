package com.example.futres_.Objetos;

public class Partida {
    private String Data_hora;
    private int Imag_mandante;
    private int Imag_visitante;
    private String Gols_mandante;
    private String Gols_visitante;
    private String Nome_mandante;
    private String Nome_visitante;

    public Partida(String data_hora, int imag_mandante, int imag_visitante, String gols_mandante, String gols_visitante, String nome_mandante, String nome_visitante) {
        Data_hora = data_hora;
        Imag_mandante = imag_mandante;
        Imag_visitante = imag_visitante;
        Gols_mandante = gols_mandante;
        Gols_visitante = gols_visitante;
        Nome_mandante = nome_mandante;
        Nome_visitante = nome_visitante;
    }

    public String getData_hora() {
        return Data_hora;
    }

    public void setData_hora(String data_hora) {
        Data_hora = data_hora;
    }

    public int getImag_mandante() {
        return Imag_mandante;
    }

    public void setImag_mandante(int imag_mandante) {
        Imag_mandante = imag_mandante;
    }

    public int getImag_visitante() {
        return Imag_visitante;
    }

    public void setImag_visitante(int imag_visitante) {
        Imag_visitante = imag_visitante;
    }

    public String getGols_mandante() {
        return Gols_mandante;
    }

    public void setGols_mandante(String gols_mandante) {
        Gols_mandante = gols_mandante;
    }

    public String getGols_visitante() {
        return Gols_visitante;
    }

    public void setGols_visitante(String gols_visitante) {
        Gols_visitante = gols_visitante;
    }

    public String getNome_mandante() {
        return Nome_mandante;
    }

    public void setNome_mandante(String nome_mandante) {
        Nome_mandante = nome_mandante;
    }

    public String getNome_visitante() {
        return Nome_visitante;
    }

    public void setNome_visitante(String nome_visitante) {
        Nome_visitante = nome_visitante;
    }
}
