package com.android.lojaonline.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.lojaonline.R;
import com.android.lojaonline.domain.Produto;

import java.util.List;

/**
 * Created by luis gustavo on 01/08/2016.
 */

public class AdapterListaProdutosCadastro extends RecyclerView.Adapter<AdapterListaProdutosCadastro.ViewHolder> {

    private List<Produto> produtoList;
    private Context ctx;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txDescProd;

        public ViewHolder(View v) {
            super(v);
            txDescProd = v.findViewById(R.id.txtDescricaoProduto);
        }

    }

    public AdapterListaProdutosCadastro (Context context, List<Produto> lista){
        this.ctx = context;
        this.produtoList = lista;
    }

    @Override
    public AdapterListaProdutosCadastro.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_produtos_cadastro, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txDescProd.setText(produtoList.get(position).produto);

    }

    @Override
    public int getItemCount() {
        return this.produtoList != null ? this.produtoList.size() : 0;
    }
}
