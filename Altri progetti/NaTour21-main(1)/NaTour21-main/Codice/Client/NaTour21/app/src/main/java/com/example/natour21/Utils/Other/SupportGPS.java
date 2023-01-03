package com.example.natour21.Utils.Other;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.example.natour21.Presenter.RegistraItinerarioPresenter;
import com.example.natour21.R;
import com.example.natour21.View.Other.RegistraPercorsoActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.osmdroid.util.GeoPoint;

public class SupportGPS implements Runnable {

    private final FusedLocationProviderClient client;
    private final RegistraPercorsoActivity registraPercorsoActivity;
    private final RegistraItinerarioPresenter registraItinerarioPresenter;
    private int interval = 3000;

    public SupportGPS(FusedLocationProviderClient client,
                      RegistraPercorsoActivity registraPercorsoActivity,
                      RegistraItinerarioPresenter registraItinerarioPresenter) {
        this.client = client;
        this.registraPercorsoActivity = registraPercorsoActivity;
        this.registraItinerarioPresenter = registraItinerarioPresenter;
    }

    @Override
    public void run() {
        Log.i("GPS","Initialize Thread.");
        if (ActivityCompat.checkSelfPermission(registraPercorsoActivity,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(registraPercorsoActivity,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            registraPercorsoActivity.runOnUiThread(() -> Service.checkGpsEnabled(registraPercorsoActivity, Service.GPS_PERMISSION_CODE));
            return;
        }
        while(!Thread.currentThread().isInterrupted()) {
            Log.i("GPS", "Thread name: " + Thread.currentThread().getName());
            client.getLastLocation().addOnSuccessListener(registraPercorsoActivity, location -> {
                if (location != null) {
                    Log.i("GPS", "Latitudine: " + location.getLatitude());
                    Log.i("GPS", "Longitudine: " + location.getLongitude());
                    GeoPoint geoPoint = new GeoPoint(location.getLatitude(),location.getLongitude());
                    registraItinerarioPresenter.addGeoPoint(geoPoint);
                }else{
                    Log.i("GPS", "Nessuna location.");
                    new MaterialAlertDialogBuilder(registraPercorsoActivity, R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog)
                            .setTitle("Errore GPS")
                            .setMessage("Il GPS è stato disattivato, verrai reindirizzato alla homepage.")
                            .setPositiveButton("Ho capito", (dialogInterface, i) -> {
                                registraItinerarioPresenter.annulla();
                                registraPercorsoActivity.finish();
                            })
                            .show();
                }
            });
            try {
                Log.i("GPS", "Sleep.");
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                Log.e("GPS", "InterruptedException.");
                e.printStackTrace();
                break;
            }
        }
    }
}
