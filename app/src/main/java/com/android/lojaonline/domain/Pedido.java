package com.android.lojaonline.domain;

import java.util.Date;

/**
 * Created by luis gustavo on 01/08/2016.
 */

public class Pedido {

    public long idPed;
    public String cpfCli;
    public String cliPedido;
    public Date dataPed;
    public Float valorPed;

    @Override
    public String toString() {
        return "Numped: " + idPed;
    }
}
