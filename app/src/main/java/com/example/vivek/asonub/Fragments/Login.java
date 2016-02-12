package com.example.vivek.asonub.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vivek.asonub.CardActivity;
import com.example.vivek.asonub.Constents.Constents;
import com.example.vivek.asonub.MainActivity;
import com.example.vivek.asonub.R;
import com.example.vivek.asonub.TopicRegisterService;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {
    @NonNull
    EditText USN;
    @NonNull
    EditText Password;
    @NonNull
    EditText confirmPassword;
    @NonNull
    EditText userName;
    Spinner branch;
    Spinner sem;
    Button signUp;
    String[] mBranch = {"--select-branch--", "CSE", "ECE", "ME", "TCE"};
    String[] mSem = {"--select-branch--", "1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th"};
    List<String> mBranchList = new ArrayList<>(Arrays.asList(mBranch));
    List<String> mSemList = new ArrayList<>(Arrays.asList(mSem));
    boolean userNameFlag = false;
    boolean usnFlag = false;
    boolean both = false;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            localValidateSuccess();
        }
    };
    Thread uploder = new Thread(runnable);

    public Login() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        USN = (EditText) root.findViewById(R.id.usn);
        USN.setTextColor(getResources().getColor(R.color.Black));
        Password = (EditText) root.findViewById(R.id.password);
        Password.setTextColor(getResources().getColor(R.color.Black));
        confirmPassword = (EditText) root.findViewById(R.id.confirmpassword);
        confirmPassword.setTextColor(getResources().getColor(R.color.Black));
        userName = (EditText) root.findViewById(R.id.userName);
        userName.setTextColor(getResources().getColor(R.color.Black));
        branch = (Spinner) root.findViewById(R.id.branch);
        sem = (Spinner) root.findViewById(R.id.sem);
        signUp = (Button) root.findViewById(R.id.signup);
        signUp.setTextColor(getResources().getColor(R.color.silver));
        USN.setHint("USN");
        USN.setHintTextColor(getResources().getColor(R.color.silver));
        Password.setHint("Password");
        Password.setHintTextColor(getResources().getColor(R.color.silver));
        Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        confirmPassword.setHint("Confirm Password");
        confirmPassword.setHintTextColor(getResources().getColor(R.color.silver));
        confirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        userName.setHint("Natasha");
        userName.setHintTextColor(getResources().getColor(R.color.silver));
        branch.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, mBranchList));
        sem.setAdapter(new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, mSemList));
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
        return root;
    }

    //custom validator
    private void validate() {
        if (userName.getText().toString().isEmpty() || USN.getText().toString().isEmpty() || Password.getText().toString().isEmpty() || confirmPassword.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "One or More fields are empty", Toast.LENGTH_LONG).show();

        } else {
            if (Password.getText().toString().contentEquals(confirmPassword.getText().toString())) {
                if (branch.getSelectedItemPosition() > 0 && sem.getSelectedItemPosition() > 0) {


                    uploder.run();

                } else {
                    Toast.makeText(getActivity(), "select branch and sem", Toast.LENGTH_LONG).show();

                }
            } else {
                Toast.makeText(getActivity(), "passwords do not match", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void localValidateSuccess() {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Signing in");
        progressDialog.setMessage("please wait for a while");
        progressDialog.show();
        ParseQuery<ParseObject> isExitsUserName = ParseQuery.getQuery("testClass");
        isExitsUserName.whereEqualTo("username", userName.getText().toString());
        ParseQuery<ParseObject> isUsnExist = ParseQuery.getQuery("testClass");
        isUsnExist.whereEqualTo("usn", USN.getText().toString());
        ParseQuery<ParseObject> list[] = new ParseQuery[2];
        list[0] = isExitsUserName;
        list[1] = isUsnExist;

        ParseQuery<ParseObject> finalQuery = ParseQuery.or(Arrays.asList(list));
        finalQuery.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {

                    if (objects.size() == 0) {


                        ParseObject parseObject = new ParseObject("testClass");
                        parseObject.put("username", userName.getText().toString());
                        parseObject.put("usn", USN.getText().toString());
                        parseObject.put("password", Password.getText().toString());
                        parseObject.put("registration_id", getActivity().getSharedPreferences(MainActivity.PREF_FILE_NAME, Context.MODE_PRIVATE).getString(MainActivity.token, null));
                        parseObject.put("branch",branch.getSelectedItemPosition());
                        parseObject.put("sem",sem.getSelectedItemPosition());
                        parseObject.saveInBackground();
                        saveDataLocally();
                        progressDialog.dismiss();

                        Toast.makeText(getActivity(), "saveSuccess", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity(), CardActivity.class);
                        startActivity(intent);

                    } else {


                        for (ParseObject p : objects) {
                            Log.e("show", "some stuffs");
                            if (userName.getText().toString().contentEquals(p.getString("username"))) {
                                userNameFlag = true;
                                Log.d("username", p.getString("username"));
                            }
                            if (USN.getText().toString().contentEquals(p.getString("usn"))) {
                                usnFlag = true;
                                Log.d("USN", p.getString("usn"));
                            }


                        }
                        if (userNameFlag && usnFlag) {
                            both = true;
                        }


                        progressDialog.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        if (both) {
                            builder.setTitle("user name or usn  already in use ");
                            builder.setMessage("please check your USN and change username and try again");
                            both = false;
                        } else if (userNameFlag) {
                            builder.setTitle("user name  already in use");
                            builder.setMessage("please  change username and try again");
                            userNameFlag = false;
                        } else if (usnFlag) {
                            builder.setTitle("usn already in use");
                            builder.setMessage("please check your USN try again");
                            usnFlag = false;
                        }
                        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.create();

                        dialog.show();
                    }
                } else {

                    Log.d("uniqueness", "no idea of error" + e);
                }

            }
        });

    }

    private void saveDataLocally() {
        SharedPreferences saveData = getContext().getSharedPreferences(MainActivity.PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = saveData.edit();
        editor.putString(Constents.NAME, userName.getText().toString());
        editor.putString(Constents.USN, USN.getText().toString());
        editor.putInt(Constents.BRANCH, branch.getSelectedItemPosition());
        editor.putInt(Constents.SEM, sem.getSelectedItemPosition());
        editor.putBoolean(Constents.LOGINSTATUS, true);
        editor.apply();
        //for(int i=0;i<2000;i++);
        Intent intent=new Intent(getActivity(), TopicRegisterService.class);
        Bundle bundle=new Bundle();
        bundle.putInt(Constents.BRANCH, branch.getSelectedItemPosition());
        bundle.putInt(Constents.SEM, sem.getSelectedItemPosition());
        intent.putExtras(bundle);
        getActivity().startService(intent);
    }


}
