package com.android.lojaonline.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.lojaonline.R;
import com.android.lojaonline.adapter.AdaperListaProdutos;
import com.android.lojaonline.basededados.ProdutosDB;
import com.android.lojaonline.domain.Produto;

import java.util.List;

/**
 * Created by luis gustavo on 01/08/2016.
 */

public class ProdutoFragment extends Fragment {

    protected RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_produtos, container, false);
        recyclerView =  view.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        if (getArguments() != null) {

            ProdutosDB p = new ProdutosDB(getContext());

            List<Produto> myDataset = p.getProdutosPorCategoria(getArguments().getString("categoria"));
            mAdapter = new AdaperListaProdutos(getContext(), myDataset);
            recyclerView.setAdapter(mAdapter);

        }

        return view;
    }
}
