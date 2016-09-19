package com.grupov08.easyfood_domiciliario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PedidosActivity extends AppCompatActivity {

    private String telCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        ArrayList<String> estadoPedido = new ArrayList<String>();
        estadoPedido.add("En cola");
        estadoPedido.add("En preparación");
        estadoPedido.add("En camino");
        Spinner spPedido = (Spinner)findViewById(R.id.spinner_pedido);
        spPedido.setAdapter(new ArrayAdapter(this, android.R.layout.simple_spinner_item, estadoPedido));
        Intent i = getIntent();
        telCliente = i.getStringExtra("telCliente");
        ((TextView)findViewById(R.id.telefono_cliente)).setText(telCliente);
    }

    public void sincronizarConCliente(View v)
    {
        Toast.makeText(PedidosActivity.this, telCliente, Toast.LENGTH_SHORT).show();
    }

    public void enviarMensaje()
    {

        String mensaje = ((EditText)findViewById(R.id.mensaje_cliente_pedido)).getText().toString();
        SmsManager.getDefault().sendTextMessage(mensaje,null,infoDomiciliario,null,null);
    }

    public void finalizarPedido (View v)
    {
        finish();
    }


}
