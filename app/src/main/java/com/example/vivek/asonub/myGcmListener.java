package com.example.vivek.asonub;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;
import com.malinskiy.materialicons.IconDrawable;
import com.malinskiy.materialicons.Iconify;

/**
 * Created by Vivek Balachandran on 1/22/2016.
 */
public class myGcmListener extends GcmListenerService {
    int mNotificationId=200;
//    IconDrawable iconDrawable=new IconDrawable(getApplicationContext(), Iconify.IconValue.zmdi_comment).colorRes(android.R.color.black);
    @Override
    public void onMessageSent(String msgId) {
        super.onMessageSent(msgId);
    }

    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);
        NotificationCompat.Builder nBuilder=new NotificationCompat.Builder(this);
        nBuilder.setSmallIcon(R.mipmap.ic_launcher);
        nBuilder.setContentTitle("ASONUB");
        nBuilder.setContentText(data.getString("message"));
        NotificationManager manager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(mNotificationId,nBuilder.build());
        Log.d("Message", data.get("message").toString());
    }
}
