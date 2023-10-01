package com.example.trabalho1bimpaulimn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.trabalho1bimpaulimn.modelo.Cliente;
import com.example.trabalho1bimpaulimn.modelo.Item;
import com.example.trabalho1bimpaulimn.modelo.Pedido;

import java.util.ArrayList;

public class Controller {
    private static Controller instancia;
    private ArrayList<Cliente> listaCliente;
    private ArrayList<Item> listaItens;
    private ArrayList<Pedido> listaPedido;
    private ArrayList<Pedido> listaPagamento;

    public static Controller getInstance(){
        if (instancia ==  null)
            return instancia = new Controller();
        else
            return instancia;
    }

    public Controller() {
        listaPagamento = new ArrayList<>();
        listaCliente   = new ArrayList<>();
        listaItens     = new ArrayList<>();
        listaPedido    = new ArrayList<>();
    }

    public void salvarPagamento(Pedido pagamento){listaPagamento.add(pagamento);}
    public ArrayList<Pedido> retornaPagamento(){
        return  listaPagamento;
    }
    public void salvarClientes(Cliente cliente){
        listaCliente.add(cliente);
    }
    public  ArrayList<Cliente> retornarClientes(){
        return  listaCliente;
    }

    public void salvarItens(Item item){
        listaItens.add(item);
    }
    public  ArrayList<Item> retornarItens(){
        return  listaItens;
    }

    public void adicionarPedido(Pedido pedido){
        listaPedido.add(pedido);
    }
    public  ArrayList<Pedido> retornarPedidos(){
        return  listaPedido;
    }



}