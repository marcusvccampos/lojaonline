package com.android.lojaonline.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.lojaonline.R;
import com.android.lojaonline.activity.CadastrarCategoria;
import com.android.lojaonline.adapter.AdapterListaCategoriaCadastro;
import com.android.lojaonline.adapter.AdapterListaProdutosCadastro;
import com.android.lojaonline.basededados.CategoriaDB;
import com.android.lojaonline.basededados.ProdutosDB;
import com.android.lojaonline.domain.Categoria;
import com.android.lojaonline.domain.Produto;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaCategoriaCadastroFragment extends Fragment {

    private RecyclerView recyclerViewCategoria;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private FloatingActionButton fab;


    public ListaCategoriaCadastroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista_categoria_cadastro, container, false);

        fab = view.findViewById(R.id.fabAddCategoria);

        recyclerViewCategoria = view.findViewById(R.id.listaCategoria);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewCategoria.setLayoutManager(mLayoutManager);
        recyclerViewCategoria.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCategoria.setHasFixedSize(true);

        CategoriaDB cat = new CategoriaDB(getContext());

        List<Categoria> myDataset = cat.getCategoria();
        mAdapter = new AdapterListaCategoriaCadastro(getContext(), myDataset);
        recyclerViewCategoria.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(), CadastrarCategoria.class);
                startActivity(it);
            }
        });
    }
}
