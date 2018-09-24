package com.android.lojaonline.basededados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.lojaonline.domain.Produto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis gustavo on 01/08/2016.
 */

public class ProdutosDB {

    public SQLiteDatabase db;
    public BaseDeDados banco;

    public ProdutosDB(Context context) {

        banco = new BaseDeDados(context);

    }

    public long saveProduto(Produto produto) {

        long id = produto._id;

        db = banco.getWritableDatabase();

        try {

            ContentValues values = new ContentValues();

            values.put("NomeProd", produto.produto);
            values.put("PrecoProd", produto.preco);
            values.put("QtdEstqProd", produto.qdeEstoque);
            values.put("IdCateg", produto.codCategoria);

            if (id != 0) {
                String _id = String.valueOf(produto._id);
                String[] whereArgs = new String[]{_id};
                int count = db.update("produto", values, "_id=?", whereArgs);
                return count;
            } else {
                id = db.insert("produto", "", values);
                return id;
            }


        } finally {
            db.close();
        }

    }


    public List<Produto> getProdutos() {
        db = banco.getWritableDatabase();

        List<Produto> lista = new ArrayList<>();

        Cursor c = db.rawQuery("select _id, NomeProd, QtdEstqProd, PrecoProd, IdCateg from produto order by NomeProd", null);
        //Cursor c = db.query("produto", null, null, null, null, null, null, null);

        while (c.moveToNext()) {
            Produto p = new Produto();


            p._id = c.getLong(c.getColumnIndex("_id"));
            p.produto = c.getString(c.getColumnIndex("NomeProd"));
            p.qdeEstoque = c.getLong(c.getColumnIndex("QtdEstqProd"));
            p.preco = c.getFloat((c.getColumnIndex("PrecoProd")));
            p.codCategoria = c.getLong(c.getColumnIndex("IdCateg"));

            lista.add(p);

        }


        return lista;
    }

    public List<Produto> getProdutosPorCategoria(String idCategoria) {

        db = banco.getWritableDatabase();

        List<Produto> produtoList = new ArrayList<>();

        String[] whereArgs = new String[]{idCategoria};

        try {

            Cursor c = db.query("produto", null, "IdCateg=?", whereArgs, null, null, null);

            //c.moveToFirst();
            while (c.moveToNext()) {

                Produto produto = new Produto();

                produto._id = c.getLong(c.getColumnIndex("_id"));
                produto.produto = c.getString(c.getColumnIndex("NomeProd"));
                produto.qdeEstoque = c.getLong(c.getColumnIndex("QtdEstqProd"));
                produto.preco = c.getFloat(c.getColumnIndex("PrecoProd"));
                produto.codCategoria = c.getLong(c.getColumnIndex("IdCateg"));

                produtoList.add(produto);
            }


            return produtoList;

        } finally {
            db.close();
        }
    }

}
