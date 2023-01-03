package com.example.natour21.View.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.natour21.Presenter.ForgotPasswordPresenter;
import com.example.natour21.R;
import com.example.natour21.View.MainActivity;


public class ForgotPasswordFragment extends Fragment {

    private ForgotPasswordPresenter mForgotPasswordPresenter;
    private final String TAG = "ForgotPasswordFragment";

    private EditText usernameRecovery, code, newPassword;
    private TextView usernameAD, codeAD;
    private Button inviaButton, confermaReset;
    private ImageView backButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recupera_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mForgotPasswordPresenter = new ForgotPasswordPresenter(this);

        code = (EditText) getActivity().findViewById(R.id.FPeditTextCodice);
        newPassword = (EditText) getActivity().findViewById(R.id.FPeditTextNewPassword);
        usernameRecovery = (EditText) getActivity().findViewById(R.id.FPeditTextUserRecovery);
        inviaButton = (Button) getActivity().findViewById(R.id.FPinviaCodiceButton);
        confermaReset = (Button) getActivity().findViewById(R.id.FPconfermaResetButton);
        usernameAD = (TextView) getActivity().findViewById(R.id.FPtextViewUsernameAD);
        codeAD = (TextView) getActivity().findViewById(R.id.FPtextViewResetAD);
        backButton = (ImageView) getActivity().findViewById(R.id.FPbackButtonRecuperaPassword);

        inviaButton.setOnClickListener(v -> {
            Log.i(TAG,"Click invia codice.");
            String recoveryUsername = usernameRecovery.getText().toString().trim();
            mForgotPasswordPresenter.recoveryAccount(recoveryUsername);
        });

        confermaReset.setOnClickListener(v -> {
            Log.i(TAG,"Click conferma reset.");
            String recoveryCode = code.getText().toString().trim();
            String newPass = newPassword.getText().toString().trim();
            mForgotPasswordPresenter.setNewPassword(recoveryCode,newPass);
        });

        backButton.setOnClickListener(v -> {
            Log.i(TAG,"Click back in password dimenticata.");
            returnToLogin();
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

    public void hideRecoveryForm() {
        Log.i(TAG,"Hide recovery form.");
        inviaButton.setVisibility(View.VISIBLE);

        codeAD.setVisibility(View.INVISIBLE);
        code.setVisibility(View.INVISIBLE);
        newPassword.setVisibility(View.INVISIBLE);
        confermaReset.setVisibility(View.INVISIBLE);
    }

    public void showRecoveryForm() {
        Log.i(TAG,"Show recovery form.");
        inviaButton.setVisibility(View.INVISIBLE);

        codeAD.setVisibility(View.VISIBLE);
        code.setVisibility(View.VISIBLE);
        newPassword.setVisibility(View.VISIBLE);
        confermaReset.setVisibility(View.VISIBLE);
    }

    public void setUsernameError() {
        usernameRecovery.setError("Username vuoto o non valido.");
        usernameRecovery.requestFocus();
    }

    public void setNewPasswordError() {
        newPassword.setError("La password non rispetta gli standard.");
        newPassword.requestFocus();
    }

    public void setCodeError() {
        code.setError("Errore con il codice, verifica la correttezza.");
        code.requestFocus();
    }

    public void returnToLogin() {
        ((MainActivity)getActivity()).changeFragment(((MainActivity) getActivity()).loginFragment);
    }

    @NonNull
    @Override
    public String toString() {
        return "RecuperaPasswordFragment";
    }
}