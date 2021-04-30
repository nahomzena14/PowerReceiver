package com.example.powerreceiver.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    private static final String ACTION_CUSTOM_BROADCAST = "message";

    @Override
    public void onReceive(Context context, Intent intent) {
        //get action
        String intentAction = intent.getAction();
        int num = intent.getIntExtra("number",1);
        num *=num;

        //if successful
        if(intentAction !=null){
            String message = "unknown intent action";
            switch (intentAction){
                case Intent.ACTION_POWER_CONNECTED:
                    message = "POWER IS CONNECTED";
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    message = "POWER IS DISCONNECTED";
                    break;
                case ACTION_CUSTOM_BROADCAST:
                    message = "CUSTOM BROADCAST RECEIVED \n"+num;
                    break;
                case Intent.ACTION_HEADSET_PLUG:
                    message = "HEADSET PLUGGED IN";
            }
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
        }
    }
}