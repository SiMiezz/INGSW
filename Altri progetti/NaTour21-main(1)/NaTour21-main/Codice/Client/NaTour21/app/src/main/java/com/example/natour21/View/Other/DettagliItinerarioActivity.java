package com.example.natour21.View.Other;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.natour21.Entity.InterestingPoint;
import com.example.natour21.Entity.Itinerario;
import com.example.natour21.Entity.Tappa;
import com.example.natour21.Presenter.DettagliItinerarioPresenter;
import com.example.natour21.R;
import com.example.natour21.Utils.Other.LocalUser;
import com.example.natour21.Utils.Other.Service;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.osmdroid.api.IMapController;

import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.util.ArrayList;

public class DettagliItinerarioActivity extends AppCompatActivity {

    private final String MY_USER_AGENT = "MyOwnUserAgent/1.0";
    private DettagliItinerarioPresenter mDettagliItinerarioPresenter;
    private final String TAG = "DettagliItinerarioActivity";

    private long currentItinerario;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    private AlertDialog.Builder dialogBuilderLoading;
    private AlertDialog dialogLoading;

    private AlertDialog.Builder dialogBuilderCompilation;
    private AlertDialog dialogCompilation;

    MapView map;
    IMapController mapController;
    RoadManager roadManager;
    ArrayList<GeoPoint> wayPoints;
    ArrayList<SlideModel> images;

    ImageSlider imageSlider;
    ImageButton aggiungiFotoButton, buttonAddInCompilation, imageButtonGoogleMaps;
    TextView titolo, descrizione, from,
            to, durata, difficulty,
            accessDisable, lunghezza, areaGeografica;
    ScrollView scrollView;
    Toolbar toolbar;

    double lat_puntoIniziale, long_puntoIniziale,
            lat_puntoFinale, long_puntoFinale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration.getInstance().setUserAgentValue(MY_USER_AGENT);
        setContentView(R.layout.activity_dettagli_itinerario);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mDettagliItinerarioPresenter = new DettagliItinerarioPresenter(this);

        imageSlider = (ImageSlider) findViewById(R.id.imageSliderDettagliItinerario);
        buttonAddInCompilation = (ImageButton) findViewById(R.id.imageButtonAddInCompilation);
        aggiungiFotoButton = (ImageButton) findViewById(R.id.ImageButtonAggiungiFotoDettagliItinerario);
        imageButtonGoogleMaps = (ImageButton) findViewById(R.id.imageButtonGoogleMaps);
        titolo = (TextView) findViewById(R.id.textViewTitoloDettagliItinerario);
        descrizione = (TextView) findViewById(R.id.textViewdescrizioneDettaglioItinerario);
        from = (TextView) findViewById(R.id.textViewFromDettagliIitinerario);
        to = (TextView) findViewById(R.id.textViewToDettagliIitinerario);
        durata = (TextView) findViewById(R.id.textViewDurataDettagliIitinerario);
        difficulty = (TextView) findViewById(R.id.textViewDifficoltaDettagliIitinerario);
        accessDisable = (TextView) findViewById(R.id.textViewDisabiliDettagliIitinerario);
        lunghezza = (TextView) findViewById(R.id.textViewLunghezzaIitinerarioDettagliItinerario);
        areaGeografica = (TextView) findViewById(R.id.textViewAreaGeograficaDettagliIitinerario);
        scrollView = (ScrollView) findViewById(R.id.scrollViewDettagliItinerario);
        toolbar = (Toolbar) findViewById(R.id.toolbarDettagliItinerario);

        map = (MapView) findViewById(R.id.mapViewDettagliItinerario);
        map.setMultiTouchControls(true);
        wayPoints = new ArrayList<GeoPoint>();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        Intent intentArrived = getIntent();
        long id_itinerario;
        if(intentArrived != null && intentArrived.hasExtra("id_itinerario")) {
            openPopupLoading();
            id_itinerario = intentArrived.getLongExtra("id_itinerario", -1);
            mDettagliItinerarioPresenter.getInterestingPointItinerario(id_itinerario);
            mDettagliItinerarioPresenter.getTappeItinerario(id_itinerario);
            mDettagliItinerarioPresenter.getDettagliItinerario(id_itinerario);
            currentItinerario = id_itinerario;
        }

        images = new ArrayList<>();
        if(!LocalUser.isGuest(this))
            mDettagliItinerarioPresenter.reloadPhoto(imageSlider, currentItinerario);

