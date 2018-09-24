package com.android.lojaonline.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.lojaonline.R;
import com.android.lojaonline.domain.Pedido;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by luis gustavo on 01/08/2016.
 */

public class AdapterListaPedidos extends RecyclerView.Adapter<AdapterListaPedidos.ViewHolder> {

    private List<Pedido>  pedidos;
    private Context context;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txCodigoPedido;
        public TextView txDtPedido;
        public TextView txClientePedido;
        public TextView txCPFPedido;

        public ViewHolder(View v) {
            super(v);

            txCodigoPedido = v.findViewById(R.id.txCodigoPedido);
            txDtPedido = v.findViewById(R.id.txDtPedido);
            txClientePedido = v.findViewById(R.id.txClientePedido);
            txCPFPedido = v.findViewById(R.id.txCPFPedido);


        }

    }

    public AdapterListaPedidos(Context ctx, List<Pedido> pedidos) {
        this.pedidos = pedidos;
        this.context = ctx;
    }


    @Override
    public AdapterListaPedidos.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lista_pedidos, parent, false);

        AdapterListaPedidos.ViewHolder vh = new AdapterListaPedidos.ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(AdapterListaPedidos.ViewHolder holder, int position) {

        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");

        String data = dateFormat.format(pedidos.get(position).dataPed);

        holder.txCodigoPedido.setText(String.valueOf(pedidos.get(position).idPed));
        holder.txDtPedido.setText(data);
        holder.txClientePedido.setText(pedidos.get(position).cliPedido);
        holder.txCPFPedido.setText(pedidos.get(position).cpfCli);

    }

    @Override
    public int getItemCount() {
        return this.pedidos != null ? this.pedidos.size() : 0;
    }
}
