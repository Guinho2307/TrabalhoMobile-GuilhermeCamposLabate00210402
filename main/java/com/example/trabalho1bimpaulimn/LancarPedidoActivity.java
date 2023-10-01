package com.example.trabalho1bimpaulimn;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trabalho1bimpaulimn.modelo.Cliente;
import com.example.trabalho1bimpaulimn.modelo.Item;
import com.example.trabalho1bimpaulimn.modelo.Pedido;

import java.util.ArrayList;
import java.util.Random;

public class LancarPedidoActivity extends AppCompatActivity {

    private Spinner spSelecionarCliente;
    private TextView tvClienteNaoSelecionado;
    private Spinner spSelecionarItem;
    private TextView tvItemNaoSelecionado;
    private TextView tvValorUnitarioItem;
    private TextView edQtdItens;
    private TextView tvRetornarCodigoPedido;
    private ImageButton btAdicionarPedido;
    private TextView tvDescricaoItem;
    private TextView tvTotaldosITens;
    private TextView tvListaValorttl;
    private RadioButton rbPgAvista;
    private RadioButton rbPgAprazo;
    private RadioGroup rgPagamentos;
    private LinearLayout LNavista;
    private TextView tvAvista;

    private LinearLayout  LNaprazo;
    private EditText edQtdParcelas;
    private ImageButton btAddPGaPrazo;
    private TextView tvAPrazo;
    private Button btFinalizaPedido;

    private ArrayList<Cliente> listaClientes;

    private ArrayList<Item> listaItens;

