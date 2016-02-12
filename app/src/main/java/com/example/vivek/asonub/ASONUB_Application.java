package com.example.vivek.asonub;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;

/**
 * Created by Vivek Balachandran on 1/27/2016.
 */
public class ASONUB_Application extends Application {
    public static String RegistrationToken;
    public static String parseAid="4j0awpqIk2LGXkjsQpz0nDzVqCvtQTiXmsJBfFDK";
    public static String parseCk="4qjerGD5QWnBj4Y8xrVadbishgneud2cEMk3B9ae";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("ASONUB","oncreate");
        Parse.enableLocalDatastore(getApplicationContext());
        Parse.initialize(getApplicationContext(),parseAid,parseCk);


    }
}
