package com.example.natour21.Presenter;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.natour21.Entity.DAO.FactoryDAO;
import com.example.natour21.Entity.DAO.IRetrofitDAO.ICompilationDAO;
import com.example.natour21.Entity.DAO.IRetrofitDAO.IFotoItinerarioDAO;
import com.example.natour21.Entity.DAO.IRetrofitDAO.IInterestingPointDAO;
import com.example.natour21.Entity.DAO.IRetrofitDAO.IItinerarioDAO;
import com.example.natour21.Entity.DAO.IRetrofitDAO.ITappaDAO;
import com.example.natour21.Entity.FotoItinerario;
import com.example.natour21.Entity.InterestingPoint;
import com.example.natour21.Entity.Itinerario;
import com.example.natour21.Entity.Tappa;
import com.example.natour21.R;
import com.example.natour21.Utils.Handler.Callback;
import com.example.natour21.Utils.Handler.Handler;
import com.example.natour21.Utils.Handler.HandlerStorage;
import com.example.natour21.Utils.Other.LocalUser;
import com.example.natour21.Utils.Other.UploadS3;
import com.example.natour21.View.Other.DettagliItinerarioActivity;

import org.apache.commons.lang3.StringUtils;
import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.location.GeocoderNominatim;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.dmoral.toasty.Toasty;

public class DettagliItinerarioPresenter {

    private final String MY_USER_AGENT = "MyOwnUserAgent/1.0";

    private final DettagliItinerarioActivity mDettagliItinerarioActivity;
    private final String TAG = "DettagliItinerarioPresenter";

    public DettagliItinerarioPresenter(DettagliItinerarioActivity mDettagliItinerarioActivity) {
        this.mDettagliItinerarioActivity = mDettagliItinerarioActivity;
    }

