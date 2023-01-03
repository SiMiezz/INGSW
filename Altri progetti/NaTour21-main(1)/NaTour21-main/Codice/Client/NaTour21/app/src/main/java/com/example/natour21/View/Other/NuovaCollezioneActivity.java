package com.example.natour21.View.Other;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.natour21.Presenter.CollezioniPresenter;
import com.example.natour21.R;

public class NuovaCollezioneActivity extends AppCompatActivity {

    EditText nomeCollezione, descrizioneCollezione;
    ImageButton backButton;
    Button salvaButton;

    private CollezioniPresenter mCollezioniPresenter;
    private final String TAG = "NuovaCollezioneActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuova_collezione);

        mCollezioniPresenter = new CollezioniPresenter(this);

        nomeCollezione = (EditText) findViewById(R.id.editTextNomeCollezioneAcNuovaCollezione);
        descrizioneCollezione = (EditText) findViewById(R.id.editTextDescrizioneCollezioneAcNuovaCollezione);
        backButton = (ImageButton) findViewById(R.id.backButtonAcNuovaCollezione);
        salvaButton = (Button) findViewById(R.id.buttonSalvaCollezione);

        backButton.setOnClickListener(v -> onBackPressed());

        salvaButton.setOnClickListener(v -> {
            Log.i(TAG,"Click save.");
            String name = nomeCollezione.getText().toString();
            String descrizione = descrizioneCollezione.getText().toString();
            mCollezioniPresenter.saveCompilation(name,descrizione);
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

    public void setNomeCollezioneError(String error) {
        nomeCollezione.setError(error);
        nomeCollezione.requestFocus();
    }

    public void setDescrizioneCollezioneError(String error) {
        descrizioneCollezione.setError(error);
        descrizioneCollezione.requestFocus();
    }

    public void clear(){
        nomeCollezione.setText("");
        descrizioneCollezione.setText("");
    }
}