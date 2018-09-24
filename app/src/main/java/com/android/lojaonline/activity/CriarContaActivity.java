package com.android.lojaonline.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.lojaonline.R;
import com.android.lojaonline.basededados.ClienteDB;
import com.android.lojaonline.domain.Cliente;

public class CriarContaActivity extends AppCompatActivity {

    public EditText cliente;
    public EditText cpf;
    public EditText endereco;
    public Spinner estado;
    public EditText municipio;
    public EditText telefone;
    public EditText email;
    public EditText senha;
    public EditText confirmarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);

        Toolbar toolbar = findViewById(R.id.toolBar);

        if (toolbar != null) {

            toolbar.setTitle(R.string.title_toolbar_criar_conta);
            setSupportActionBar(toolbar);

        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cliente = findViewById(R.id.edNome);
        cpf = findViewById(R.id.edCPF);
        endereco = findViewById(R.id.edEndereco);
        estado = findViewById(R.id.spnEstado);
        municipio = findViewById(R.id.edMunicipio);
        telefone = findViewById(R.id.edTelefone);
        email = findViewById(R.id.edEmail);
        senha = findViewById(R.id.edSenha);
        confirmarSenha = findViewById(R.id.edConfirmarSenha);

    }

    public void onClickCadastrar(View v) {

        if (TextUtils.isEmpty(cliente.getText().toString())) {
            cliente.setError(getText(R.string.msg_nome_vazio));
            return;
        }

        if (TextUtils.isEmpty(cpf.getText().toString())) {
            cpf.setError(getText(R.string.msg_cpf_vazio));
            return;
        }

        if (TextUtils.isEmpty(endereco.getText().toString())) {
            endereco.setError(getText(R.string.msg_endereco_vazio));
            return;
        }

        if (estado.getSelectedItemPosition() == 0) {
            TextView errorText = (TextView) estado.getSelectedView();
            errorText.setTextColor(Color.RED);
            return;
        }

        if (TextUtils.isEmpty(municipio.getText().toString())) {
            municipio.setError(getText(R.string.msg_municipio_vazio));
            return;
        }

        if (TextUtils.isEmpty(telefone.getText().toString())) {
            telefone.setError(getText(R.string.msg_telefone_vazio));
            return;
        }

        if (TextUtils.isEmpty(email.getText().toString())) {
            email.setError(getString(R.string.msg_email_vazio));
            return;
        } else if (!emailValido(email.getText().toString())) {
            email.setError(getString(R.string.msg_email_invalido));
            return;
        }

        if (TextUtils.isEmpty(senha.getText().toString())) {
            senha.setError(getString(R.string.msg_senha_vazia));
            return;
        } else if (!senhaValida(senha.getText().toString())) {
            senha.setError(getString(R.string.msg_senha_invalida));
            return;
        }

        if (TextUtils.isEmpty(confirmarSenha.getText().toString())) {
            confirmarSenha.setError(getString(R.string.msg_confirmar_senha));
            return;
        } else if (!confirmarSenha(senha.getText().toString(), confirmarSenha.getText().toString())) {
            confirmarSenha.setError(getString(R.string.msg_validar_confirmar_senha));
            return;
        }

        Cliente cli = new Cliente();

        cli.cliente = cliente.getText().toString();
        cli.cpf = cpf.getText().toString();
        cli.endereco = endereco.getText().toString();
        cli.estado = estado.getSelectedItem().toString();
        cli.municipio = municipio.getText().toString();
        cli.telefone = telefone.getText().toString();
        cli.email = email.getText().toString();
        cli.senha = senha.getText().toString();


        ClienteDB db = new ClienteDB(this);
        long id = db.saveCliente(cli);

        if (id > 0) {
            Toast.makeText(this, "Cliente Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

            finish();
        }

    }

    private boolean emailValido(String email) {
        return email.contains("@");
    }

    private boolean senhaValida(String senha) {
        return senha.length() > 4;
    }

    private boolean confirmarSenha(String senha, String confirmarSenha) {
        return senha.equals(confirmarSenha);
    }
}


