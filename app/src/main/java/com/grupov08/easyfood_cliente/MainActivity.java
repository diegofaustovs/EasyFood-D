package com.grupov08.easyfood_cliente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void realizarPedido (View v)
    {
        Toast.makeText(MainActivity.this, "Btn Realizar Pedido", Toast.LENGTH_SHORT).show();
        try
        {
            SmsManager.getDefault().sendTextMessage("123456",null,"Prueba Mensaje",null,null);
        }
        catch (Exception e)
        {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void pedidosAnteriores (View v)
    {
        Toast.makeText(MainActivity.this, "Btn Pedidos Anteriores", Toast.LENGTH_SHORT).show();
    }

    public void iniciarSesion (View v)
    {
        Toast.makeText(MainActivity.this, "Btn Iniciar Sesión", Toast.LENGTH_SHORT).show();
    }
}
