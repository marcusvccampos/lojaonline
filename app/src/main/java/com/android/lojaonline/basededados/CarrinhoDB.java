package com.android.lojaonline.basededados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.lojaonline.domain.Carrinho;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis gustavo on 01/08/2016.
 */

public class CarrinhoDB {

    public SQLiteDatabase db;
    public BaseDeDados banco;

    public CarrinhoDB(Context context){

        banco = new BaseDeDados(context);

    }


    public long saveProdutoCarrinho(Carrinho carrinho) {

        long IdProd;

        db = banco.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();

            values.put("idProd", carrinho.idProd);
            values.put("Qt", carrinho.qt);
            values.put("valor", carrinho.valor);

            IdProd = db.insert("carrinho", "", values);

            return IdProd;

        }finally {
            db.close();
        }


    }


    public List<Carrinho> getProdutosCarrinho() {

        db = banco.getWritableDatabase();

        List<Carrinho> carrinhoList = new ArrayList<>();

        try {

            Cursor c = db.rawQuery   (" select c.IdProd, p.NomeProd, c.Qt, c.valor from Carrinho c, Produto p where c.IdProd = p._id ", null);

            //c.moveToFirst();
            while (c.moveToNext()){

                Carrinho car = new Carrinho();

                car.idProd = c.getLong(c.getColumnIndex("IdProd"));
                car.nomeProd = c.getString(c.getColumnIndex("NomeProd"));
                car.qt = c.getLong(c.getColumnIndex("Qt"));
                car.valor = c.getFloat(c.getColumnIndex("valor"));

                carrinhoList.add(car);
            }


            return carrinhoList;

        } finally {
            db.close();
        }
    }


    public int limpaCarrinho() {

        db = banco.getWritableDatabase();

        try {
            int count = db.delete("carrinho", null, null);
            return count;
        } finally {
            db.close();
        }
    }



    public float updateQdeCarrinho(Carrinho carrinho) {

        db = banco.getWritableDatabase();

        try {

            ContentValues values = new ContentValues();

            values.put("idProd", carrinho.idProd);
            values.put("Qt", carrinho.qt);
            values.put("valor", carrinho.valor);

            String _id = String.valueOf(carrinho.idProd);
            String[] whereArgs = new String[]{_id};
            int count = db.update("carrinho", values, "idProd=?", whereArgs);
            return count;

        } finally {
            db.close();
        }
    }


    public int deleteItemCarrinho(String IdProd) {

        db = banco.getWritableDatabase();

        try {
            int count = db.delete("carrinho", "idProd=?", new String[]{IdProd});
            return count;
        } finally {
            db.close();
        }
    }


    public float getValorTotalCarrinho() {

        db = banco.getWritableDatabase();

        try {

            Cursor c = db.rawQuery(" select  sum( Qt * valor) as total from Carrinho ", null);

            //c.moveToFirst();
            while (c.moveToNext()) {

                return c.getFloat(c.getColumnIndex("total"));

            }

            return 0;

        } finally {
            db.close();
        }
    }

}
