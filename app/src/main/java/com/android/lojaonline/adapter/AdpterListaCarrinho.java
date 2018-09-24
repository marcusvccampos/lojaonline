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
import com.android.lojaonline.activity.CarrinhoActivity;
import com.android.lojaonline.basededados.CarrinhoDB;
import com.android.lojaonline.domain.Carrinho;

import java.text.NumberFormat;
import java.util.List;

/**
 * Created by luis gustavo on 01/08/2016.
 */

public class AdpterListaCarrinho extends RecyclerView.Adapter<AdpterListaCarrinho.ViewHolder> {

    CarrinhoActivity carrinhoActivity;
    CarrinhoDB carrinhoDB;

    private List<Carrinho> carrinho;
    private Context context;
    private int qde = 1;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txDescProd;
        public ImageButton btExcluirItem;
        public TextView txQde;
        public ImageButton btAdd;
        public ImageButton btSub;
        public TextView txValorItem;

        public ViewHolder(View v) {
            super(v);
            txDescProd = v.findViewById(R.id.txDescProdCarrinho);
            btExcluirItem = v.findViewById(R.id.btExcluirItemCarrinho);
            txQde = v.findViewById(R.id.txQdeItemCarrinho);
            btAdd = v.findViewById(R.id.addQdeItemCarrinho);
            btSub = v.findViewById(R.id.subQdeItemCarrinho);
            txValorItem = v.findViewById(R.id.txValorItemCarrinho);
        }

    }

    public AdpterListaCarrinho(Context ctx, List<Carrinho> carrinho) {
        this.carrinho = carrinho;
        this.context = ctx;
    }


    @Override
    public AdpterListaCarrinho.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        carrinhoDB = new CarrinhoDB(context);

        float valor =  carrinhoDB.getValorTotalCarrinho();

        carrinhoActivity = new CarrinhoActivity();

        carrinhoActivity.updateTotalCarrinho(context,valor);

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_carrinho, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Carrinho car = carrinho.get(position);

        holder.txDescProd.setText(carrinho.get(position).nomeProd);
        holder.txValorItem.setText(NumberFormat.getCurrencyInstance().format(carrinho.get(position).valor));

        holder.btExcluirItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long IdProd = car.idProd;

                CarrinhoDB carrinhoDB =  new CarrinhoDB(context);
                int count = carrinhoDB.deleteItemCarrinho(String.valueOf(IdProd));

                if (count > 0){
                    //CarrinhoActivity carrinhoActivity = new CarrinhoActivity();
                    //carrinhoActivity.updateListaCarrinho();

                    Toast.makeText(context,  car.nomeProd +" EXCLUÍDO do carrinho!", Toast.LENGTH_SHORT).show();

                    carrinho.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, carrinho.size());

                    float valor =  carrinhoDB.getValorTotalCarrinho();

                    carrinhoActivity.updateTotalCarrinho(context,valor);
                }
            }
        });

        holder.btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                qde = qde + 1;

                car.qt = qde;

                holder.txQde.setText(String.valueOf(qde));

                holder.txValorItem.setText(NumberFormat.getCurrencyInstance().format(carrinho.get(position).valor * qde));

                carrinhoDB.updateQdeCarrinho(car);

                float valor =  carrinhoDB.getValorTotalCarrinho();

                carrinhoActivity.updateTotalCarrinho(context,valor);
            }
        });

        holder.btSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (qde > 1) {
                    qde = qde - 1;

                    car.qt = qde;

                    holder.txQde.setText(String.valueOf(qde));


                    holder.txValorItem.setText(NumberFormat.getCurrencyInstance().format(carrinho.get(position).valor * qde));

                    carrinhoDB.updateQdeCarrinho(car);

                    float valor =  carrinhoDB.getValorTotalCarrinho();

                    carrinhoActivity.updateTotalCarrinho(context,valor);
                }

            }
        });

    }

    @Override
    public int getItemCount() {

        return this.carrinho != null ? this.carrinho.size() : 0;

    }
}
