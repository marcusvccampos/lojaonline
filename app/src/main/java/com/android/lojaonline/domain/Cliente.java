package com.android.lojaonline.domain;

import java.io.Serializable;

/**
 * Created by luis gustavo on 01/08/2016.
 */

public class Cliente implements Serializable {

    public long _id;
    public String cliente;
    public String cpf;
    public String endereco;
    public String estado;
    public String municipio;
    public String telefone;
    public String email;
    public String senha;

    @Override
    public String toString() {
        return "Cliente = "+cliente ;
    }
}
