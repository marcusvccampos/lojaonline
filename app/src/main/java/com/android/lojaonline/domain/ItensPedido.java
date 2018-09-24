package com.android.lojaonline.domain;

/**
 * Created by luis gustavo on 01/08/2016.
 */

public class ItensPedido {

    public long idPed;
    public long idProd;
    public long qdeItemPed;

    @Override
    public String toString() {
        return "Pedido: " + idPed;
    }
}
