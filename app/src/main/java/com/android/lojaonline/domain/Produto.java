package com.android.lojaonline.domain;


import java.io.Serializable;

/**
 * Created by luis gustavo on 01/08/2016.
 */

public class Produto implements Serializable {

    public long _id;
    public String produto;
    public long qdeEstoque;
    public Float preco;
    public long codCategoria;

    @Override
    public String toString() {
        return "Produto = " + produto;
    }
}
