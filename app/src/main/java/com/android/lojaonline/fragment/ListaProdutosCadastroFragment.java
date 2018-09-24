package com.android.lojaonline.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.lojaonline.R;
import com.android.lojaonline.adapter.AdapterListaPedidos;
import com.android.lojaonline.adapter.AdapterListaProdutosCadastro;
import com.android.lojaonline.basededados.PedidoDB;
import com.android.lojaonline.basededados.ProdutosDB;
import com.android.lojaonline.domain.Pedido;
import com.android.lojaonline.domain.Produto;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaProdutosCadastroFragment extends Fragment {

    private RecyclerView recyclerViewProdutos;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private FloatingActionButton fab;


    public ListaProdutosCadastroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_produtos_cadastro, container, false);

        fab = view.findViewById(R.id.fabAddProduto);

        recyclerViewProdutos =  view.findViewById(R.id.recyclerViewlistaProdutosCadastro);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewProdutos.setLayoutManager(mLayoutManager);
        recyclerViewProdutos.setItemAnimator(new DefaultItemAnimator());
        recyclerViewProdutos.setHasFixedSize(true);

        ProdutosDB p = new ProdutosDB(getContext());

        List<Produto> myDataset = p.getProdutos();
        mAdapter = new AdapterListaProdutosCadastro(getContext(), myDataset);
        recyclerViewProdutos.setAdapter(mAdapter);


        return  view;
    }

    @Override
    public void onResume() {
        super.onResume();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Add produto", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
