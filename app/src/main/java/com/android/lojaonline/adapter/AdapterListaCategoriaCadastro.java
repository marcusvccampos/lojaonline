package com.android.lojaonline.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.lojaonline.R;
import com.android.lojaonline.domain.Categoria;

import java.util.List;

/**
 * Created by luis gustavo on 01/08/2016.
 */

public class AdapterListaCategoriaCadastro extends RecyclerView.Adapter<AdapterListaCategoriaCadastro.ViewHolder> {


    private List<Categoria> categoriaList;
    private Context ctx;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txDescCategoria;

        public ViewHolder(View v) {
            super(v);
            txDescCategoria = v.findViewById(R.id.txtDescricaoCategoria);
        }

    }

    public AdapterListaCategoriaCadastro(Context context, List<Categoria> list){
        this.ctx = context;
        this.categoriaList = list;
    }

    @Override
    public AdapterListaCategoriaCadastro.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_categoria_cadastro, parent,false);

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txDescCategoria.setText(categoriaList.get(position).descricao);

    }


    @Override
    public int getItemCount() {
        return this.categoriaList != null ? this.categoriaList.size() : 0;
    }
}
