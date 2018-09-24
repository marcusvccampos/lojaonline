package com.android.lojaonline.basededados;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.lojaonline.domain.Categoria;
import com.android.lojaonline.domain.Produto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis gustavo on 01/08/2016.
 */

public class CategoriaDB {

    public SQLiteDatabase db;
    public BaseDeDados banco;

    public CategoriaDB(Context context) {

        banco = new BaseDeDados(context);

    }

    public List<Categoria> getCategoria() {
        db = banco.getWritableDatabase();

        List<Categoria> lista = new ArrayList<>();

        Cursor c = db.query("categoria", null, null, null, null, null, null);

        while (c.moveToNext()) {
            Categoria cat = new Categoria();


            cat.id = c.getLong(c.getColumnIndex("_id"));
            cat.descricao = c.getString(c.getColumnIndex("NomeCateg"));

            lista.add(cat);

        }


        return lista;
    }

}