        imageButtonGoogleMaps.setOnClickListener(v -> {
            Log.i(TAG, "Click google maps icon.");
            mDettagliItinerarioPresenter.goGoogleMaps(lat_puntoIniziale, long_puntoIniziale);
        });

        buttonAddInCompilation.setOnClickListener(v -> {
            if(!LocalUser.isGuest(this)) {
                Log.i(TAG, "Click add in compilation icon.");
                mDettagliItinerarioPresenter.popupAddInCompilation(dialogBuilderCompilation,
                        dialogCompilation,
                        currentItinerario);
            }
        });

        aggiungiFotoButton.setOnClickListener(v -> {
            if(!LocalUser.isGuest(this)) {
                Log.i(TAG, "Click add foto button.");
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                startActivityForResult(chooserIntent, Service.CAMERA_PERMISSION_CODE);
            }
        });

        requestPermissionsIfNecessary(new String[] {
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        });
    }

    public void openPopupLoading(){
        Log.i(TAG,"Opening loading Popup.");
        dialogBuilderLoading = new AlertDialog.Builder(DettagliItinerarioActivity.this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup_loading, null);

        dialogBuilderLoading.setView(contactPopupView);
        dialogLoading = dialogBuilderLoading.create();
        dialogLoading.show();
    }

    public void closePopupLoading(){
        Log.i(TAG,"Closing Loading Popup.");
        dialogLoading.dismiss();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Service.CAMERA_PERMISSION_CODE && resultCode == HomePageActivity.RESULT_OK) {
            mDettagliItinerarioPresenter.popupNewImage(dialogBuilder,
                    dialog,
                    map,
                    data,
                    imageSlider,
                    currentItinerario);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            permissionsToRequest.add(permissions[i]);
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    Service.CAMERA_PERMISSION_CODE);
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[1]),
                    Service.STORAGE_PERMISSION_CODE);
        }
    }

    private void requestPermissionsIfNecessary(String[] permissions) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(permission);
            }
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    Service.CAMERA_PERMISSION_CODE);
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[1]),
                    Service.STORAGE_PERMISSION_CODE);
        }
    }

    public MapView getMap(){
        return map;
    }
    public ArrayList<SlideModel> getImages() {
        return images;
    }

    public void setFrom(String from) {
        this.from.append(" " + from);
    }
    public void setTo(String to) {
        this.to.append(" " + to);
    }
    public void setDurata(String durata) {
        this.durata.append(" " + durata);
    }
    public void setDifficulty(String difficulty) {
        this.difficulty.append(" " + difficulty);
    }
    public void setAccessDisable(String accessDisable) {
        this.accessDisable.append(" " + accessDisable);
    }
    public void setLunghezza(String lunghezza) {
        this.lunghezza.append(" " + lunghezza + " " + getResources().getString(R.string.km));
    }
    public void setAreaGeografica(String areaGeografica) {
        this.areaGeografica.append(" " + areaGeografica);
    }

    public void setLat_puntoIniziale(double lat_puntoIniziale) {
        this.lat_puntoIniziale = lat_puntoIniziale;
    }
    public void setLong_puntoIniziale(double long_puntoIniziale) {
        this.long_puntoIniziale = long_puntoIniziale;
    }
    public void setLat_puntoFinale(double lat_puntoFinale) {
        this.lat_puntoFinale = lat_puntoFinale;
    }
    public void setLong_puntoFinale(double long_puntoFinale) {
        this.long_puntoFinale = long_puntoFinale;
    }

    public void setTitolo(String titolo) {
        this.titolo.setText(titolo);
    }
    public void setDescrizione(String descrizione) {
        this.descrizione.setText(descrizione);
    }

    public void uploadInterestingPoint(ArrayList<InterestingPoint> interestingPoints) {
        if(interestingPoints != null && (!interestingPoints.isEmpty()))
            mDettagliItinerarioPresenter.uploadInterestingPoints(interestingPoints, map);
    }

    public void uploadDettagliItinerario(Itinerario itinerario) {
        mDettagliItinerarioPresenter.uploadDettagliItinerario(itinerario, mapController);
    }

    public void uploadTappe(ArrayList<Tappa> tappe) {
        mDettagliItinerarioPresenter.uploadTappe(tappe, wayPoints,
                lat_puntoIniziale, long_puntoIniziale,
                lat_puntoFinale, long_puntoFinale);
    }
}