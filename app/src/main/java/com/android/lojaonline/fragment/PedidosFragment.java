package com.android.lojaonline.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.lojaonline.R;
import com.android.lojaonline.adapter.AdaperListaProdutos;
import com.android.lojaonline.adapter.AdapterListaPedidos;
import com.android.lojaonline.basededados.PedidoDB;
import com.android.lojaonline.basededados.ProdutosDB;
import com.android.lojaonline.domain.Pedido;
import com.android.lojaonline.domain.Produto;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PedidosFragment extends Fragment {

    private RecyclerView recyclerViewPedidos;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;


    public PedidosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pedidos, container, false);

        recyclerViewPedidos =  view.findViewById(R.id.listaPedidos);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewPedidos.setLayoutManager(mLayoutManager);
        recyclerViewPedidos.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPedidos.setHasFixedSize(true);

        PedidoDB p = new PedidoDB(getContext());

        List<Pedido> myDataset = p.listaPedidos();
        mAdapter = new AdapterListaPedidos(getContext(), myDataset);
        recyclerViewPedidos.setAdapter(mAdapter);


        return  view;

    }

}
