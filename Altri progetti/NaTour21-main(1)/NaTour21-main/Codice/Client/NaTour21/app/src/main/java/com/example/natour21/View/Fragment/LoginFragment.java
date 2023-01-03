package com.example.natour21.View.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.natour21.Presenter.LoginPresenter;
import com.example.natour21.R;
import com.example.natour21.Utils.Other.Service;
import com.example.natour21.View.MainActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.analytics.FirebaseAnalytics;


public class LoginFragment extends Fragment {

    private LoginPresenter mLoginPresenter;
    private final String TAG = "LoginFragment";

    private EditText editTextUsername, editTextPassword;
    private TextInputLayout layoutUsername, layoutPassword;
    private Button loginButton, loginFacebook ,loginGoogle;
    private TextView twPasswordDimenticatata, twRegistrati, twSkip;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    private ConstraintLayout cl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLoginPresenter = new LoginPresenter(this);
        
        editTextUsername = (EditText) getView().findViewById(R.id.LIeditTextUsernameLogin);
        layoutUsername = (TextInputLayout) getView().findViewById(R.id.LIeditTextUsernameLoginLayout);
        editTextPassword = (EditText) getView().findViewById(R.id.LIeditTextPasswordLogin);
        layoutPassword = (TextInputLayout) getView().findViewById(R.id.LIeditTextPasswordLayout);

        loginFacebook = (Button) getView().findViewById(R.id.LIloginFacebookButton);
        loginGoogle = (Button) getView().findViewById(R.id.LIloginGoogleButton);
        loginButton = (Button) getView().findViewById(R.id.LIloginButton);

        twPasswordDimenticatata = (TextView) getView().findViewById(R.id.LIforgotPasswordTextView);
        twRegistrati = (TextView) getView().findViewById(R.id.LItextViewRegistrati);
        twSkip = (TextView) getView().findViewById(R.id.LItextViewSkip);

        cl = (ConstraintLayout) getView().findViewById(R.id.ConstraintLayout);

        mLoginPresenter.isLogged();

        editTextUsername.setOnTouchListener((v, event) -> {
            layoutUsername.setErrorEnabled(false);
            return false;
        });

        editTextPassword.setOnTouchListener((v, event) -> {
            layoutPassword.setErrorEnabled(false);
            return false;
        });

        loginButton.setOnClickListener(v -> {
            Log.i(TAG,"Click local login.");
            layoutUsername.setErrorEnabled(false);
            layoutPassword.setErrorEnabled(false);
            String username = editTextUsername.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            mLoginPresenter.localLogin(username,password);
        });

        loginFacebook.setOnClickListener(v -> {
            Log.i(TAG,"Click Facebook login.");
            mLoginPresenter.doLoginFacebook();
        });

        loginGoogle.setOnClickListener(v -> {
            Log.i(TAG,"Click Google login.");
            mLoginPresenter.doLoginGoogle();
        });

        twSkip.setOnClickListener(v -> {
            Log.i(TAG,"Click skip.");
            mLoginPresenter.loginGuest();
        });

        twRegistrati.setOnClickListener(v -> {
            Log.i(TAG,"Click Registrati.");
            ((MainActivity)getActivity()).changeFragment(((MainActivity)getActivity()).signupFragment);
        });

        twPasswordDimenticatata.setOnClickListener(v -> {
            Log.i(TAG,"Click Password Dimenticata.");
            ((MainActivity)getActivity()).changeFragment(((MainActivity)getActivity()).forgotPasswordFragment);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume Called.");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart Called.");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause Called.");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop Called.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy Called.");
    }
    
    public void setUsernameError() {
        layoutUsername.setError("Username errato o vuoto.");
    }

    public void setPasswordError() {
        layoutPassword.setError("Password errata o vuota.");
    }

    public void openPopupLoading(){
        Log.i(TAG,"Opening Login Loading Popup.");
        dialogBuilder = new AlertDialog.Builder(getActivity());
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup_loading_login, null);
        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();
    }

    public void closePopupLoading(){
        Log.i(TAG,"Closing Login Popup.");
        dialog.dismiss();
    }

    @NonNull
    @Override
    public String toString() {
        return "LoginFragment";
    }
}