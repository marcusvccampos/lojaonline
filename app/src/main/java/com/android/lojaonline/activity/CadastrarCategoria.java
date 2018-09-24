package com.android.lojaonline.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;;

import com.android.lojaonline.R;

public class CadastrarCategoria extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_categoria);

        toolbar = findViewById(R.id.toolBar);

        if (toolbar != null) {

            toolbar.setTitle(R.string.text_lista_categoria_cadastro);
            setSupportActionBar(toolbar);

        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
