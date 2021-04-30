package com.example.powerreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.example.powerreceiver.view.CustomReceiver;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private CustomReceiver customReceiver = new CustomReceiver();
    private static final String ACTION_CUSTOM_BROADCAST = "message";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //register receiver
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        intentFilter.addAction(Intent.ACTION_HEADSET_PLUG);
        intentFilter.addAction(Intent.ACTION_HEADSET_PLUG);

        registerReceiver(customReceiver,intentFilter);

        //register custom broadcast
        LocalBroadcastManager.getInstance(this).registerReceiver(customReceiver,new IntentFilter(ACTION_CUSTOM_BROADCAST));
    }

    @Override
    protected void onDestroy() {
        //unregister receiver
        unregisterReceiver(customReceiver);
        super.onDestroy();

        //unregister custom broadcast
        LocalBroadcastManager.getInstance(this).unregisterReceiver(customReceiver);
    }

    public void sendCustomBroadcast(View view) {
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
        Random r = new Random();
        int num = r.nextInt(20);
        customBroadcastIntent.putExtra("number",num);
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
    }
}