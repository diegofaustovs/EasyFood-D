package com.grupov08.easyfood_domiciliario;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.grupov08.easyfood_domiciliario.mundo.EasyFood;

/**
 * Created by concol on 17/09/2016.
 */
public class SMSBroadcastReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Bundle intentExtras = intent.getExtras();
        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get("pdus");
            String smsMessageStr = "";
            for (int i = 0; i < sms.length; ++i) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                String smsBody = smsMessage.getMessageBody().toString();
                String address = smsMessage.getOriginatingAddress();

                if (smsBody.startsWith("*EF:P:"))
                {
                    smsBody = smsBody.replace("*EF:P:","");
                    String smsIn[] = smsBody.split("__");
                    EasyFood.getInstancia().setLocal(smsIn[0]);
                    EasyFood.getInstancia().setPedido(smsIn[1]);
                    Toast.makeText(context, smsBody, Toast.LENGTH_SHORT).show();
                }

                else if (smsBody.startsWith("*EF:U:"))
                {
                    SmsManager.getDefault().sendTextMessage(EasyFood.getInstancia().getTel_cliente(), null,"*EF:U:Descripción__4.12345 72.12345", null, null);
                }
            }
        }
    }
}
