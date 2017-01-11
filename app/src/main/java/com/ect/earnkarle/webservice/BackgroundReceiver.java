package com.ect.earnkarle.webservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.ect.earnkarle.AppCheckService;

/**
 * Created by ABC on 4/8/2016.
 */
public class BackgroundReceiver extends BroadcastReceiver {
    public static String BROADCAST_ACTION = "com.ect.earnkarle.ACTION_BACKGROUND";
    public static String CATEGORY = "com.ect.earnkarle.CATEGORY_BACKGROUND";

    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("call BroadcastReceiver");
        Intent downloadIntent = new Intent(context, AppCheckService.class);
        context.startService(downloadIntent);
        Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();
        System.out.println("call AppCheckService");

    }
}