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

import com.example.natour21.Presenter.SupportPresenter;
import com.example.natour21.R;
import com.example.natour21.Utils.Other.LocalUser;

public class SupportFragment extends Fragment {

    private final String TAG = "SupportFragment";
    private SupportPresenter mSupportPresenter;

    private EditText fromEditText , subjectEditText, messaggioEditText;
    private Button inviaMessaggioButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_assistenza, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSupportPresenter = new SupportPresenter(this);

        fromEditText = (EditText) getActivity().findViewById(R.id.editTextFromAcAssistenza);
        subjectEditText = (EditText) getActivity().findViewById(R.id.editTextOggettoDelMessaggioAcAssistenza);
        messaggioEditText = (EditText) getActivity().findViewById(R.id.editTextMessaggio);
        inviaMessaggioButton = (Button) getActivity().findViewById(R.id.buttonConfermaAssistenza);
        fromEditText.setHint(LocalUser.getLocalUser(this.getActivity()).getEmail());

        inviaMessaggioButton.setOnClickListener(v -> {
            String subject = subjectEditText.getText().toString();
            String message = messaggioEditText.getText().toString();

            mSupportPresenter.sendEmail(subject,message);
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

    public void setSubjectError(String error) {
        subjectEditText.setError(error);
        subjectEditText.requestFocus();
    }

    public void setMessaggioError(String error) {
        messaggioEditText.setError(error);
        messaggioEditText.requestFocus();
    }

    public void clear(){
        subjectEditText.setText("");
        messaggioEditText.setText("");
    }

    @NonNull
    @Override
    public String toString() {
        return "SupportFragment";
    }
}