    public void getInterestingPointItinerario(long id_itinerario) {
        Log.i(TAG, "Getting Interesting Point of itinerario: "+id_itinerario);
        final IInterestingPointDAO interestingPointDAO = FactoryDAO.getInterestingPointDAO();
        interestingPointDAO.getInterestingPointByItinerario(id_itinerario, new Callback() {
            @Override
            public void onSuccess(Object o) {
                Log.i(TAG,"onSuccess: getInterestingPointItinerario started.");
                try {
                    @SuppressWarnings("unchecked")
                    ArrayList<InterestingPoint> interestingPoints = (ArrayList<InterestingPoint>) o;
                    assert mDettagliItinerarioActivity != null;
                    mDettagliItinerarioActivity.uploadInterestingPoint(interestingPoints);
                }catch (ClassCastException e) {
                    Log.e(TAG,"Errore nel cast.");
                }
            }
            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG,"onError: getInterestingPointItinerario started.");
                Log.e(TAG,e.getLocalizedMessage());
                mDettagliItinerarioActivity.uploadInterestingPoint(new ArrayList<>());
                Handler.HandleError(e,mDettagliItinerarioActivity);
            }
        });
    }

    public void getTappeItinerario(long id_itinerario) {
        Log.i(TAG, "Getting tappe of itinerario: "+id_itinerario);
        final ITappaDAO tappaDAO = FactoryDAO.getTappaDAO();
        tappaDAO.getTappaByItinerario(id_itinerario, new Callback() {
            @Override
            public void onSuccess(Object o) {
                Log.i(TAG,"onSuccess: getTappeItinerario started.");
                try {
                    @SuppressWarnings("unchecked")
                    ArrayList<Tappa> tappe = (ArrayList<Tappa>) o;
                    assert mDettagliItinerarioActivity != null;
                    mDettagliItinerarioActivity.uploadTappe(tappe);
                }catch (ClassCastException e) {
                    Log.e(TAG,"Errore nel cast.");
                }
            }
            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG,"onError: getTappeItinerario started.");
                Log.e(TAG,e.getLocalizedMessage());
                mDettagliItinerarioActivity.uploadTappe(null);
                Handler.HandleError(e,mDettagliItinerarioActivity);
            }
        });
    }

    public void getDettagliItinerario(long id_itinerario) {
        Log.i(TAG, "Getting dettagli of itinerario: "+id_itinerario);
        final IItinerarioDAO itinerarioDAO = FactoryDAO.getItinerarioDAO();
        itinerarioDAO.getItinerarioByID(id_itinerario, new Callback() {
            @Override
            public void onSuccess(Object o) {
                Log.i(TAG,"onSuccess: getDettagliItinerario started.");
                try {
                    @SuppressWarnings("unchecked")
                    Itinerario itinerario = (Itinerario) o;
                    assert mDettagliItinerarioActivity != null;
                    mDettagliItinerarioActivity.uploadDettagliItinerario(itinerario);
                }catch (ClassCastException e) {
                    Log.e(TAG,"Errore nel cast.");
                }
            }
            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG,"onError: getDettagliItinerario started.");
                Log.e(TAG,e.getLocalizedMessage());
                Handler.HandleError(e,mDettagliItinerarioActivity);
            }
        });
    }

    public void checkCompilation(AlertDialog dialogCompilation,
                                 ArrayAdapter<String> adapterCollections,
                                 long currentItinerario,
                                 String username) {
        Log.i(TAG, "Checking compilations.");
        adapterCollections.clear();
        final ICompilationDAO compilationDAO = FactoryDAO.getCompilationDAO();
        compilationDAO.getCompilationValidSave(username, currentItinerario, new Callback() {
            @Override
            public void onSuccess(Object o) {
                Log.i(TAG,"onSuccess: checkCompilation started.");
                try {
                    @SuppressWarnings("unchecked")
                    ArrayList<String> strings = (ArrayList<String>) o;
                    assert mDettagliItinerarioActivity != null;
                    adapterCollections.addAll(strings);
                    adapterCollections.notifyDataSetChanged();
                    dialogCompilation.show();
                }catch (ClassCastException e) {
                    Log.e(TAG,"Errore nel cast.");
                }
            }
            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG,"onError: checkCompilation started.");
                Log.e(TAG,e.getLocalizedMessage());
                dialogCompilation.dismiss();
                mDettagliItinerarioActivity.uploadInterestingPoint(new ArrayList<>());
                Handler.HandleError(e,mDettagliItinerarioActivity);
            }
        });
    }

    public void saveItinerarioInCompilation(String selectedCompilation, long id_itinerario) {
        Log.i(TAG, "Saving itinerario in compilation.");
        final ICompilationDAO compilationDAO = FactoryDAO.getCompilationDAO();

//        //Standard
//        String getId = selectedCompilation.substring(selectedCompilation.indexOf("C") + 1);
//        getId = getId.substring(0, getId.indexOf("-"));

        //Alternativa
        String getId = StringUtils.substringBetween(selectedCompilation,"C","-");
        long id_compilation = Long.valueOf(getId);
        compilationDAO.postItinerarioInCompilation(id_compilation, id_itinerario, new Callback() {
            @Override
            public void onSuccess(Object o) {
                Log.i(TAG,"onSuccess: saveItinerarioInCompilation started.");
                Toasty.success(mDettagliItinerarioActivity,"Itinerario salvato nella compilation.",
                        Toasty.LENGTH_SHORT,true).show();
            }
            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG,"onError: saveItinerarioInCompilation started.");
                Log.e(TAG,e.getLocalizedMessage());
                mDettagliItinerarioActivity.uploadInterestingPoint(new ArrayList<>());
                Handler.HandleError(e,mDettagliItinerarioActivity);
            }
        });
    }

    public void goGoogleMaps(double lat_puntoIniziale, double long_puntoIniziale) {
        Log.i(TAG,"Google maps intent started.");
        Uri gmmIntentUri = Uri.parse("geo:"+lat_puntoIniziale+","+long_puntoIniziale);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(mDettagliItinerarioActivity.getPackageManager()) != null) {
            mDettagliItinerarioActivity.startActivity(mapIntent);
        }
    }

    public void popupAddInCompilation(AlertDialog.Builder dialogBuilderCompilation,
                                      AlertDialog dialogCompilation,
                                      long currentItinerario) {
        Log.i(TAG,"Opening: popupAddInCompilation");
        dialogBuilderCompilation = new AlertDialog.Builder(mDettagliItinerarioActivity);
        final View popupView = mDettagliItinerarioActivity.getLayoutInflater().inflate(R.layout.popup_inserisci_in_compilation, null);

        Spinner spinnerCompilations = popupView.findViewById(R.id.spinnerPopupInserisciInCompilation);
        Button annullaPopupButton = popupView.findViewById(R.id.buttonAnnullaPopupInserisciInCompilation);
        Button confermaPopupButton = popupView.findViewById(R.id.buttonConfermaPopupInserisciInCompilation);

        ArrayAdapter<String> adapterCollections = new ArrayAdapter<String>(mDettagliItinerarioActivity, android.R.layout.simple_spinner_item);
        adapterCollections.setDropDownViewResource(R.layout.spinner_item);
        spinnerCompilations.setAdapter(adapterCollections);

        dialogBuilderCompilation.setView(popupView);
        dialogCompilation = dialogBuilderCompilation.create();

        checkCompilation(dialogCompilation,
                adapterCollections,
                currentItinerario,
                LocalUser.getLocalUser(mDettagliItinerarioActivity).getUsername());


        AlertDialog finalDialogCompilation = dialogCompilation;

        confermaPopupButton.setOnClickListener(v -> {
            String selected = spinnerCompilations.getSelectedItem().toString();
            saveItinerarioInCompilation(selected,currentItinerario);
            finalDialogCompilation.dismiss();
        });

        annullaPopupButton.setOnClickListener(v -> finalDialogCompilation.dismiss());
    }

    public void popupNewImage(AlertDialog.Builder dialogBuilder, AlertDialog dialog,
                              MapView map, Intent data, ImageSlider imageSlider,
                              long id_itinerario) {
        Log.i(TAG,"Opening: popupNewImage");
        ArrayList<SlideModel> images = mDettagliItinerarioActivity.getImages();
        dialogBuilder = new AlertDialog.Builder(mDettagliItinerarioActivity);
        final View popupView = mDettagliItinerarioActivity.getLayoutInflater().inflate(R.layout.popup_aggiungi_interesting_point_registra_itinerario, null);
        ImageView fotoInterestinPoint = (ImageView) popupView.findViewById(R.id.imageViewPopupRegistraItinerario);
        EditText nomeInterestingPoint = (EditText) popupView.findViewById(R.id.editTextTitoloPopupRegistraItinerario);
        EditText descrizioneInterestingPoint = (EditText) popupView.findViewById(R.id.editTextDescrizionePopupPubblicaItinerario);
        Button confermaButton = (Button) popupView.findViewById(R.id.buttonConfermaFotoPopupRegistraItinerario);
        Button annullaButton = (Button) popupView.findViewById(R.id.buttonAnnullaFotoPopupRegistraItinerario);
        descrizioneInterestingPoint.setVisibility(View.INVISIBLE);
        nomeInterestingPoint.setVisibility(View.INVISIBLE);
        dialogBuilder.setView(popupView);
        dialog = dialogBuilder.create();
        dialog.show();

        fotoInterestinPoint.setImageURI(data.getData());

        AlertDialog finalDialog = dialog;
        confermaButton.setOnClickListener(v -> {
            fotoInterestinPoint.setImageURI(data.getData());
            saveFotoItinerario(data, id_itinerario, imageSlider);
            finalDialog.dismiss();
        });

        annullaButton.setOnClickListener(v -> finalDialog.dismiss());
    }

    public void uploadInterestingPoints(ArrayList<InterestingPoint> interestingPoints, MapView map) {
        Log.i(TAG,"Uploading POI.");
        for(InterestingPoint t : interestingPoints){
            Marker newMarker = new Marker(map);
            if(!t.getTitolo().isEmpty())
                newMarker.setTitle(t.getTitolo());
            if(!t.getDescrizione().isEmpty())
                newMarker.setSnippet(t.getDescrizione());
            newMarker.setPosition(new GeoPoint(t.getLatitudine(), t.getLongitudine()));
            newMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            newMarker.setIcon(mDettagliItinerarioActivity.getResources().getDrawable(R.mipmap.placeholder_interestingpoint));
            setPointPhoto(t, newMarker);
            map.getOverlays().add(newMarker);
        }
    }

    private void setPointPhoto(InterestingPoint t, Marker newMarker) {
        Log.i(TAG,"Setting POI photo.");
        UploadS3.getUrl(t.getUrlfoto(), new Callback() {
            @Override
            public void onSuccess(Object o) {
                Log.i(TAG,"onSuccess: setPointPhoto started.");
                String url = (String) o;
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        InputStream input;
                        Bitmap x, bitmapResized;
                        HttpURLConnection connection;
                        try {
                            connection = (HttpURLConnection) new URL(url).openConnection();
                            connection.connect();
                            input = connection.getInputStream();
                            x = BitmapFactory.decodeStream(input);
                            bitmapResized = Bitmap.createScaledBitmap(x, 200, 200, false);
                            mDettagliItinerarioActivity.runOnUiThread(() -> newMarker.setImage(new BitmapDrawable(Resources.getSystem(), bitmapResized)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                t.start();
            }
            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG,"onError: setPointPhoto started.");
                Log.e(TAG,e.getLocalizedMessage());
                mDettagliItinerarioActivity.uploadInterestingPoint(new ArrayList<>());
                HandlerStorage.HandleStore(e,mDettagliItinerarioActivity);
            }
        });
    }

    public void uploadTappe(ArrayList<Tappa> tappe, ArrayList<GeoPoint> wayPoints,
                            double lat_puntoIniziale, double long_puntoIniziale,
                            double lat_puntoFinale, double long_puntoFinale){
        Log.i(TAG,"Uploading tappe.");
        MapView map = mDettagliItinerarioActivity.getMap();
        wayPoints.add(new GeoPoint(lat_puntoIniziale, long_puntoIniziale));
        RoadManager roadManager = new OSRMRoadManager(mDettagliItinerarioActivity, MY_USER_AGENT);
        ((OSRMRoadManager)roadManager).setMean(OSRMRoadManager.MEAN_BY_FOOT);

        Marker startMarker = new Marker(map);
        startMarker.setPosition(new GeoPoint(lat_puntoIniziale, long_puntoIniziale));
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setIcon(mDettagliItinerarioActivity.getResources().getDrawable(R.mipmap.placeholder_tappa));
        GeocoderNominatim gn = new GeocoderNominatim(MY_USER_AGENT);
        try {
            List<Address> addresses = gn.getFromLocation(lat_puntoIniziale, long_puntoIniziale, 1);
            startMarker.setTitle("Inizio:");
            startMarker.setSnippet(addresses.get(0).getAddressLine(0));
        } catch (IOException e) {
            startMarker.setTitle("Inizio.");
        }
        map.getOverlays().add(startMarker);

        if(tappe != null && !tappe.isEmpty()){
            for(Tappa t : tappe){
                wayPoints.add(new GeoPoint(t.getLatitudine(), t.getLongitudine()));
                Marker newMarker = new Marker(map);
                newMarker.setPosition(new GeoPoint(t.getLatitudine(), t.getLongitudine()));
                newMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                newMarker.setIcon(mDettagliItinerarioActivity.getResources().getDrawable(R.mipmap.placeholder_tappa));
                try {
                    List<Address> addresses = gn.getFromLocation(t.getLatitudine(), t.getLongitudine(), 1);
                    newMarker.setTitle("Tappa:");
                    newMarker.setSnippet(addresses.get(0).getAddressLine(0));
                } catch (IOException e) {
                    startMarker.setTitle("Tappa.");
                }
                map.getOverlays().add(newMarker);
            }
        }

        wayPoints.add(new GeoPoint(lat_puntoFinale, long_puntoFinale));

        Marker endMarker = new Marker(map);
        endMarker.setPosition(new GeoPoint(lat_puntoFinale, long_puntoFinale));
        endMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        endMarker.setIcon(mDettagliItinerarioActivity.getResources().getDrawable(R.mipmap.placeholder_tappa));
        try {
            List<Address> addresses = gn.getFromLocation(lat_puntoFinale, long_puntoFinale, 1);
            endMarker.setTitle("Fine:");
            endMarker.setSnippet(addresses.get(0).getAddressLine(0));
        } catch (IOException e) {
            endMarker.setTitle("Fine.");
        }
        map.getOverlays().add(endMarker);

        Road road = roadManager.getRoad(wayPoints);
        Polyline roadOverlay = roadManager.buildRoadOverlay(road);
        map.getOverlays().add(roadOverlay);
        map.invalidate();

        mDettagliItinerarioActivity.closePopupLoading();
    }

    public void saveFotoItinerario(Intent data, long id_itinerario, ImageSlider imageSlider){
        Log.i(TAG, "Saving photo itinerario.");
        Uri uri = data.getData();
        String key = "photo-"
                +id_itinerario
                +"-"
                +new Random().nextInt(100)
                +"-"
                +LocalUser.getLocalUsername(mDettagliItinerarioActivity);

        UploadS3.upload(uri, key,
                mDettagliItinerarioActivity, new Callback() {
                    @Override
                    public void onSuccess(Object o) {
                        Log.i(TAG,"onSuccess: publishFoto started.");
                        FotoItinerario fotoItinerario = new FotoItinerario();
                        fotoItinerario.setId_itinerario(id_itinerario);
                        fotoItinerario.setUrlfoto(key);
                        final IFotoItinerarioDAO fotoItinerarioDAO = FactoryDAO.getFotoItinerarioDAO();
                        fotoItinerarioDAO.publshFoto(fotoItinerario, new Callback() {
                            @Override
                            public void onSuccess(Object o) {
                                Log.i(TAG,"onSuccess: saveFotoItinerario started.");
                                Toasty.success(mDettagliItinerarioActivity,"Foto caricata con successo.",
                                        Toasty.LENGTH_SHORT,true).show();
                                SlideModel newImage = new SlideModel(data.getData().toString(), null);
                                ArrayList<SlideModel> images = mDettagliItinerarioActivity.getImages();
                                images.add(newImage);
                                imageSlider.setImageList(images);
                            }
                            @Override
                            public void onFailure(Throwable e) {
                                Log.e(TAG,"onError: saveFotoItinerario started.");
                                Log.e(TAG,e.getLocalizedMessage());
                                Handler.HandleError(e,mDettagliItinerarioActivity);
                            }
                        });
                    }
                    @Override
                    public void onFailure(Throwable e) {
                        Log.e(TAG,"onError: publishFoto started.");
                        Log.e(TAG,e.getLocalizedMessage());
                        HandlerStorage.HandleStore(e,mDettagliItinerarioActivity);
                    }
                });
    }

    public void reloadPhoto(ImageSlider imageSlider, long currentItinerario) {
        Log.i(TAG, "Checking photo.");
        final IFotoItinerarioDAO fotoItinerarioDAO = FactoryDAO.getFotoItinerarioDAO();
        fotoItinerarioDAO.getFotoItinerarioByItinerario(currentItinerario, new Callback() {
            @Override
            public void onSuccess(Object o) {
                Log.i(TAG,"onSuccess: reloadPhoto started.");
                try {
                    @SuppressWarnings("unchecked")
                    ArrayList<FotoItinerario> listaFoto = (ArrayList<FotoItinerario>) o;
                    for(FotoItinerario fotoItinerario : listaFoto)
                        setFotoInSlider(imageSlider,fotoItinerario.getUrlfoto());
                }catch (ClassCastException e) {
                    Log.e(TAG,"Errore nel cast.");
                }
            }
            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG,"onError: reloadPhoto started.");
                Log.e(TAG,e.getLocalizedMessage());
                Handler.HandleError(e,mDettagliItinerarioActivity);
            }
        });
    }

    private void setFotoInSlider(ImageSlider imageSlider, String key) {
        Log.i(TAG, "Setting photo.");
        UploadS3.getUrl(key, new Callback() {
            @Override
            public void onSuccess(Object o) {
                Log.i(TAG,"onSuccess: setFotoInSlider started.");
                String url = (String) o;
                SlideModel slideModel = new SlideModel(url,null);
                ArrayList<SlideModel> images = mDettagliItinerarioActivity.getImages();
                images.add(slideModel);
                imageSlider.setImageList(images);
            }
            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG,"onError: setFotoInSlider started.");
                Log.e(TAG,e.getLocalizedMessage());
                HandlerStorage.HandleStore(e,mDettagliItinerarioActivity);
            }
        });
    }

    public void uploadDettagliItinerario(Itinerario itinerario, IMapController mapController){
        Log.i(TAG,"Uploading dettagli itinerario.");
        mDettagliItinerarioActivity.setLat_puntoIniziale(itinerario.getLatitudine_pi());
        mDettagliItinerarioActivity.setLong_puntoIniziale(itinerario.getLongitudine_pi());
        mDettagliItinerarioActivity.setLat_puntoFinale(itinerario.getLatitudine_pf());
        mDettagliItinerarioActivity.setLong_puntoFinale(itinerario.getLongitudine_pf());
        MapView map = mDettagliItinerarioActivity.getMap();

        GeoPoint startPoint = new GeoPoint(itinerario.getLatitudine_pi(), itinerario.getLongitudine_pi());
        mapController = map.getController();
        mapController.setZoom(12);
        mapController.setCenter(startPoint);

        mDettagliItinerarioActivity.setFrom(itinerario.getPuntoinizio());
        mDettagliItinerarioActivity.setTo(itinerario.getPuntofine());

        mDettagliItinerarioActivity.setTitolo(itinerario.getTitolo());
        mDettagliItinerarioActivity.setDescrizione(itinerario.getDescrizione());

        mDettagliItinerarioActivity.setDurata(itinerario.getDurata());
        mDettagliItinerarioActivity.setDifficulty(itinerario.getDifficulty());

        if(itinerario.getAccessodisabili())
            mDettagliItinerarioActivity.setAccessDisable("Sì");
        else
            mDettagliItinerarioActivity.setAccessDisable("No");

        mDettagliItinerarioActivity.setLunghezza(String.format("%.2f", itinerario.getLunghezza()));
        mDettagliItinerarioActivity.setAreaGeografica(itinerario.getAreageografica());
    }
}