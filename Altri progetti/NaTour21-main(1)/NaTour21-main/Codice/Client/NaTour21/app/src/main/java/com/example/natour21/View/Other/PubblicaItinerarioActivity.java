package com.example.natour21.View.Other;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.natour21.Entity.InterestingPoint;
import com.example.natour21.Entity.Itinerario;
import com.example.natour21.Presenter.PubblicaItinerarioPresenter;
import com.example.natour21.R;
import com.example.natour21.Utils.Other.LocalUser;
import com.example.natour21.Utils.Other.Service;
import com.example.natour21.View.MainActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.switchmaterial.SwitchMaterial;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;
import org.xmlpull.v1.XmlPullParserException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class PubblicaItinerarioActivity extends AppCompatActivity {

    private final String MY_USER_AGENT = "MyOwnUserAgent/1.0";

    private PubblicaItinerarioPresenter mPubblicaItinerarioPresenter;
    private final String TAG = "PubblicaItinerarioActivity";

    private EditText puntoDiPartenzaEditText, puntoDiArrivoEditText,
            titoloEditText, descrizioneEditText, areaGeograficaEditText;
    private TextView caricaFileGPXTextView, inserisciInterestinPointTextView;
    private Spinner difficoltaSpinner;
    private Button annullaButton, pubblicaButton, eliminaTappa, eliminaPOI;
    private ImageButton helpButton;
    private TextView pulisciCampi, durataTextView, lunghezzaTextView;
    private SwitchMaterial accessDisable;


    private List<InterestingPoint> interestingPoints;
    private ArrayList<Marker> markers, markersInterestingPoints = new ArrayList<>();
    private int ore;
    private int minuti;
    private Double lunghezza;
    private boolean enabledMappa=true;

    private MapView map;
    private IMapController mapController;
    private RoadManager roadManager;
    private ArrayList<GeoPoint> rout;
    private GeoPoint startPoint = new GeoPoint(40.8517746, 14.2681244);
    private GeoPoint endPoint = new GeoPoint(40.8517746, 14.2681244);

    final Road[] road = new Road[1];
    final Polyline[] roadOverlay = new Polyline[1];

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration.getInstance().setUserAgentValue(MY_USER_AGENT);
        setContentView(R.layout.activity_pubblica_itinerario);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        caricaFileGPXTextView = (TextView) findViewById(R.id.caricaFileGPXTextView);
        inserisciInterestinPointTextView = (TextView) findViewById(R.id.textViewInserisciIPPubbicaItinerario);
        puntoDiPartenzaEditText = (EditText) findViewById(R.id.editTextPartenzaPubblicaItinerario);
        puntoDiArrivoEditText = (EditText) findViewById(R.id.editTextArrivoPubblicaItinerario);
        titoloEditText = (EditText) findViewById(R.id.editTextTitoloPubblicaItinerario);
        descrizioneEditText = (EditText) findViewById(R.id.editTextDescrizionePubblicaItinerario);
        durataTextView = (TextView) findViewById(R.id.durataPubblicaItinerarioTextView);
        lunghezzaTextView = (TextView) findViewById(R.id.lunghezzaPubblicaItinerarioTextView);
        helpButton = (ImageButton) findViewById(R.id.imageButtonHelpPubblicaItinerario);
        areaGeograficaEditText = (EditText) findViewById(R.id.editTextAreaGeograficaPubblicaItinerario);

        difficoltaSpinner = (Spinner) findViewById(R.id.spinnerDifficoltaPubblicaItinerario);
        annullaButton = (Button) findViewById(R.id.buttonAnnullaPubblicaItinerario);
        pubblicaButton = (Button) findViewById(R.id.confermaPubblicaItinerario);
        pulisciCampi = (TextView) findViewById(R.id.pulisciCampiTextView);
        accessDisable = (SwitchMaterial) findViewById(R.id.switchAccessibilitaDisabiliPubblicaItinerario);
        eliminaTappa = (Button) findViewById(R.id.buttonRimuoviTappaPubblicaItinerario);
        eliminaPOI = (Button) findViewById(R.id.buttonRimuoviInterestingPointPubblicaItinerario);

        mPubblicaItinerarioPresenter = new PubblicaItinerarioPresenter(this);

        ArrayAdapter<CharSequence> adapterDifficolta = ArrayAdapter.createFromResource(PubblicaItinerarioActivity.this,
                R.array.choose_difficulty, R.layout.spinner_item);
        adapterDifficolta.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficoltaSpinner.setAdapter(adapterDifficolta);

        map = (MapView) findViewById(R.id.mapViewAggiungiItinerario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAggiungiItinerario);
        interestingPoints = new ArrayList<>();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        map.setMultiTouchControls(true);
        mapController = map.getController();
        mapController.setZoom(10);
        mapController.setCenter(new GeoPoint(40.8517746, 14.2681244));
        roadManager = new OSRMRoadManager(this, MY_USER_AGENT);
        ((OSRMRoadManager)roadManager).setMean(OSRMRoadManager.MEAN_BY_FOOT);
        rout = new ArrayList<>();
        markers = new ArrayList<>();
        enabledAll();

        Intent getIntent = getIntent();
        if(getIntent!=null && getIntent.hasExtra("listaTappe") && getIntent.hasExtra("listaInterestingPoint")){
            List<GeoPoint> geoPointList = getIntent.getParcelableArrayListExtra("listaTappe");
            List<InterestingPoint> listaInterestingPoint = getIntent.getParcelableArrayListExtra("listaInterestingPoint");
            mPubblicaItinerarioPresenter.uploadDettagliFromRegistrazione(geoPointList, listaInterestingPoint, road, roadOverlay);
        }

        MapEventsReceiver mReceive = new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {
                if(enabledMappa)
                    mPubblicaItinerarioPresenter.aggiungiTappa(p, road, roadOverlay);
                return false;
            }
            @Override
            public boolean longPressHelper(GeoPoint p) {
                return false;
            }
        };

        MapEventsOverlay OverlayEvents = new MapEventsOverlay(getBaseContext(), mReceive);
        map.getOverlays().add(OverlayEvents);

        helpButton.setOnClickListener(v -> {
            Log.i(TAG,"Click help button.");
            new MaterialAlertDialogBuilder(PubblicaItinerarioActivity.this, R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog)
                    .setTitle("Help")
                    .setMessage(R.string.helpPubblicaItinerario)
                    .setPositiveButton("Ho capito.", (dialogInterface, i) -> {})
                    .show();
        });

        eliminaTappa.setOnClickListener(v -> {
            Log.i(TAG,"Click elimina tappa button.");
            eliminaTappa.setEnabled(false);
            if(markers != null && (!markers.isEmpty()))
                mPubblicaItinerarioPresenter.eliminaTappa(road, roadOverlay);
            else
                Toasty.info(PubblicaItinerarioActivity.this,"Nessuna tappa da rimuovere.",
                                Toasty.LENGTH_SHORT,true).show();
            eliminaTappa.setEnabled(true);
        });

        eliminaPOI.setOnClickListener(v -> {
            Log.i(TAG,"Click elimina POI button.");
            eliminaPOI.setEnabled(false);
            if(markersInterestingPoints != null && (!markersInterestingPoints.isEmpty())){
                interestingPoints.remove(interestingPoints.size()-1);
                map.getOverlays().remove(markersInterestingPoints.get(markersInterestingPoints.size()-1));
                markersInterestingPoints.remove(markersInterestingPoints.size()-1);
                }
            map.invalidate();
            eliminaPOI.setEnabled(true);
        });

        pulisciCampi.setOnClickListener(v -> {
            Log.i(TAG,"Click clear all.");
            pulisciCampi.setEnabled(false);
            mPubblicaItinerarioPresenter.pulisciCampi(road, roadOverlay);
            pulisciCampi.setEnabled(true);
            enabledAll();
        });

        caricaFileGPXTextView.setOnClickListener(v -> {
            Log.i(TAG,"Click carica via GPX.");

            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("*/*");

            startActivityForResult(Intent.createChooser(intent, "Choose file"), Service.READ_GPX_FILE_REQUEST_CODE);
        });

        inserisciInterestinPointTextView.setOnClickListener(v -> {
            Log.i(TAG,"Click insert new POI.");
            Intent getIntent1 = new Intent(Intent.ACTION_GET_CONTENT);
            getIntent1.setType("image/*");

            Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickIntent.setType("image/*");

            Intent chooserIntent = Intent.createChooser(getIntent1, "Select Image");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

            startActivityForResult(chooserIntent, Service.CAMERA_PERMISSION_CODE);
        });

        pubblicaButton.setOnClickListener(v -> {
            Log.i(TAG,"Click pubblica.");
            if(rout.size()>=2 && (!titoloEditText.getText().toString().isEmpty())){
                mPubblicaItinerarioPresenter.saveItinerario();
            }else{
                Toasty.error(PubblicaItinerarioActivity.this,"Inserire un titolo e almeno due tappe.",
                        Toasty.LENGTH_SHORT,true).show();
            }
        });

        annullaButton.setOnClickListener(v -> {
            Log.i(TAG,"Click annulla.");
            onBackPressed();
        });

        requestPermissionsIfNecessary(new String[] {
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        });
    }

    @Override
    public void onBackPressed() {
        new MaterialAlertDialogBuilder(PubblicaItinerarioActivity.this, R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog)
                .setTitle("Esci")
                .setMessage("Sei sicuro di voler abbandonare la pubblicazione dell'itinerario?")
                .setPositiveButton("Esci", (dialogInterface, i) -> finish())
                .setNegativeButton("Annulla", (dialogInterface, i) -> {})
                .show();
    }

    public MapView getMap() {
        return map;
    }
    public RoadManager getRoadManager() {
        return roadManager;
    }
    public ArrayList<GeoPoint> getRoute() {
        return rout;
    }
    public GeoPoint getStartPoint() {
        return startPoint;
    }
    public GeoPoint getEndPoint() {
        return endPoint;
    }

    public void setLunghezzaTextView(double kmItinerario){
        lunghezzaTextView.setText((String.format("%.2f", kmItinerario) + "KM"));
    }
    public void setDurataTextView(int hours, int minutes){
        durataTextView.setText("h:" + hours + " m:" + minutes);
    }
    public void setDurataDefaultTextView(){
        durataTextView.setText(R.string.durata);
    }
    public void setLunghezzaDefaultTextView(){
        lunghezzaTextView.setText(R.string.lunghezza);
    }

    public void setPuntoDiPartenzaEditText(String puntoDiPartenza){
        puntoDiPartenzaEditText.setHint(puntoDiPartenza);
    }
    public void setPuntoDiPartenzaDefaultEditText(){
        puntoDiPartenzaEditText.setHint(R.string.puntoDiInizio);
    }
    public void setPuntoDiArrivoEditText(String puntoDiArrivo){
        puntoDiArrivoEditText.setHint(puntoDiArrivo);
    }
    public void setPuntoDiArrivoDefaultEditText(){
        puntoDiArrivoEditText.setHint(R.string.puntoDiFine);
    }

    public void setTitoloDefaultEditText(){
        titoloEditText.setText("");
        titoloEditText.setHint(R.string.titoloItinerario);
    }
    public void setDescrizioneDefaultEditText(){
        descrizioneEditText.setText("");
        descrizioneEditText.setHint(R.string.descrizione);
    }
    public void setAccessibilitaDisabiliDefaultSwitch(){
        accessDisable.setChecked(false);
    }

    public String getTitolo(){
        return titoloEditText.getText().toString();
    }
    public String getDescrizione(){
        return descrizioneEditText.getText().toString();
    }
    public boolean getAccessDisable(){
        return accessDisable.isChecked();
    }
    public void clear(){
        mPubblicaItinerarioPresenter.pulisciCampi();
    }

    public Double getLunghezza() {
        return lunghezza;
    }
    public void setLunghezza(Double lunghezza) {
        this.lunghezza = lunghezza;
    }
    public void setOre(int ore) {
        this.ore = ore;
    }
    public void setMinuti(int minuti) {
        this.minuti = minuti;
    }
    public void setAreaGeograficaEditText(String areaGeografica){
        areaGeograficaEditText.setHint(areaGeografica);
    }
    public void setStartPoint(GeoPoint p){
        this.startPoint = p;
    }
    public void setEndPoint(GeoPoint p){
        this.endPoint = p;
    }

    public ArrayList<Marker> getMarkers() {
        return markers;
    }
    public List<InterestingPoint> getInterestingPoints() {
        return interestingPoints;
    }
    public ArrayList<Marker> getMarkersInterestingPoints(){return  markersInterestingPoints;}
    public void setInterestingPoints(List<InterestingPoint> interestingPoints) {
        this.interestingPoints = interestingPoints;
    }

    public AlertDialog getDialog() {
        return dialog;
    }
    public void setDialog(AlertDialog dialog) {
        this.dialog = dialog;
    }

    public Itinerario getItinerario(){
        Itinerario newItinerario = new Itinerario();
        newItinerario.setTitolo(getTitolo());
        newItinerario.setDescrizione(getDescrizione());
        newItinerario.setId_utente(LocalUser.getLocalUser(PubblicaItinerarioActivity.this).getUsername());
        newItinerario.setPuntoinizio(puntoDiPartenzaEditText.getHint().toString());
        newItinerario.setPuntofine(puntoDiArrivoEditText.getHint().toString());
        newItinerario.setLatitudine_pi(startPoint.getLatitude());
        newItinerario.setLongitudine_pi(startPoint.getLongitude());
        newItinerario.setLatitudine_pf(endPoint.getLatitude());
        newItinerario.setLongitudine_pf(endPoint.getLongitude());
        newItinerario.setAccessodisabili(getAccessDisable());
        newItinerario.setDifficulty(difficoltaSpinner.getSelectedItem().toString());

        String durataTotale;
        if(ore<10)
            durataTotale = "0"+ ore;
        else
            durataTotale = String.valueOf(ore);

        if(minuti<10)
            durataTotale = durataTotale + ":0"+ minuti;
        else
            durataTotale = durataTotale + ":" + minuti;

        durataTotale = durataTotale + ":00";

        newItinerario.setDurata(durataTotale);
        newItinerario.setLunghezza(getLunghezza());
        newItinerario.setAreageografica(areaGeograficaEditText.getHint().toString());
        return newItinerario;
    }

    public void enabledAll(){
        Log.i(TAG,"Enable all.");
        enabledMappa = true;
        caricaFileGPXTextView.setEnabled(true);
        inserisciInterestinPointTextView.setEnabled(true);
        inserisciInterestinPointTextView.setVisibility(View.VISIBLE);
        eliminaTappa.setEnabled(true);
        eliminaPOI.setEnabled(true);
    }

    public void disableAll(){
        Log.i(TAG,"Disable all.");
        enabledMappa = false;
        caricaFileGPXTextView.setEnabled(false);
        inserisciInterestinPointTextView.setEnabled(false);
        inserisciInterestinPointTextView.setVisibility(View.INVISIBLE);
        eliminaTappa.setEnabled(false);
        eliminaPOI.setEnabled(false);
    }

    public void openPopupLoading(){
        Log.i(TAG,"Opening Loading Popup.");
        dialogBuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup_loading, null);
        dialogBuilder.setView(contactPopupView);
        dialog = dialogBuilder.create();
        dialog.show();
    }

    public void closePopupLoading(){
        Log.i(TAG,"Closing Loading Popup.");
        dialog.dismiss();
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Service.READ_GPX_FILE_REQUEST_CODE && resultCode == MainActivity.RESULT_OK) {
            try {
                mPubblicaItinerarioPresenter.uploadGPXFile(data, road, roadOverlay);
            } catch (FileNotFoundException e) {
                Toasty.error(PubblicaItinerarioActivity.this,"File non trovato.",
                        Toasty.LENGTH_SHORT,true).show();
            }catch (IOException | XmlPullParserException e){
                Toasty.error(PubblicaItinerarioActivity.this,"Impossibile aprire il file.",
                        Toasty.LENGTH_SHORT,true).show();
            }
        }
        if (requestCode == Service.CAMERA_PERMISSION_CODE && resultCode == MainActivity.RESULT_OK) {
            mPubblicaItinerarioPresenter.inserisciInterestingPoint(data);
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

}