    private int posicaoSelec = 0;
    private int posicaoITemSelec =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancar_pedido);

        spSelecionarCliente = findViewById(R.id.spSelecionarCliente);
        tvClienteNaoSelecionado = findViewById(R.id.tvClienteNaoSelecionado);
        spSelecionarItem = findViewById(R.id.spSelecionarItem);
        tvItemNaoSelecionado = findViewById(R.id.tvItemNaoSelecionado);
        tvValorUnitarioItem = findViewById(R.id.tvValorUnitarioItem);
        edQtdItens = findViewById(R.id.edQtdItens);
        tvRetornarCodigoPedido = findViewById(R.id.tvRetornarCodigoPedido);
        btAdicionarPedido = findViewById(R.id.btAdicionarPedido);
        tvDescricaoItem = findViewById(R.id.tvDescricaoItem);
        tvTotaldosITens = findViewById(R.id.tvTotaldosITens);
        tvListaValorttl = findViewById(R.id.tvListaValorttl);
        rbPgAvista = findViewById(R.id.rbPgAvista);
        rbPgAprazo = findViewById(R.id.rbPgAprazo);
        rgPagamentos = findViewById(R.id.rgPagamentos);
        LNavista = findViewById(R.id.LNavista);
        tvAvista = findViewById(R.id.tvAvista);
        LNaprazo = findViewById(R.id.LNaprazo);
        edQtdParcelas = findViewById(R.id.edQtdParcelas);
        btAddPGaPrazo = findViewById(R.id.btAddPGaPrazo);
        tvAPrazo = findViewById(R.id.tvAPrazo);
        btFinalizaPedido = findViewById(R.id.btFinalizaPedido);

        rgPagamentos.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int posicao) {
                if (posicao == rbPgAvista.getId()){
                    posicaoSelec = posicao;
                    LNavista.setVisibility(View.VISIBLE);
                    ExibeAVista();
                } else if (posicao == rbPgAprazo.getId()) {
                    int valores;
                    posicaoSelec = posicao;
                    LNaprazo.setVisibility(View.VISIBLE);

                }
            }
        });

        spSelecionarCliente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicao, long l) {
                if (posicao >0){
                    tvClienteNaoSelecionado.setVisibility(View.GONE);
                    posicaoSelec = posicao;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        
        spSelecionarItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int posicao2, long l) {
                if(posicao2 > 0){
                    tvItemNaoSelecionado.setVisibility((View.GONE));
                    posicaoITemSelec = posicao2;
                    exibeValorItem();
                    
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btFinalizaPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                finalizandoPedido();


            }

        });
        
        btAddPGaPrazo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvaPagamento();
                ExibePgAPRazo();
            }
        });


        
        btAdicionarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarPedido();
                atualizarNomePedido();
                
                quantidadeItensAdicionado();
                
                valorTotal();
                
            }
        });

        carregarClientes();

        carregarItens();

        gerarCodPedido();

    }

    private void finalizandoPedido() {
        Toast.makeText(LancarPedidoActivity.this, "Obrigado por comprar na loja do Manco",Toast.LENGTH_LONG).show();


            this.finish();
    }

    private void valorTotal() {
        ArrayList<Pedido> lista = Controller.getInstance().retornarPedidos();
        String ValorTotal = "";
        for (Pedido desc: lista){
            Item item = desc.getItem();
            ValorTotal += desc.getQuantidade()*item.getValorUnitario();

        }
        tvListaValorttl.setText(ValorTotal);
    }

    private void quantidadeItensAdicionado() {
        ArrayList<Pedido> lista = Controller.getInstance().retornarPedidos();
        String quantidadeItens = "";
        for (Pedido desc: lista){
            quantidadeItens += "Quantidade de itens que foram adicionados: "+desc.getQuantidade();

        }
        tvTotaldosITens.setText(quantidadeItens);
    }

    private void atualizarNomePedido() {
        ArrayList<Pedido> lista = Controller.getInstance().retornarPedidos();
        String msg = "";
        String nomeCliente = "";
        String codigoItem = "";
        String nomeItem = "";

        nomeCliente = listaClientes.get(posicaoITemSelec -1 ).getNome().toString();
        codigoItem = listaItens.get(posicaoITemSelec -1 ).getCodigo().toString();
        nomeItem= listaItens.get(posicaoITemSelec -1).getNomeItem().toString();
        msg = "Nome: "+ nomeCliente + "\n" +
                "Cod: " +codigoItem + "\n" +
                "Nome do Item"+nomeItem;

        tvDescricaoItem.setText(msg);
    }

    private void salvarPedido() {
        double quantidadeItens;

        if (edQtdItens.getText().toString().isEmpty()) {
            edQtdItens.setError("Informe a quantidade de Itens!");
            edQtdItens.requestFocus();
            return;
        } else {
            quantidadeItens = Double.parseDouble(edQtdItens.getText().toString());
            if (quantidadeItens <= 0) {
                edQtdItens.setError("Quantidade de Itens tem que ser maior que zero!");
                edQtdItens.requestFocus();
                return;
            }

        }
        if (posicaoSelec == 0) {
            tvClienteNaoSelecionado.setVisibility(View.VISIBLE);
            tvItemNaoSelecionado.setVisibility(View.VISIBLE);
            return;

        }

        Cliente cliente = listaClientes.get(posicaoSelec -1);
        Item item = listaItens.get(posicaoSelec -1);
        Pedido pedido = new Pedido();
        pedido.setQuantidade((Integer.parseInt(edQtdItens.getText().toString())));
        pedido.setCliente(cliente);
        pedido.setItem(item);

        Controller.getInstance().adicionarPedido(pedido);
        Toast.makeText(this, "O pedido " + item.getCodigo()+ "\n Salvo com Sucesso!!",
                Toast.LENGTH_LONG).show();


    }

    private void ExibePgAPRazo() {
        ArrayList<Pedido> lista = Controller.getInstance().retornarPedidos();


        int max = 0;
        double total = 0.0;

        for (Pedido desc : lista) {
          Item item = desc.getItem();
            total += item.getValorUnitario() * desc.getQuantidade();

       }
        double juros = total * (5/100);
        total = total + juros;
       max = Integer.parseInt(edQtdParcelas.getText().toString());
        double valorParcelas = total / max;
        String msg = "";
        for (int i = 0; i < max; i++) {
            msg += i+1+"ª parcela: R$"+valorParcelas+"\n";
        }
        tvAPrazo.setText(msg);


    }

    private void salvaPagamento() {

        double quantidadeParcelas;

        if(edQtdParcelas.getText().toString().isEmpty()){
            edQtdParcelas.setError("Informe a quantidade de parcelas!");
            edQtdParcelas.requestFocus();
            return;
        }else {
            quantidadeParcelas = Double.parseDouble(edQtdParcelas.getText().toString());
            if(quantidadeParcelas <= 0){
                edQtdParcelas.setError("Quantidade de parcelas tem que ser maior que zero!");
                edQtdParcelas.requestFocus();
                return;
            }
            if(quantidadeParcelas >10){
                edQtdParcelas.setError("Quantidade de parcelas tem que ser menor que dez!");
                edQtdParcelas.requestFocus();
                return;
            }

            Pedido pag = new Pedido();
            pag.setParcelas(Integer.parseInt(edQtdParcelas.getText().toString()));

            Controller.getInstance().salvarPagamento(pag);

            Toast.makeText(this,"Pagamento salvo!", Toast.LENGTH_LONG).show();


        }
    }

    private void exibeValorItem() {
        ArrayList<Item> lista = Controller.getInstance().retornarItens();
        String ExibeValorITem = "";

        ExibeValorITem = lista.get(posicaoITemSelec -1).getValorUnitario() + "R$";
        tvValorUnitarioItem.setText(ExibeValorITem);
    }

    private void gerarCodPedido() {
        int codigoPedido = new Random().nextInt(5000);
        tvRetornarCodigoPedido.setText(" código: " + codigoPedido);
    }

    private void carregarItens() {
        listaItens = Controller.getInstance().retornarItens();
        String[] vetItens = new String[listaItens.size()+1];
        vetItens[0] = "Selecione o Item";
        for (int i = 0; i < listaItens.size() ; i++) {
            Item item = listaItens.get(i);
            vetItens[i + 1] = item.getCodigo() + " - " + item.getNomeItem();


        }

        ArrayAdapter adapter = new ArrayAdapter(
                LancarPedidoActivity.this,
                android.R.layout.simple_dropdown_item_1line,
                vetItens);

        spSelecionarItem.setAdapter(adapter);
    }

    private void carregarClientes() {
        listaClientes = Controller.getInstance().retornarClientes();
        String[] vetClientes = new String[listaClientes.size() +1];
        vetClientes[0] = "Selcione o Cliente!";
        for (int i = 0; i < listaClientes.size(); i++) {
            Cliente cliente = listaClientes.get(i);
            vetClientes[i+1] = cliente.getNome() + " - " + cliente.getCPF();

        }
        ArrayAdapter adapter = new ArrayAdapter(
                LancarPedidoActivity.this,
                android.R.layout.simple_dropdown_item_1line,
                vetClientes);

        spSelecionarCliente.setAdapter(adapter);
    }




    private void ExibeAVista() {
        ArrayList<Pedido> lista = Controller.getInstance().retornarPedidos();

        String valorDesconto= "";
        String valorTotal = "";

        for (Pedido desc: lista) {
            Item item = desc.getItem();
            valorDesconto += ((desc.getQuantidade() * item.getValorUnitario())*0.05);
            valorTotal += ((desc.getQuantidade() * item.getValorUnitario())-Double.parseDouble(valorDesconto));

        }
        tvAvista.setText("R$: " + valorTotal);

    }



}