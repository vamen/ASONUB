package com.example.vivek.asonub;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class splashfragment extends Fragment {


    private CallbackManager callbackManager;

    public splashfragment() {
        // Required empty public constructor
    }
    LoginButton loginButton;
    public View root;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         FacebookSdk.sdkInitialize(getActivity());
         callbackManager = CallbackManager.Factory.create();
         root= inflater.inflate(R.layout.fragment_splashfragment, container, false);
         loginButton = (LoginButton) root.findViewById(R.id.login_button);
         loginButton.setFragment(this);

        return root;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
