package com.android.lojaonline.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.lojaonline.R;
import com.android.lojaonline.activity.CriarContaActivity;
import com.android.lojaonline.activity.HomeActivity;
import com.android.lojaonline.basededados.BaseDeDados;
import com.android.lojaonline.basededados.ClienteDB;
import com.android.lojaonline.domain.Cliente;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText emailLogin;
    private EditText senhaLogin;
    public Boolean logado;
    Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolBar);

        if (toolbar != null) {

            toolbar.setTitle(R.string.title_toolbar_login);
            setSupportActionBar(toolbar);

        }

        emailLogin = findViewById(R.id.edEmailLogin);
        senhaLogin = findViewById(R.id.edSenhaLogin);

        /*
        BaseDeDados banco = new BaseDeDados(this);
        SQLiteDatabase db = banco.getReadableDatabase();
        banco.onUpgrade(db,1,2);
        banco.onCreate(db);
        */

    }


    public void onClickEntrar(View v) {

        String email = emailLogin.getText().toString();
        String password = senhaLogin.getText().toString();

        if (TextUtils.isEmpty(email)) {
            emailLogin.setError(getString(R.string.msg_email_vazio));
            return;
        } else if (!emailValido(email)) {
            emailLogin.setError(getString(R.string.msg_email_invalido));
            return;
        }

        if (TextUtils.isEmpty(password)) {
            senhaLogin.setError(getString(R.string.msg_senha_vazia));
            return;
        }


        logado = logar();

        if (logado){
            Toast.makeText(this, "Bem vindo!", Toast.LENGTH_SHORT).show();
            Intent it = new Intent(this, HomeActivity.class);
            it.putExtra("cliente", cliente);
            startActivity(it);
            finish();
        } else {
            Toast.makeText(this, "Cadastro não localizado!", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean logar() {


        ClienteDB banco = new ClienteDB(this);

        List<Cliente> clienteList = banco.getCliente(emailLogin.getText().toString(), senhaLogin.getText().toString());

        if (clienteList.size() > 0) {
            cliente = clienteList.get(0);
            return cliente._id > 0;
        }

        return false;

    }

    public void onClickCadastrar(View v) {

        Intent it = new Intent(this, CriarContaActivity.class);
        startActivity(it);

    }

    private boolean emailValido(String email) {
        return email.contains("@");
    }


}
