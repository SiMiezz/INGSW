package com.example.natour21.View.Other;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.natour21.Presenter.RegistraItinerarioPresenter;
import com.example.natour21.R;
import com.example.natour21.Utils.Other.Service;
import com.example.natour21.View.MainActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class RegistraPercorsoActivity extends AppCompatActivity {

    private RegistraItinerarioPresenter mRegistraItinerarioPresenter;
    private final String TAG = "RegistraPercorsoActivity";

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    Chronometer cronometro;
    ImageButton cameraButton;
    Button avviaButton, stopButton, pubblicaButton, annullaButton;

    long timeWhenStopped = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registra_percorso);

        mRegistraItinerarioPresenter = new RegistraItinerarioPresenter(this);

        cronometro = (Chronometer) findViewById(R.id.Cronometro);
        cameraButton = (ImageButton) findViewById(R.id.imageButtonScattaFotoRegistraItinerario);
        avviaButton = (Button) findViewById(R.id.buttonAvviaCronometro);
        stopButton = (Button) findViewById(R.id.buttonStopCronometro);
        pubblicaButton = (Button) findViewById(R.id.buttonPubblica);
        annullaButton = (Button) findViewById(R.id.buttonAnnullaCronometro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarRegistraItinerario);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        avviaButton.setVisibility(View.VISIBLE);
        stopButton.setVisibility(View.INVISIBLE);

        avviaButton.setEnabled(true);
        stopButton.setEnabled(false);

        avviaButton.setOnClickListener(v -> {
            Log.i(TAG, "Click avvia registrazione.");
            if(cameraButton.getVisibility()==View.INVISIBLE)
                cameraButton.setVisibility(View.VISIBLE);
            avviaButton.setEnabled(false);
            stopButton.setEnabled(true);
            if(timeWhenStopped == 0)
                cronometro.setBase(SystemClock.elapsedRealtime());
            else
                cronometro.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);

            cronometro.start();
            pubblicaButton.setVisibility(View.INVISIBLE);
            annullaButton.setVisibility(View.INVISIBLE);
            mRegistraItinerarioPresenter.avvia();
            avviaButton.setVisibility(View.INVISIBLE);
            stopButton.setVisibility(View.VISIBLE);
        });

        stopButton.setOnClickListener(v -> {
            Log.i(TAG, "Click stop registrazione.");
            avviaButton.setEnabled(true);
            stopButton.setEnabled(false);
            timeWhenStopped = cronometro.getBase() - SystemClock.elapsedRealtime();
            cronometro.stop();
            pubblicaButton.setVisibility(View.VISIBLE);
            annullaButton.setVisibility(View.VISIBLE);
            stopButton.setVisibility(View.INVISIBLE);
            mRegistraItinerarioPresenter.stop();
        });

        cameraButton.setOnClickListener(v -> {
            Log.i(TAG, "Click add camera photo.");
            Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            try {
                startActivityForResult(takePicture, Service.CAMERA_PERMISSION_CODE);
            }catch (ActivityNotFoundException e){}
        });

        pubblicaButton.setOnClickListener(v -> {
            Log.i(TAG, "Click pubblica registrazione.");
            mRegistraItinerarioPresenter.pubblica();
        });

        annullaButton.setOnClickListener(v -> {
            Log.i(TAG, "Click annulla registrazione.");
            onBackPressed();
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

    @Override
    public void onBackPressed() {
        new MaterialAlertDialogBuilder(RegistraPercorsoActivity.this, R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog)
                .setTitle("Esci")
                .setMessage("Sei sicuro di voler abbandonare la registrazione dell'itinerario?")
                .setPositiveButton("Esci", (dialogInterface, i) -> {
                    mRegistraItinerarioPresenter.annulla();
                    finish();
                })
                .setNegativeButton("Annulla", (dialogInterface, i) -> {})
                .show();
    }

    public void popupChangeImage(Intent data){
        mRegistraItinerarioPresenter.addPOI(data);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Service.CAMERA_PERMISSION_CODE && resultCode == MainActivity.RESULT_OK)
            popupChangeImage(data);
    }

    @NonNull
    @Override
    public String toString() {
        return "RegistraPercorsoFragment";
    }
}