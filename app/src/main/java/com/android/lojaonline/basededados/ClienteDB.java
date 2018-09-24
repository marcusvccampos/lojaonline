package com.android.lojaonline.basededados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.lojaonline.domain.Cliente;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis gustavo on 01/08/2016.
 */

public class ClienteDB {

    public SQLiteDatabase db;
    public BaseDeDados banco;


    public ClienteDB(Context context){

        banco = new BaseDeDados(context);

    }

    public long saveCliente(Cliente cliente) {

        long id = cliente._id;

        db = banco.getWritableDatabase();

        try {

            ContentValues values = new ContentValues();

            values.put("NomeCli", cliente.cliente);
            values.put("CpfCli", cliente.cpf);
            values.put("EnderecoCli", cliente.endereco);
            values.put("EstadoCli", cliente.estado);
            values.put("MunicipioCli", cliente.municipio);
            values.put("TelefoneCli", cliente.telefone);
            values.put("EmailCli", cliente.email);
            values.put("SenhaCli", cliente.senha);

            if (id != 0) {
                String _id = String.valueOf(cliente._id);
                String[] whereArgs = new String[]{_id};
                int count = db.update("cliente", values, "_id=?", whereArgs);
                return count;
            } else {
                id = db.insert("cliente", "", values);
                return id;
            }


        } finally {
            db.close();
        }

    }

    public List<Cliente> getCliente(String email, String senha) {

         db = banco.getWritableDatabase();

        String[] whereArgs = new String[]{email, senha};

        List<Cliente> clienteList = new ArrayList<>();

        try {

            Cursor c = db.query("cliente", null, "EmailCli=? and SenhaCli=?", whereArgs, null, null, null);

            while (c.moveToNext()){

                Cliente cliente = new Cliente();

                cliente._id = c.getLong(c.getColumnIndex("_id"));
                cliente.cliente = c.getString(c.getColumnIndex("NomeCli"));
                cliente.cpf = c.getString(c.getColumnIndex("CpfCli"));
                cliente.endereco = c.getString(c.getColumnIndex("EnderecoCli"));
                cliente.estado = c.getString(c.getColumnIndex("EstadoCli"));
                cliente.municipio = c.getString(c.getColumnIndex("MunicipioCli"));
                cliente.telefone = c.getString(c.getColumnIndex("TelefoneCli"));
                cliente.email = c.getString(c.getColumnIndex("EmailCli"));
                cliente.senha = c.getString(c.getColumnIndex("SenhaCli"));

                clienteList.add(cliente);
            }


            return clienteList;

        } finally {
            db.close();
        }
    }
}
