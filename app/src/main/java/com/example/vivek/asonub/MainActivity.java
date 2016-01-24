package com.example.vivek.asonub;

import android.support.v7.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements SplashTransfer{
    public static final String PREF_FILE_NAME="tokenFile";
    public static final String token="Token";
    Handler handler=new Handler();
    Runnable mRunnable=new Runnable() {
        @Override
        public void run() {
        Intent cardIntent=new Intent(MainActivity.this,CardActivity.class);
            startActivity(cardIntent);

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Parse.enableLocalDatastore(this);
        //Parse.initialize(this);
            if(!isTokenExist()) {
                ConnectivityManager connectivityManager= (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo info=connectivityManager.getActiveNetworkInfo();
                if(info.isConnectedOrConnecting()) {
                    Intent RegistrationIntent = new Intent(MainActivity.this, RegistrationTokenService.class);
                    startService(RegistrationIntent);
                    handler.postDelayed(mRunnable,3000);

                }
                else
                {
                    AlertDialog.Builder builder=new AlertDialog.Builder(this);
                    builder.setTitle("No Network");
                    builder.setMessage("check your connectivity and Try again ");
                    builder.setCancelable(false);

                    builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                         dialog.dismiss();
                         finish();
                        }
                    });
                    AlertDialog dialog=builder.create();
                    dialog.show();
                    Button b=dialog.getButton(DialogInterface.BUTTON_NEUTRAL);

                //    if(b!=null) {
   //                  Log.e(getClass().getName(),"button");
   //                  b.setBackgroundColor(getResources().getColor(R.color.colorAccent));
   //                  b.setTextColor(getResources().getColor(R.color.common_action_bar_splitter));
     //               }


                }
            }
        else
            {
                handler.postDelayed(mRunnable, 3000);
            }

    }

    private boolean isTokenExist() {
        SharedPreferences preferences=getSharedPreferences(PREF_FILE_NAME,MODE_PRIVATE);
        if(preferences.getString(token,null)==null)
            return false;
        else
            return true;
    }

    @Override
    public void changeFragment() {

    }
}
