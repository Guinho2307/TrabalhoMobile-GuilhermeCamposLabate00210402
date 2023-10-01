package com.example.trabalho1bimpaulimn.modelo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.trabalho1bimpaulimn.R;

public class Item {

    String NomeItem;
    String Codigo;
    Double ValorUnitario;

    public Item() {
    }

    public Item(String nomeItem, String codigo, Double valorUnitario) {
        NomeItem = nomeItem;
        Codigo = codigo;
        ValorUnitario = valorUnitario;
    }

    public String getNomeItem() {
        return NomeItem;
    }

    public void setNomeItem(String nomeItem) {
        NomeItem = nomeItem;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public Double getValorUnitario() {
        return ValorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        ValorUnitario = valorUnitario;
    }
}