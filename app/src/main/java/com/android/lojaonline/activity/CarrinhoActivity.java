package com.android.lojaonline.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.lojaonline.R;
import com.android.lojaonline.adapter.AdaperListaProdutos;
import com.android.lojaonline.adapter.AdpterListaCarrinho;
import com.android.lojaonline.basededados.CarrinhoDB;
import com.android.lojaonline.basededados.PedidoDB;
import com.android.lojaonline.basededados.ProdutosDB;
import com.android.lojaonline.domain.Carrinho;
import com.android.lojaonline.domain.Cliente;
import com.android.lojaonline.domain.Pedido;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CarrinhoActivity extends AppCompatActivity {

    Cliente cli;
    Toolbar toolbar;
    RecyclerView listaCarrinho;
    List<Carrinho> dataSetListaCarrinho;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private TextView tTotalCarrinho;
    private Button btFinalizarPed;
    private EditText edNumCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);


        toolbar = findViewById(R.id.toolBar);

        if (toolbar != null) {

            toolbar.setTitle(R.string.title_toolbar_carrinho);
            setSupportActionBar(toolbar);

        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cli = (Cliente) getIntent().getSerializableExtra("cliente");


        listaCarrinho = findViewById(R.id.lista_itens_carrinho);
        mLayoutManager = new LinearLayoutManager(this);
        listaCarrinho.setLayoutManager(mLayoutManager);
        listaCarrinho.setItemAnimator(new DefaultItemAnimator());
        listaCarrinho.setHasFixedSize(true);

        btFinalizarPed = findViewById(R.id.btFinalizarPedido);
        edNumCart = findViewById(R.id.edNumCartCred);

        CarrinhoDB carrinhoDB = new CarrinhoDB(this);

        dataSetListaCarrinho = carrinhoDB.getProdutosCarrinho();

        mAdapter = new AdpterListaCarrinho(this, dataSetListaCarrinho);

        listaCarrinho.setAdapter(mAdapter);

        btFinalizarPed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                PedidoDB pedidoDB = new PedidoDB(getBaseContext());
                Pedido ped = new Pedido();
                CarrinhoDB car = new CarrinhoDB(getBaseContext());
                List<Carrinho> listaCarrinho = dataSetListaCarrinho;

                ped.cpfCli = cli.cpf;
                ped.dataPed = GregorianCalendar.getInstance().getTime();
                ped.valorPed = car.getValorTotalCarrinho();
                ped.cliPedido = cli.cliente;

                pedidoDB.savePedido(ped);
                pedidoDB.saveItensPedido(listaCarrinho);
                pedidoDB.savePagamento(ped, edNumCart.getText().toString());

                car.limpaCarrinho();

                Toast.makeText(getBaseContext(), "Pedido finalizado com sucesso!", Toast.LENGTH_SHORT).show();

                finish();

            }
        });

    }


    public void updateTotalCarrinho(Context ctx, float valor) {

        tTotalCarrinho = ((Activity) ctx).findViewById(R.id.txValorTotalCarrinho);

        tTotalCarrinho.setText(NumberFormat.getCurrencyInstance().format(valor));

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
