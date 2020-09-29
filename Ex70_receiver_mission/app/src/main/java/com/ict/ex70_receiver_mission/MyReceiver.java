package com.ict.ex70_receiver_mission;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getAction();
        if (name.equals("Test")){
            String msg = intent.getStringExtra("msg");
            Intent go_intent = new Intent(context, MainActivity.class);
            go_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP
                    |Intent.FLAG_ACTIVITY_CLEAR_TOP);
            go_intent.putExtra("msg", msg);
            context.startActivity(go_intent);

        }

    }


}
