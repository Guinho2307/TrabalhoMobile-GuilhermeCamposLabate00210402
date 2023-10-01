package com.example.trabalho1bimpaulimn.modelo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.trabalho1bimpaulimn.R;

public class Cliente {

    String Nome;
    String CPF;

    public Cliente(){

    }

    public Cliente(String nome, String CPF) {
        Nome = nome;
        this.CPF = CPF;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
}