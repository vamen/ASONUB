package com.example.vivek.asonub;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by Vivek Balachandran on 1/22/2016.
 */
public class RegistrationTokenService extends IntentService {

     String token;
     String TAG=RegistrationTokenService.class.getSimpleName();
     String senderId="179378528909";
     public RegistrationTokenService()
     {
        super(RegistrationTokenService.class.getName());

     }
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    public RegistrationTokenService(String name) {
        super(name);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        InstanceID id=InstanceID.getInstance(getApplicationContext());

        try {
            Log.d(TAG+"ID", id.getId());
            // request token that will be used by the server to send push notifications
            token = id.getToken(senderId, GoogleCloudMessaging.INSTANCE_ID_SCOPE,null);
            Log.e(TAG + "token", "GCM Registration Token:\n " + token + "\n");
            setSharedPref(token);
            sendRegistrationToServer(token);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void setSharedPref(String token) {
        SharedPreferences pref=getSharedPreferences(MainActivity.PREF_FILE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        editor.putString(MainActivity.token,token);
        editor.apply();
    }

    private void sendRegistrationToServer(String token) {
      stopSelf();
    }
}
