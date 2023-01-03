package com.example.natour21.Presenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;

import com.example.natour21.Entity.InterestingPoint;
import com.example.natour21.R;
import com.example.natour21.Utils.Other.SupportGPS;
import com.example.natour21.View.Other.PubblicaItinerarioActivity;
import com.example.natour21.View.Other.RegistraPercorsoActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import org.osmdroid.util.GeoPoint;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class RegistraItinerarioPresenter {

    private final String TAG = "RegistraItinerarioPresenter";
    private final RegistraPercorsoActivity mRegistraPercorsoActivity;

    private FusedLocationProviderClient client;
    private Thread getPointService;
    private SupportGPS register;

    private List<GeoPoint> geoPointList;
    private List<InterestingPoint> interestingPointList;

    public RegistraItinerarioPresenter(RegistraPercorsoActivity mRegistraPercorsoActivity) {
        this.client = LocationServices.getFusedLocationProviderClient(mRegistraPercorsoActivity);
        this.register = new SupportGPS(client, mRegistraPercorsoActivity, this);
        this.mRegistraPercorsoActivity = mRegistraPercorsoActivity;
        this.geoPointList = new ArrayList<>();
        this.interestingPointList = new ArrayList<>();
    }

    public void avvia() {
        Log.i(TAG,"Avvia registrazione.");
        if(getPointService != null && getPointService.isAlive())
            Toasty.warning(mRegistraPercorsoActivity,"Servizio in corso.",
                    Toasty.LENGTH_SHORT, true).show();
        else {
            getPointService = new Thread(register);
            getPointService.start();
        }
    }

    public void stop() {
        Log.i(TAG,"Stop registrazione.");
        if(getPointService != null && getPointService.isAlive())
            getPointService.interrupt();
        else
            Toasty.warning(mRegistraPercorsoActivity,"Servizio non avviato.",
                    Toasty.LENGTH_SHORT,true).show();
    }

    public void pubblica() {
        Log.i(TAG,"Pubblica registrazione.");
        Intent newIntent = new Intent(mRegistraPercorsoActivity, PubblicaItinerarioActivity.class);
        newIntent.putParcelableArrayListExtra("listaTappe", (ArrayList<? extends Parcelable>) geoPointList);
        newIntent.putParcelableArrayListExtra("listaInterestingPoint", (ArrayList<? extends Parcelable>) interestingPointList);
        mRegistraPercorsoActivity.startActivity(newIntent);

    }

    public void annulla() {
        Log.i(TAG,"Annulla registrazione.");
        geoPointList.clear();
        interestingPointList.clear();
        if(getPointService != null && getPointService.isAlive())
            getPointService.interrupt();
    }

    public void addGeoPoint(GeoPoint g) {
        Log.i(TAG,"Adding new geopoint.");
        geoPointList.add(g);
    }

    public void addPOI(Intent data) {
        Log.i(TAG,"Adding new POI.");
        AlertDialog.Builder dialogBuilder;
        AlertDialog dialog;
        dialogBuilder = new AlertDialog.Builder(mRegistraPercorsoActivity);
        final View popupView = mRegistraPercorsoActivity.getLayoutInflater().inflate(R.layout.popup_aggiungi_interesting_point_registra_itinerario, null);

        EditText nomeInterestingPoint = (EditText) popupView.findViewById(R.id.editTextTitoloPopupRegistraItinerario);
        EditText descrizioneInterestingPoint = (EditText) popupView.findViewById(R.id.editTextDescrizionePopupPubblicaItinerario);
        ImageView fotoInterestinPoint = (ImageView) popupView.findViewById(R.id.imageViewPopupRegistraItinerario);
        Button confermaButton = (Button) popupView.findViewById(R.id.buttonConfermaFotoPopupRegistraItinerario);
        Button annullaButton = (Button) popupView.findViewById(R.id.buttonAnnullaFotoPopupRegistraItinerario);

        dialogBuilder.setView(popupView);
        dialog = dialogBuilder.create();
        dialog.show();

        Bundle extras = data.getExtras();
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        fotoInterestinPoint.setImageBitmap(imageBitmap);
        Uri tempUri = getImageUri(mRegistraPercorsoActivity, imageBitmap);

        Double latitudine;
        Double longitudine;

        latitudine = geoPointList.get(geoPointList.size()-1).getLatitude();
        longitudine = geoPointList.get(geoPointList.size()-1).getLongitude();

        Double finalLatitudine = latitudine;
        Double finalLongitudine = longitudine;

        confermaButton.setOnClickListener(v -> {
            fotoInterestinPoint.setImageURI(data.getData());
            InterestingPoint newInterestingPoint = new InterestingPoint();

            if(finalLatitudine != null && finalLongitudine != null){
                newInterestingPoint.setLatitudine(Double.valueOf(finalLatitudine));
                newInterestingPoint.setLongitudine(Double.valueOf(finalLongitudine));
                newInterestingPoint.setDescrizione(descrizioneInterestingPoint.getText().toString());
                newInterestingPoint.setTitolo(nomeInterestingPoint.getText().toString());
                newInterestingPoint.setUrlfoto(tempUri.toString());

                interestingPointList.add(newInterestingPoint);

                dialog.dismiss();

            }else{
                Log.i("LatLong", "Latitudine e longitudine non presenti");
                Toasty.error(mRegistraPercorsoActivity,"Posizione non presente nella foto.",
                        Toasty.LENGTH_SHORT,true).show();
                dialog.dismiss();
            }
        });

        annullaButton.setOnClickListener(v -> dialog.dismiss());
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}