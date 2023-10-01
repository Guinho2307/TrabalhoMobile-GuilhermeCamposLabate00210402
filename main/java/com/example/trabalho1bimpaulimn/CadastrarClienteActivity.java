package com.example.trabalho1bimpaulimn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trabalho1bimpaulimn.modelo.Cliente;

public class CadastrarClienteActivity extends AppCompatActivity {


    private EditText edNomeCliente;
    private EditText edCpfCliente;
    private Button btSalvarCliente;
    private TextView tvClienteCadastrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);

        edNomeCliente = findViewById(R.id.edNomeCliente);
        edCpfCliente = findViewById(R.id.edCpfCliente);
        btSalvarCliente = findViewById(R.id.btSalvarCliente);
        tvClienteCadastrado  = findViewById(R.id.tvClienteCadastrado);

        atualizarListaCliente();

        btSalvarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarCliente();
            }
        });

    }

    private void salvarCliente() {
        if(edNomeCliente.getText().toString().isEmpty()){
            edNomeCliente.setError("Por favor Informe o nome do Cliente!");
            return;
        }
        if(edCpfCliente.getText().toString().isEmpty()){
            edCpfCliente.setError("Por favor informe o CPF do Cliente!");
            return;
        }


        Cliente cliente = new Cliente();
        cliente.setNome(edNomeCliente.getText().toString());
        cliente.setCPF(edCpfCliente.getText().toString());

        Controller.getInstance().salvarClientes(cliente);

        Toast.makeText(CadastrarClienteActivity.this,
                "Cliente cadastrado com sucesso!!",Toast.LENGTH_LONG).show();

        this.finish();
    }

    private void atualizarListaCliente() {
       String texto = "";
       for (Cliente cliente : Controller.getInstance().retornarClientes()){
           texto += texto + "Nome: " +cliente.getNome() +
                   " \n CPF : " + cliente.getCPF() + " \n";

       }
        tvClienteCadastrado.setText(texto);


    }


}