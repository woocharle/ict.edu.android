package com.ict.ex68_myreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;

// Receiver는 BroadcastReceive를 상속 받는다.
// 메니페스트에 등록한다.

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // intent안에 Bundle 객체 존재함 ( 메세지 전달하는 역할을 함 )
        Bundle bundle = intent.getExtras();

        SmsMessage[] msg = paresSms(bundle);
        if(msg != null && msg.length > 0){
            String sender = msg[0].getOriginatingAddress();
            String contents = msg[0].getMessageBody();

            // 화면에 메세지 보내기 (리시버도 UI가 없다. => MainActivity2.java)
            sendToActivity(context, sender, contents);
        }
    }

    private SmsMessage[] paresSms(Bundle bundle){
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] msg = new SmsMessage[objs.length];
        for (int i = 0; i < msg.length ; i++){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                String format = bundle.getString("format");
                msg[i] = SmsMessage.createFromPdu((byte[])objs[i], format);
            }else{
                msg[i] = SmsMessage.createFromPdu((byte[])objs[i]);
            }
        }
        return msg;
    }

    private void sendToActivity(Context context, String sender, String contents){
        Intent intent = new Intent(context, MainActivity2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|
                    Intent.FLAG_ACTIVITY_SINGLE_TOP|
                    Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("sender", sender);
        intent.putExtra("contents", contents);
        context.startActivity(intent);
    }

}