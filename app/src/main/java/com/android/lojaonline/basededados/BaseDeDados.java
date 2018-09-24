package com.android.lojaonline.basededados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.w3c.dom.Text;

/**
 * Created by luis gustavo on 01/08/2016.
 */

public class BaseDeDados extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "lojaonline.sqlite";
    private static final int VERSAO_BANCO = 1;


    public BaseDeDados(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /*
        sqLiteDatabase.execSQL("create table if not exists cliente" +
                "(_id integer primary key autoincrement, cliente text, cpf text, endereco text, " +
                " estado text, municipio text, telefone text, email text, senha text);");
        */


        sqLiteDatabase.execSQL("CREATE TABLE  if not exists Cliente( _id Integer Primary Key  NOT NULL," +
                "CpfCli Text, NomeCli Text NOT NULL ,EnderecoCli Text  NOT NULL, " +
                "MunicipioCli Text  NOT NULL ,EstadoCli Text  NOT NULL ," +
                "TelefoneCli Text  NOT NULL , EmailCli Text  NOT NULL ,SenhaCli Text  NOT NULL);");


        sqLiteDatabase.execSQL("create table if not exists Produto" +
                "(_id integer primary key autoincrement, NomeProd text, QtdEstqProd integer, PrecoProd NUMBER(10, 2), IdCateg long,  FOREIGN KEY(IdCateg) REFERENCES Categoria(_id));");

        sqLiteDatabase.execSQL("CREATE TABLE if not exists Historico_Estoque(IdProd INTEGER NOT NULL, DataHistEstq DATE NOT NULL, QtdProdHistEstq INTEGER NOT NULL, EntradaSaida TEXT NOT NULL, FOREIGN KEY(IdProd) REFERENCES Produto(_id)	);");

        sqLiteDatabase.execSQL("CREATE TRIGGER if not exists cadastra_historico_estoque_saida UPDATE OF QtdEstqProd ON Produto " +
                "WHEN OLD.QtdEstqProd > NEW.QtdEstqProd " +
                "BEGIN " +
                "INSERT INTO Historico_Estoque(IdProd,DataHistEstq,QtdProdHistEstq,EntradaSaida)VALUES(OLD._id,DATETIME('NOW','LOCALTIME'),NEW.QtdEstqProd,'Saida' ); " +
                "END;");

        sqLiteDatabase.execSQL("CREATE TRIGGER if not exists cadastra_historico_estoque_entrada UPDATE OF QtdEstqProd ON Produto " +
                "WHEN OLD.QtdEstqProd < NEW.QtdEstqProd " +
                "BEGIN " +
                "INSERT INTO Historico_Estoque(IdProd,DataHistEstq,QtdProdHistEstq,EntradaSaida)VALUES(OLD._id,DATETIME('NOW','LOCALTIME'),NEW.QtdEstqProd,'Entrada' ); " +
                "END;");

        sqLiteDatabase.execSQL("CREATE TABLE if not exists Categoria ( _id Integer PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "NomeCateg TEXT NOT NULL);");


        sqLiteDatabase.execSQL("CREATE TABLE if not exists Pedido( IdPed INTEGER PRIMARY KEY  NOT NULL, CpfCli TEXT  NOT NULL, DataPed DATETIME  NOT NULL, ValorPed NUMBER(10, 2)  NOT NULL  );");


        sqLiteDatabase.execSQL("CREATE TABLE if not exists Item_Pedido( IdPed INTEGER NOT NULL, IdProd INTEGER NOT NULL, QtdItemPed INTEGER NOT NULL, FOREIGN KEY(IdPed) REFERENCES Pedido(IdPed), FOREIGN KEY(IdProd) REFERENCES Produto(_id)	);");


        sqLiteDatabase.execSQL("CREATE TABLE if not exists Pagamento( _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, IdPed INTEGER NOT NULL, DataPag DATE NOT NULL, ValorPag NUMBER(10, 2) NOT NULL, NumCartaoPag TEXTO NOT NULL, FOREIGN KEY(IdPed) REFERENCES Pedido(IdPed)); ");


        sqLiteDatabase.execSQL("CREATE TABLE if not exists Nota_Fiscal ( _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, IdPed INTEGER NOT NULL, DataNF DATE NOT NULL, FOREIGN KEY(IdPed) REFERENCES Pedido(IdPed));");

        sqLiteDatabase.execSQL("CREATE TABLE if not exists Carrinho ( _id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, IdProd INTEGER NOT NULL, Qt INTEGER NOT NULL, valor NUMBER(10, 2), FOREIGN KEY(IdProd) REFERENCES Produto(_id));");



        sqLiteDatabase.execSQL("insert into Produto values ('1','Sapatênis Calvin Klein','100','174.9','1');");
        sqLiteDatabase.execSQL("insert into Produto values ('2','Tênis Mizuno Wing N Cinza','50','159.89','1');");
        sqLiteDatabase.execSQL("insert into Produto values ('3','Blusa Maculina','300','29.9','2');");
        sqLiteDatabase.execSQL("insert into Produto values ('4','Bolsa Feminina','10','299.9','3');");
        sqLiteDatabase.execSQL("insert into Produto values ('5','Chuteira','30','199.9','4');");
        sqLiteDatabase.execSQL("insert into Produto values ('6','Perfume Masculino','45','30.9','5');");
        sqLiteDatabase.execSQL("insert into Produto values ('7','Perfume Feminino','60','399.9','5');");
        sqLiteDatabase.execSQL("insert into Produto values ('8','Perfume EGEO','3','65','5');");



        sqLiteDatabase.execSQL("insert into Categoria values ('1','Calçados');");
        sqLiteDatabase.execSQL("insert into Categoria values ('2','Roupas');");
        sqLiteDatabase.execSQL("insert into Categoria values ('3','Bolsas e Acessórios');");
        sqLiteDatabase.execSQL("insert into Categoria values ('4','Esporte');");
        sqLiteDatabase.execSQL("insert into Categoria values ('5','Perfume e Beleza');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Cliente");

        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Categoria");

    }
}
