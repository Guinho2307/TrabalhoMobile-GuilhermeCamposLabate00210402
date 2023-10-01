package com.example.trabalho1bimpaulimn.modelo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.trabalho1bimpaulimn.R;

public class Pedido {

    int Quantidade;
    double ValorTotal;
    int Parcelas;

    Cliente cliente;
    Item item;
    public Pedido() {

    }
    public Pedido(int quantidade, double valorTotal, int parcelas, Cliente cliente, Item item) {
        Quantidade = quantidade;
        ValorTotal = valorTotal;
        Parcelas = parcelas;
        this.cliente = cliente;
        this.item = item;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int quantidade) {
        Quantidade = quantidade;
    }

    public double getValorTotal() {
        return ValorTotal;
    }

    public void setValorTotal(double valorTotal) {
        ValorTotal = valorTotal;
    }

    public int getParcelas() {
        return Parcelas;
    }

    public void setParcelas(int parcelas) {
        Parcelas = parcelas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }






}