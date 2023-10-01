package com.example.trabalho1bimpaulimn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btCadastrarCliente;

    private Button btCadastrarItens;

    private Button btPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCadastrarItens = findViewById(R.id.btCadastrarItens);
        btCadastrarCliente = findViewById(R.id.btCadastrarCliente);
        btPedido = findViewById(R.id.btPedido);


        btCadastrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirActivity(CadastrarClienteActivity.class);
            }
        });
            btCadastrarItens.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    abrirActivity(CadastrarITensActivity.class);
                }
            });


            btPedido.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    abrirActivity(LancarPedidoActivity.class);
                }
            });

    }

    private void abrirActivity(Class<?> activity) {
        Intent intent = new Intent(MainActivity.this,
                activity);
        startActivity(intent);
    }
}