package com.android.lojaonline.domain;

/**
 * Created by luis gustavo on 01/08/2016.
 */

public class Categoria {

    public long id;
    public String descricao;

    @Override
    public String toString() {
        return "Categoria = " + descricao;
    }
}
