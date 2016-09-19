package com.grupov08.easyfood_domiciliario.mundo;

import java.util.ArrayList;

/**
 * Created by concol on 19/09/2016.
 */
public class EasyFood {

    private static EasyFood instancia;

    private String local;
    private String pedido;
    private String tel_cliente;

    public EasyFood() {}

    public static EasyFood getInstancia() {
        if (instancia == null)
            return instancia = new EasyFood();
        else
            return instancia;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getPedido() {
        StringBuilder sb = new StringBuilder();
        String[] st = pedido.split("_");
        for (int i = 0; i < st.length - 1; i++) {
            sb.append(st[i]);
            sb.append(", ");
        }

        sb.append(st[st.length-1]);
        
        return sb.toString();
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getTel_cliente() {
        return tel_cliente;
    }

    public void setTel_cliente(String tel_cliente) {
        this.tel_cliente = tel_cliente;
    }
}
