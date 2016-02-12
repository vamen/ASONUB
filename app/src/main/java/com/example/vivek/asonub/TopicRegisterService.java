package com.example.vivek.asonub;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.example.vivek.asonub.Constents.Constents;
import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class TopicRegisterService extends IntentService {
    HttpsURLConnection urlConnection;
    URL url;
    String senderId="179378528909";
    String assignment="-assignment";
    String announcement="-announcement";
    String notes="-notes";
    String[] TOPICS;
    public TopicRegisterService()
    {
        super(TopicRegisterService.class.getName());
    }


    //@Override
    //public int onStartCommand(Intent intent, int flags, int startId) {

      //      return  startId;
    //}

    private void subscribeTopics(String token) throws IOException {
        GcmPubSub pubSub = GcmPubSub.getInstance(getApplicationContext());
        for (String topic : TOPICS) {
            String s=topic.trim()+assignment.trim();
            Log.d(TopicRegisterService.class.getName(),s);
            pubSub.subscribe(token, "/topics/" +s,null);

            Log.d("topics",s);
        }

    }



    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("Intent ","inside on handle");
        Bundle data=intent.getExtras();
        int branch=data.getInt("BRANCH");
        int sem=data.getInt("SEM");
        InstanceID id= InstanceID.getInstance(getApplicationContext());

        String token=getSharedPreferences(MainActivity.PREF_FILE_NAME,MODE_PRIVATE).getString(MainActivity.token, null);


        if(token!=null) {
            TOPICS = Constents.getSubjects(branch, sem);
            try {
                subscribeTopics(token);
            } catch (IOException e) {
                Log.d("some network error", "error");
                e.printStackTrace();
            }

        }
    }
}
