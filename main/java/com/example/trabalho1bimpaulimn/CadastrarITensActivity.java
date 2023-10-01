package com.example.trabalho1bimpaulimn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trabalho1bimpaulimn.modelo.Item;

import java.util.Random;

public class CadastrarITensActivity extends AppCompatActivity {


    private EditText edNomeItem;

    private EditText edValorItem;
    private TextView tvRetornarCodItem;
    private Button btSalvarItem;
    private TextView tvItensCadastrados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_itens);

        edNomeItem = findViewById(R.id.edNomeItem);
        edValorItem = findViewById(R.id.edValorItem);
        tvRetornarCodItem  = findViewById(R.id.tvRetornarCodItem);
        btSalvarItem  = findViewById(R.id.btSalvarItem);
        tvItensCadastrados  = findViewById(R.id.tvItensCadastrados);

        btSalvarItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                SalvarItem();
            }
        });
        
        atualizarListaItens();
        
        geradorCodigo();
    }



    private void geradorCodigo() {
        int codigo = new Random().nextInt(5000);
        tvRetornarCodItem.setText("Código do Item:" +codigo);

    }

    private void SalvarItem() {
       double valorUnitarioItem;

       if (tvRetornarCodItem.getText().toString().isEmpty()){
           tvRetornarCodItem.setError("Cliente no Botão para gerar um Código!");
           return;
        }
        if (edNomeItem.getText().toString().isEmpty()){
            edNomeItem.setError("Informe o Nome do Item!");
            return;
        }
        if (edValorItem.getText().toString().isEmpty()){
            edValorItem.setError("Informe o valor do Item!");
            edValorItem.requestFocus();
            return;
        }else {
            valorUnitarioItem = Double.parseDouble(edValorItem.getText().toString());
            if (valorUnitarioItem <=0){
                edValorItem.setError("O valor do item tem que ser maior que zero!");
                edValorItem.requestFocus();
                return;
            }
        }

        Item item = new Item();
        item.setCodigo(tvRetornarCodItem.getText().toString());
        item.setNomeItem(edNomeItem.getText().toString());
        item.setValorUnitario(Double.parseDouble(edValorItem.getText().toString()));

        Controller.getInstance().salvarItens(item);
        Toast.makeText(CadastrarITensActivity.this,
                "Item de "+ item.getCodigo()+ " Foi cadastrado com sucesso!!",
                Toast.LENGTH_LONG).show();
        this.finish();


    }
    private void atualizarListaItens() {

        String msg = "";
        for (Item item : Controller.getInstance().retornarItens()){
            msg = msg + item.getCodigo() +
                    " \n Nome Item: " + item.getNomeItem() +
                    " \n Valor Unitário Item  R$ " + item.getValorUnitario() + " \n";


        }

        tvItensCadastrados.setText(msg);

    }
}