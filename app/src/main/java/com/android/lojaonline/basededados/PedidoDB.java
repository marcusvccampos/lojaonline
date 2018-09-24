package com.android.lojaonline.basededados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.android.lojaonline.domain.Carrinho;
import com.android.lojaonline.domain.ItensPedido;
import com.android.lojaonline.domain.Pedido;
import com.android.lojaonline.domain.Produto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by luis gustavo on 01/08/2016.
 */

public class PedidoDB {

    public SQLiteDatabase db;
    public BaseDeDados banco;
    public long numPed = 0;


    public PedidoDB(Context context) {

        banco = new BaseDeDados(context);

    }


    public long getProxNumPed() {

        long proxNumPed = 0;

        try {
            //db = banco.getWritableDatabase();

            Cursor c = db.rawQuery("select ifnull((select max(idped) from pedido),0) + 1 as proxnumped", null);

            if (c != null && c.moveToNext()) {
                proxNumPed = c.getLong(c.getColumnIndex("proxnumped"));
            }

        } catch (Exception ex) {
            Log.e("getProxNumPed", ex.getMessage());
        }


        return proxNumPed;

    }


    public long savePedido(Pedido pedido) {

        db = banco.getWritableDatabase();

        numPed = getProxNumPed();

        pedido.idPed = numPed;

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String dtPed = df.format(pedido.dataPed);

        try {

            ContentValues values = new ContentValues();

            values.put("IdPed", pedido.idPed);
            values.put("CpfCli", pedido.cpfCli);
            values.put("DataPed", dtPed);
            values.put("ValorPed", pedido.valorPed);

            return db.insert("pedido", "", values);

        } finally {
            db.close();
        }

    }


    public long saveItensPedido(List<Carrinho> carrinho) {

        db = banco.getWritableDatabase();

        long id = 0;


        for (int i = 0; i < carrinho.size(); i++) {


            ContentValues values = new ContentValues();

            values.put("IdPed", numPed);
            values.put("IdProd", carrinho.get(i).idProd);
            values.put("QtdItemPed", carrinho.get(i).qt);

            id = db.insert("Item_Pedido", "", values);

            updateEstoque(carrinho.get(i));
        }


        return id;

    }


    public long savePagamento(Pedido pedido, String numCartao) {

        db = banco.getWritableDatabase();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        String dtPed = df.format(pedido.dataPed);

        try {

            ContentValues values = new ContentValues();

            values.put("IdPed", numPed);
            values.put("DataPag", dtPed);
            values.put("ValorPag", pedido.valorPed);
            values.put("NumCartaoPag", numCartao);

            return db.insert("Pagamento", "", values);

        } finally {
            db.close();
        }

    }


    public void updateEstoque(Carrinho carrinho) {

        //db = banco.getWritableDatabase();

        String sql = "update Produto set QtdEstqProd = QtdEstqProd - ? where _id = ?";

        try {
            db.execSQL(sql, new String[]{String.valueOf(carrinho.qt), String.valueOf(carrinho.idProd)});
        } finally {
            //db.close();
        }

    }


    public List<Pedido> listaPedidos(){

        db = banco.getWritableDatabase();

        List<Pedido> pedidosList = new ArrayList<>();


        try {

            Cursor c = db.rawQuery("select p.IdPed, p.CpfCli, c.NomeCli, p.DataPed from pedido p, cliente c where p.CpfCli = c.CpfCli", null);

            //c.moveToFirst();
            while (c.moveToNext()){

                Pedido pedido = new Pedido();

                pedido.idPed = c.getLong(c.getColumnIndex("IdPed"));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
                Date dtPedido = dateFormat.parse(c.getString(c.getColumnIndex("DataPed")));
                pedido.dataPed = dtPedido;
                pedido.cliPedido = c.getString(c.getColumnIndex("NomeCli"));
                pedido.cpfCli = c.getString(c.getColumnIndex("CpfCli"));

                pedidosList.add(pedido);
            }


            return pedidosList;


        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        } finally {
            db.close();
        }

    }
}
