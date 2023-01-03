package com.example.natour21.View.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.example.natour21.Presenter.SignUpPresenter;
import com.example.natour21.R;
import com.example.natour21.View.MainActivity;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpFragment extends Fragment {

    private SignUpPresenter mSignUpPresenter;
    private final String TAG = "SignUpFragment";

    private EditText editTextEmail, editTextUsername, editTextNome,
            editTextCognome, editTextPassword, editTextConfermaPassword;
    private ImageView backButton;
    private Button registratiButton;
    private TextInputLayout passwordLayout, confermaPasswordLayout;

    private CheckBox terminiServiziCheckBox;
    private TextView twCheckBox;

    private Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSignUpPresenter = new SignUpPresenter(this);

        editTextEmail = (EditText) getActivity().findViewById(R.id.SUeditTextEmailSignUp);
        editTextUsername = (EditText) getActivity().findViewById(R.id.SUeditTextUsername);
        editTextNome = (EditText) getActivity().findViewById(R.id.SUeditTextNome);
        editTextCognome = (EditText) getActivity().findViewById(R.id.SUeditTextCognome);
        editTextPassword = (EditText) getActivity().findViewById(R.id.SUeditTextPasswordSignup);
        editTextConfermaPassword = (EditText) getActivity().findViewById(R.id.SUeditTextConfermaPassword);
        backButton = (ImageView) getActivity().findViewById(R.id.SUbackButtonRegistrati);
        registratiButton = (Button) getActivity().findViewById(R.id.SUregistratiButton);
        passwordLayout = (TextInputLayout) getActivity().findViewById(R.id.SUeditTextPasswordSignupLayout);
        confermaPasswordLayout = (TextInputLayout) getActivity().findViewById(R.id.SUeditTextConfermaPasswordLayout);
        terminiServiziCheckBox = (CheckBox) getActivity().findViewById(R.id.SUcheckBoxTerminiServizi);
        twCheckBox = (TextView) getActivity().findViewById(R.id.SUtextViewTerminiServizi);

        toolbar = (Toolbar) getActivity().findViewById(R.id.SUtoolbar);

        editTextPassword.setOnTouchListener((v, event) -> {
            passwordLayout.setErrorEnabled(false);
            return false;
        });

        editTextConfermaPassword.setOnTouchListener((v, event) -> {
            confermaPasswordLayout.setErrorEnabled(false);
            return false;
        });

        terminiServiziCheckBox.setOnClickListener(v -> {
            if(twCheckBox.getVisibility() == View.VISIBLE)
                twCheckBox.setVisibility(View.INVISIBLE);
        });

        registratiButton.setOnClickListener(v -> {
            Log.i(TAG,"Click conferma registrazione.");
            String email = editTextEmail.getText().toString().trim();
            String username = editTextUsername.getText().toString().trim();
            String nome = editTextNome.getText().toString().trim();
            String cognome = editTextCognome.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            String confermaPassword = editTextConfermaPassword.getText().toString().trim();
            boolean flag = terminiServiziCheckBox.isChecked();

            mSignUpPresenter.signUp(email, username, nome, cognome, password, confermaPassword, flag);
        });

        backButton.setOnClickListener(v -> {
            Log.i(TAG,"Click back in registrazione.");
            returnToLogin();
        });
    }

    public void setError(String errore) {
        Log.i(TAG,"Show error " + errore);
        switch (errore) {
            case "email":               editTextEmail.setError("Email vuota o non valida.");
                                        break;
            case "username":            editTextUsername.setError("Username vuoto o non valido.");
                                        break;
            case "nome":                editTextNome.setError("Inserire un nome.");
                                        break;
            case "cognome":             editTextCognome.setError("Inserire un cognome.");
                                        break;
            case "password":            passwordLayout.setError("Password non valida o vuota.");
                                        break;
            case "confirmPassword":     confermaPasswordLayout.setError("Le password non corrispondono.");
                                        break;
            case "tos":                 twCheckBox.setVisibility(View.VISIBLE);
                                        break;
        }
    }

    public void clear() {
        Log.i(TAG,"Clearing text input field.");
        editTextEmail.setText("");
        editTextUsername.setText("");
        editTextNome.setText("");
        editTextCognome.setText("");
        editTextPassword.setText("");
        editTextConfermaPassword.setText("");

        terminiServiziCheckBox.setChecked(false);
        twCheckBox.setVisibility(View.INVISIBLE);

        passwordLayout.setErrorEnabled(false);
        confermaPasswordLayout.setErrorEnabled(false);
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

    public void returnToLogin() {
        Log.i(TAG,"Return to login.");
        ((MainActivity)getActivity()).changeFragment(((MainActivity)getActivity()).loginFragment);
    }

    @NonNull
    @Override
    public String toString() {
        return "SignUpFragment";
    }
}