package com.example.vivek.asonub;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by Vivek Balachandran on 1/22/2016.
 */
public class myGcmListener extends GcmListenerService {
    @Override
    public void onMessageSent(String msgId) {
        super.onMessageSent(msgId);
    }

    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);
        Log.d("Message", data.get("message").toString());
    }
}
