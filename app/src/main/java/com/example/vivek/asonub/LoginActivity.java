package com.example.vivek.asonub;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vivek.asonub.Constents.Constents;
import com.example.vivek.asonub.Fragments.Login;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout logIns;
    Button logIn;
    Button signUp;
    TextView or;
    boolean flag;
    EditText userName;
    EditText suserName;
    EditText password;
    EditText spassword;
    int sem;
    int branch;
    NetworkInfo info;
    ConnectivityManager connectivityManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);

        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);
        logIns = (LinearLayout) findViewById(R.id.LoginButtons);
        logIn = (Button) findViewById(R.id.LoginButton);
        logIn.setTextColor(getResources().getColor(R.color.silver));
        signUp = (Button) findViewById(R.id.SignUpButton);
        signUp.setTextColor(getResources().getColor(R.color.silver));
        logIn.setOnClickListener(this);
        signUp.setOnClickListener(this);
        or= (TextView) findViewById(R.id.orText);
    }

    @Override
    public void onClick(View v) {
        flag=true;
        switch (v.getId()) {

            case R.id.LoginButton:
                logIns.removeView(findViewById(R.id.SignUpButton));
                logIns.removeView(findViewById(R.id.orText));
                // logIns.removeAllViews();
                logIn.setId(R.id.login);
                userName = new EditText(this);
                userName.setHint("User Name");
                userName.setHintTextColor(getResources().getColor(R.color.silver));
                userName.setTextColor(getResources().getColor(R.color.Black));
                logIns.addView(userName, 0);
                DisplayMetrics dm = userName.getResources().getDisplayMetrics();
                LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, convertDpToPx(80, dm));
                userName.setLayoutParams(layoutParams1);
                password = new EditText(this);
                password.setHint("password");
                password.setHintTextColor(getResources().getColor(R.color.silver));
                password.setTextColor(getResources().getColor(R.color.Black));
                password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                logIns.addView(password, 1);
                DisplayMetrics pdm = password.getResources().getDisplayMetrics();
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, convertDpToPx(80, pdm));
                password.setLayoutParams(layoutParams2);
                //Button login = new Button(this);
                //login.setText("Log In");
                //login.setTextColor(getResources().getColor(R.color.silver));
                //logIns.addView(login, 2);
                //DisplayMetrics bdm = login.getResources().getDisplayMetrics();
                //LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, convertDpToPx(80, bdm));
                //password.setLayoutParams(layoutParams3);

                break;
            case R.id.SignUpButton:
                logIns.removeAllViews();
                Login loginFragment=new Login();
                FragmentManager fragmentManager=getSupportFragmentManager();
               FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.LoginButtons,loginFragment);
                fragmentTransaction.commit();
              /*  logIns.removeView(findViewById(R.id.LoginButton));
                logIns.removeView(findViewById(R.id.orText));
                signUp.setId(R.id.signup);
                suserName = new EditText(this);
                suserName.setHint("User Name");
                suserName.setHintTextColor(getResources().getColor(R.color.silver));
                logIns.addView(suserName, 0);
                DisplayMetrics sdm = suserName.getResources().getDisplayMetrics();
                LinearLayout.LayoutParams slayoutParams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, convertDpToPx(80, sdm));
                suserName.setLayoutParams(slayoutParams1);
                spassword = new EditText(this);
                spassword.setHint("password");
                spassword.setHintTextColor(getResources().getColor(R.color.silver));
                spassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                logIns.addView(spassword, 1);
                DisplayMetrics spdm = spassword.getResources().getDisplayMetrics();
                LinearLayout.LayoutParams slayoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, convertDpToPx(80, spdm));
                spassword.setLayoutParams(slayoutParams2);
                EditText scpassword = new EditText(this);
                scpassword.setHint("confirm password");
                scpassword.setHintTextColor(getResources().getColor(R.color.silver));
                scpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                logIns.addView(scpassword, 2);
                DisplayMetrics scpdm = scpassword.getResources().getDisplayMetrics();
                LinearLayout.LayoutParams sclayoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, convertDpToPx(80, scpdm));
                scpassword.setLayoutParams(sclayoutParams2);
                */break;

            case R.id.login:
                //Toast.makeText(getApplicationContext(), "loging in", Toast.LENGTH_LONG).show();
                final ProgressDialog prBuilder=new ProgressDialog(LoginActivity.this);
                prBuilder.setMessage("Loging in");
                prBuilder.setCancelable(false);
                prBuilder.show();
                info=connectivityManager.getActiveNetworkInfo();

                ParseQuery<ParseObject> parseQuery=ParseQuery.getQuery("testClass");
                parseQuery.whereEqualTo("username",userName.getText().toString());
                parseQuery.whereEqualTo("password",password.getText().toString());
                parseQuery.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(objects.size()!=0)
                        {  branch= objects.get(0).getInt("branch");
                            sem=objects.get(0).getInt("sem");
                            prBuilder.dismiss();
                            saveDataLocally(userName.getText().toString(), branch, sem);
                            Intent intent = new Intent(getApplicationContext(), CardActivity.class);
                            startActivity(intent);

                        }
                        else
                        {   prBuilder.dismiss();
                            AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
                            builder.setTitle("error !!");
                            builder.setMessage("user name and password not matching");
                            builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog dialog=builder.create();
                            dialog.show();

                        }
                    }
                });
                break;
            /*case R.id.signup:
                Toast.makeText(getBaseContext(),"sigining up",Toast.LENGTH_LONG).show();*/
        }
    }

    private void saveDataLocally(String USN,int branch,int sem) {
        SharedPreferences saveData = getSharedPreferences(MainActivity.PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = saveData.edit();
        editor.putString(Constents.NAME, userName.getText().toString());
        editor.putString(Constents.USN, USN);
        editor.putInt(Constents.BRANCH, branch);
        editor.putInt(Constents.SEM, sem);
        editor.putBoolean(Constents.LOGINSTATUS, true);
        editor.apply();
    }

    @Override
    public void onBackPressed() {
        if(flag) {
            logIn.setId(R.id.LoginButton);
            //signUp.setId(R.id.SignUpButton);
            logIns.removeAllViews();
            logIns.addView(logIn);
            logIns.addView(or);
            logIns.addView(signUp);
            flag=false;
        }
        else

            super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    private int convertDpToPx(int dp, DisplayMetrics displayMetrics) {
        float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
        return Math.round(pixels);
    }
}
