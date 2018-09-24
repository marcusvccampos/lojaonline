package com.android.lojaonline.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.lojaonline.R;
import com.android.lojaonline.basededados.CarrinhoDB;
import com.android.lojaonline.domain.Carrinho;
import com.android.lojaonline.domain.Produto;

import java.util.List;

/**
 * Created by luis gustavo on 01/08/2016.
 */

public class AdaperListaProdutos extends RecyclerView.Adapter<AdaperListaProdutos.ViewHolder> {

    private List<Produto> produtos;
    public Context context;
    public RecyclerView recyclerViewLista;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageButton btAddCarrinho;

        public ViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.txt_card_produto);
            btAddCarrinho = v.findViewById(R.id.btAddCarrinho);
        }

    }

    public AdaperListaProdutos(Context ctx, List<Produto> produtos) {
        this.produtos = produtos;
        this.context = ctx;
    }

    @Override
    public AdaperListaProdutos.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lista_produtos, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.mTextView.setText(produtos.get(position).produto);

        holder.btAddCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Produto p = produtos.get(position);

                CarrinhoDB carrinhoDB = new CarrinhoDB(context);

                Carrinho carrinho = new Carrinho();

                carrinho.idProd = p._id;
                carrinho.nomeProd = p.produto;
                carrinho.qt = 1;
                carrinho.valor = (carrinho.qt * p.preco);

                carrinhoDB.saveProdutoCarrinho(carrinho);

                Toast.makeText(context,  p.produto +" adicionado no carrinho!", Toast.LENGTH_SHORT).show();
                //Toast.makeText(context, " _id: " + String.valueOf(p._id) + " Produto: " + p.produto + " Est: " + p.qdeEstoque, Toast.LENGTH_SHORT).show();

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Produto p = produtos.get(position);

                //Toast.makeText(context, " _id: " + String.valueOf(p._id) + " Produto: " + p.produto + " Est: " + p.qdeEstoque, Toast.LENGTH_SHORT).show();

                //Intent it = new Intent(view.getContext(), CadastrarProdutoActivity.class);
                //it.putExtra("produto", p);
                //view.getContext().startActivity(it);
            }
        });


    }


    @Override
    public int getItemCount() {
        return this.produtos != null ? this.produtos.size() : 0;
    }


}
