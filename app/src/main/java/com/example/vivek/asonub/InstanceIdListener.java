package com.example.vivek.asonub;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by Vivek Balachandran on 1/22/2016.
 */
public class InstanceIdListener extends InstanceIDListenerService {
                String TAG=InstanceIdListener.class.getSimpleName();
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        Log.d(TAG,"Token Changed");
        Intent RegistrationIntent=new Intent(InstanceIdListener.this,RegistrationTokenService.class);
        startService(RegistrationIntent);
    }
}
