package com.grupov08.easyfood_domiciliario;


import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.grupov08.easyfood_domiciliario.mundo.EasyFood;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class MainActivity extends AppCompatActivity {

    public final static String numerotel = "telefonoActual.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.RECEIVE_SMS}, 12);
        }

        String telString = "";
        String nomdomi = "";
        try
        {

            File f =  new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), numerotel);
            FileReader fr;
            fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            telString = br.readLine();
            nomdomi = br.readLine();
            br.close();
        }
        catch (IOException e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        ((EditText)findViewById(R.id.telefono_actual_main)).setText(telString);
        ((EditText)findViewById(R.id.nombre_actual_domi)).setText(nomdomi);
    }

    public void contactarCliente (View v)
    {
        EasyFood.getInstancia().setTel_cliente(((EditText)findViewById(R.id.telefono_cliente)).getText().toString());
        String infoDomiciliario = "Lo que se le mande al Cliente, teléfono Domiciliario, estado del pedido, distancia";
        try
        {
            SmsManager.getDefault().sendTextMessage( ((EditText)findViewById(R.id.telefono_cliente)).getText().toString(),null,infoDomiciliario,null,null);
        }
        catch (Exception e)
        {
            Toast.makeText(MainActivity.this, "El mensaje no pudo ser enviado", Toast.LENGTH_SHORT).show();
        }

        if (!((EditText)findViewById(R.id.telefono_cliente)).getText().toString().equals(""))
        {
            EasyFood.getInstancia().setTel_cliente(((EditText)findViewById(R.id.telefono_cliente)).getText().toString());
            try {
                Intent i = new Intent(this, PedidosActivity.class);
                i.putExtra("telCliente",((EditText)findViewById(R.id.telefono_cliente)).getText().toString());
                startActivity(i);
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "No se ha recibido un pedido", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void cambiarTelefono(View v)
    {
        this.deleteFile(numerotel);
        String str1 = ((EditText)findViewById(R.id.telefono_actual_main)).getText().toString();
        String str2 = ((EditText)findViewById(R.id.nombre_actual_domi)).getText().toString();
        FileOutputStream outputStream;
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), numerotel);
        try
        {
            PrintWriter out = new PrintWriter( file );
            out.println(str1);
            out.println(str2);
            out.close();
        }
        catch (Exception e)
        {
        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }



}
