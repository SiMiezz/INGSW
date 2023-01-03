package com.example.natour21.Presenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.example.natour21.Entity.DAO.FactoryDAO;
import com.example.natour21.Entity.DAO.IRetrofitDAO.IInterestingPointDAO;
import com.example.natour21.Entity.DAO.IRetrofitDAO.IItinerarioDAO;
import com.example.natour21.Entity.DAO.IRetrofitDAO.ITappaDAO;
import com.example.natour21.Entity.InterestingPoint;
import com.example.natour21.Entity.Itinerario;
import com.example.natour21.Entity.Tappa;
import com.example.natour21.R;
import com.example.natour21.Utils.Handler.Callback;
import com.example.natour21.Utils.Handler.Handler;
import com.example.natour21.Utils.Handler.HandlerStorage;
import com.example.natour21.Utils.Other.UploadS3;
import com.example.natour21.View.Other.HomePageActivity;
import com.example.natour21.View.Other.PubblicaItinerarioActivity;

import org.osmdroid.api.IMapController;
import org.osmdroid.api.IMapView;
import org.osmdroid.bonuspack.location.GeocoderNominatim;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;
import org.xmlpull.v1.XmlPullParserException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import io.ticofab.androidgpxparser.parser.GPXParser;
import io.ticofab.androidgpxparser.parser.domain.Gpx;
import io.ticofab.androidgpxparser.parser.domain.WayPoint;

public class PubblicaItinerarioPresenter {

    private final String MY_USER_AGENT = "MyOwnUserAgent/1.0";
    private final String TAG = "PubblicaItinerarioPresenter";

    private final PubblicaItinerarioActivity mPubblicaItinerarioActivity;

    public PubblicaItinerarioPresenter(PubblicaItinerarioActivity mPubblicaItinerarioActivity) {
        this.mPubblicaItinerarioActivity = mPubblicaItinerarioActivity;
    }

    public void pulisciCampi(Road[] road, Polyline[] roadOverlay) {
        Log.i(TAG,"Clear totale campi.");
        MapView map = mPubblicaItinerarioActivity.getMap();
        ArrayList<Marker> markers = mPubblicaItinerarioActivity.getMarkers();
        ArrayList<Marker> markersInterestingPoints = mPubblicaItinerarioActivity.getMarkersInterestingPoints();
        List<InterestingPoint> interestingPoints = mPubblicaItinerarioActivity.getInterestingPoints();
        ArrayList<GeoPoint> rout = mPubblicaItinerarioActivity.getRoute();
        GeoPoint startPoint = mPubblicaItinerarioActivity.getStartPoint();
        GeoPoint endPoint = mPubblicaItinerarioActivity.getEndPoint();

        mPubblicaItinerarioActivity.setPuntoDiArrivoDefaultEditText();
        mPubblicaItinerarioActivity.setPuntoDiPartenzaDefaultEditText();

        mPubblicaItinerarioActivity.setTitoloDefaultEditText();
        mPubblicaItinerarioActivity.setDescrizioneDefaultEditText();
        mPubblicaItinerarioActivity.setDurataDefaultTextView();
        mPubblicaItinerarioActivity.setLunghezzaDefaultTextView();
        mPubblicaItinerarioActivity.setAccessibilitaDisabiliDefaultSwitch();
        mPubblicaItinerarioActivity.setStartPoint(null);
        mPubblicaItinerarioActivity.setEndPoint(null);

        for (Marker m : markers)
            map.getOverlays().remove(m);
        for (Marker m : markersInterestingPoints)
            map.getOverlays().remove(m);
        markers.clear();
        interestingPoints.clear();
        markersInterestingPoints.clear();
        rout.clear();
        map.getOverlays().remove(roadOverlay[0]);
        map.invalidate();
        mPubblicaItinerarioActivity.setLunghezzaDefaultTextView();
        mPubblicaItinerarioActivity.setDurataDefaultTextView();
    }

    public void pulisciCampi() {
        Log.i(TAG,"Clear totale mappa.");
        MapView map = mPubblicaItinerarioActivity.getMap();
        ArrayList<Marker> markers = mPubblicaItinerarioActivity.getMarkers();
        ArrayList<Marker> markersInterestingPoints = mPubblicaItinerarioActivity.getMarkersInterestingPoints();
        List<InterestingPoint> interestingPoints = mPubblicaItinerarioActivity.getInterestingPoints();
        ArrayList<GeoPoint> rout = mPubblicaItinerarioActivity.getRoute();
        GeoPoint startPoint = mPubblicaItinerarioActivity.getStartPoint();
        GeoPoint endPoint = mPubblicaItinerarioActivity.getEndPoint();

        mPubblicaItinerarioActivity.setPuntoDiArrivoDefaultEditText();
        mPubblicaItinerarioActivity.setPuntoDiPartenzaDefaultEditText();

        mPubblicaItinerarioActivity.setTitoloDefaultEditText();
        mPubblicaItinerarioActivity.setDescrizioneDefaultEditText();
        mPubblicaItinerarioActivity.setDurataDefaultTextView();
        mPubblicaItinerarioActivity.setLunghezzaDefaultTextView();
        mPubblicaItinerarioActivity.setAccessibilitaDisabiliDefaultSwitch();
        mPubblicaItinerarioActivity.setStartPoint(null);
        mPubblicaItinerarioActivity.setEndPoint(null);

        for (Marker m : markers)
            map.getOverlays().remove(m);
        for (Marker m : markersInterestingPoints)
            map.getOverlays().remove(m);
        markers.clear();
        interestingPoints.clear();
        markersInterestingPoints.clear();
        rout.clear();
        map.invalidate();
        mPubblicaItinerarioActivity.setLunghezzaDefaultTextView();
        mPubblicaItinerarioActivity.setDurataDefaultTextView();
    }


    public void aggiungiTappa(GeoPoint p, Road[] road, Polyline[] roadOverlay) {
        Log.i(TAG,"Adding new tappa.");
        MapView map = mPubblicaItinerarioActivity.getMap();
        ArrayList<Marker> markers = mPubblicaItinerarioActivity.getMarkers();
        ArrayList<GeoPoint> rout = mPubblicaItinerarioActivity.getRoute();
        RoadManager roadManager = mPubblicaItinerarioActivity.getRoadManager();
        GeoPoint startPoint = mPubblicaItinerarioActivity.getStartPoint();
        GeoPoint endPoint = mPubblicaItinerarioActivity.getEndPoint();
        IMapController mapController = map.getController();

        Marker newMarker = new Marker(map);
        newMarker.setPosition(p);
        newMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        newMarker.setIcon(mPubblicaItinerarioActivity.getResources().getDrawable(R.mipmap.placeholder_tappa));
        newMarker.setOnMarkerClickListener((marker, mapView) -> false);
        map.getOverlays().add(newMarker);

        markers.add(newMarker);
        map.getOverlays().remove(roadOverlay[0]);
        rout.add(p);
        road[0] = roadManager.getRoad(rout);
        roadOverlay[0] = roadManager.buildRoadOverlay(road[0]);

        map.getOverlays().add(roadOverlay[0]);
        map.invalidate();

        Double totalSecs = road[0].mDuration;
        int hours = totalSecs.intValue() / 3600;
        int minutes = (totalSecs.intValue() % 3600) / 60;
        double kmItinerario = road[0].mLength;
        if (hours <= 23) {

            mPubblicaItinerarioActivity.setDurataTextView(hours, minutes);
            mPubblicaItinerarioActivity.setLunghezzaTextView(kmItinerario);
            mPubblicaItinerarioActivity.setOre(hours);
            mPubblicaItinerarioActivity.setMinuti(minutes);
            mPubblicaItinerarioActivity.setLunghezza(kmItinerario);

            if (markers.size() == 1) {
                GeocoderNominatim gn = new GeocoderNominatim(MY_USER_AGENT);
                try {
                    List<Address> addresses = gn.getFromLocation(p.getLatitude(), p.getLongitude(), 1);
                    mPubblicaItinerarioActivity.setPuntoDiPartenzaEditText(addresses.get(0).getAddressLine(0));
                    mPubblicaItinerarioActivity.setStartPoint(p);
                    mPubblicaItinerarioActivity.setAreaGeograficaEditText(addresses.get(0).getLocality());
                    mapController.setCenter(new GeoPoint(addresses.get(0).getLatitude(), addresses.get(0).getLongitude()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (markers.size() > 1) {
                GeocoderNominatim gn = new GeocoderNominatim(MY_USER_AGENT);
                try {
                    List<Address> addresses = gn.getFromLocation(p.getLatitude(), p.getLongitude(), 1);
                    mPubblicaItinerarioActivity.setPuntoDiArrivoEditText(addresses.get(0).getAddressLine(0));
                    mPubblicaItinerarioActivity.setEndPoint(p);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Toasty.info(mPubblicaItinerarioActivity, "Percorso troppo lungo.",
                    Toasty.LENGTH_SHORT, true).show();
            map.getOverlays().remove(markers.get(markers.size() - 1));
            markers.remove(markers.size() - 1);
            map.getOverlays().remove(roadOverlay[0]);
            rout.remove(rout.size() - 1);
            road[0] = roadManager.getRoad(rout);
            roadOverlay[0] = roadManager.buildRoadOverlay(road[0]);

            map.getOverlays().add(roadOverlay[0]);
            map.invalidate();
        }
    }

    public void eliminaTappa(Road[] road, Polyline[] roadOverlay) {
        Log.i(TAG,"Delete last tappa.");
        MapView map = mPubblicaItinerarioActivity.getMap();
        ArrayList<Marker> markers = mPubblicaItinerarioActivity.getMarkers();
        ArrayList<GeoPoint> rout = mPubblicaItinerarioActivity.getRoute();
        RoadManager roadManager = mPubblicaItinerarioActivity.getRoadManager();
        GeoPoint startPoint = mPubblicaItinerarioActivity.getStartPoint();
        GeoPoint endPoint = mPubblicaItinerarioActivity.getEndPoint();

        map.getOverlays().remove(markers.get(markers.size() - 1));
        markers.remove(markers.size() - 1);
        map.getOverlays().remove(roadOverlay[0]);
        rout.remove(rout.size() - 1);
        if (rout.size() != 0) {

            if (rout.size() == 1) {
                mPubblicaItinerarioActivity.setPuntoDiArrivoDefaultEditText();
                mPubblicaItinerarioActivity.setEndPoint(null);
            } else {
                GeocoderNominatim gn = new GeocoderNominatim(MY_USER_AGENT);
                try {
                    List<Address> addresses = gn.getFromLocation(rout.get(rout.size() - 1).getLatitude(), rout.get(rout.size() - 1).getLongitude(), 1);
                    mPubblicaItinerarioActivity.setPuntoDiArrivoEditText(addresses.get(0).getAddressLine(0));
                    mPubblicaItinerarioActivity.setEndPoint(rout.get(rout.size() - 1));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            road[0] = roadManager.getRoad(rout);
            roadOverlay[0] = roadManager.buildRoadOverlay(road[0]);

            map.getOverlays().add(roadOverlay[0]);

            Double totalSecs = road[0].mDuration;
            int hours = totalSecs.intValue() / 3600;
            int minutes = (totalSecs.intValue() % 3600) / 60;
            double kmItinerario = road[0].mLength;
            mPubblicaItinerarioActivity.setDurataTextView(hours, minutes);
            mPubblicaItinerarioActivity.setLunghezzaTextView(kmItinerario);
        } else {
            mPubblicaItinerarioActivity.setPuntoDiPartenzaDefaultEditText();
            map.getOverlays().remove(markers);
            map.getOverlays().remove(roadOverlay);
            mPubblicaItinerarioActivity.setStartPoint(null);
            mPubblicaItinerarioActivity.setEndPoint(null);
        }
        map.invalidate();
    }

    public void saveItinerario() {
        Log.i(TAG, "Salvataggio itinerario.");
        mPubblicaItinerarioActivity.openPopupLoading();
        final IItinerarioDAO itinerarioDAO = FactoryDAO.getItinerarioDAO();
        List<InterestingPoint> interestingPoints = mPubblicaItinerarioActivity.getInterestingPoints();
        ArrayList<GeoPoint> route = mPubblicaItinerarioActivity.getRoute();

        Itinerario newItinerario;
        newItinerario = mPubblicaItinerarioActivity.getItinerario();
        itinerarioDAO.createItinerario(newItinerario, new Callback() {
            @Override
            public void onSuccess(Object o) {
                Log.i(TAG, "onSuccess: saveItinerario started.");
                Long id_itinerario = (Long) o;

                Map<String, String> poiUri = new HashMap<>();

                saveTappe(route, id_itinerario);
                saveInterestingPoints(interestingPoints, poiUri, id_itinerario);
                uploadInterestingPoits(poiUri);

                Toasty.success(mPubblicaItinerarioActivity, "Itinerario pubblicato con successo.",
                        Toasty.LENGTH_SHORT, true).show();

                mPubblicaItinerarioActivity.clear();

                mPubblicaItinerarioActivity.closePopupLoading();
                Intent newIntent = new Intent(mPubblicaItinerarioActivity, HomePageActivity.class);
                mPubblicaItinerarioActivity.startActivity(newIntent);
            }

            @Override
            public void onFailure(Throwable e) {
                Log.e(TAG, "onError: saveItinerario started.");
                Log.e(TAG, e.getLocalizedMessage());
                mPubblicaItinerarioActivity.closePopupLoading();
                Handler.HandleError(e, mPubblicaItinerarioActivity);
            }
        });
    }

    private void saveTappe(ArrayList<GeoPoint> route,
                           Long id_itinerario) {
        Log.i(TAG, "Salvataggio tappe.");
        List<Tappa> tappe = new ArrayList<>();
        if (route.size() > 2) {
            int i = 0;
            for (GeoPoint g : route) {
                Tappa tappa = new Tappa(-1L, id_itinerario, "Tappa-" + id_itinerario + "-" + i,
                        g.getLatitude(), g.getLongitude(), i);
                tappe.add(tappa);
                i++;
            }
            tappe.remove(0);
            tappe.remove(tappe.size() - 1);

            final ITappaDAO tappaDAO = FactoryDAO.getTappaDAO();
            tappaDAO.createTappe(tappe, new Callback() {
                @Override
                public void onSuccess(Object o) {
                    Log.i(TAG, "onSuccess: saveTappe started.");
                }

                @Override
                public void onFailure(Throwable e) {
                    Log.e(TAG, "onError: saveTappe started.");
                    Log.e(TAG, e.getLocalizedMessage());
                    Handler.HandleError(e, mPubblicaItinerarioActivity);
                }
            });
        }
    }

    private void saveInterestingPoints(List<InterestingPoint> interestingPoints,
                                       Map<String, String> poiUri,
                                       Long id_itinerario) {
        Log.i(TAG, "Salvataggio POI.");
        if(!interestingPoints.isEmpty()) {
            for (InterestingPoint i : interestingPoints) {
                i.setId_itinerario(id_itinerario);
                String key = "POI-" + i.getId_itinerario().toString() +
                        "-" + i.getLatitudine().toString() +
                        "-" + i.getLongitudine().toString();
                poiUri.put(key, i.getUrlfoto());
                i.setUrlfoto(key);
            }
            final IInterestingPointDAO interestingPointDAO = FactoryDAO.getInterestingPointDAO();
            interestingPointDAO.createInterestingPoints(interestingPoints, new Callback() {
                @Override
                public void onSuccess(Object o) {
                    Log.i(TAG, "onSuccess: saveInterestingPoints started.");
                }

                @Override
                public void onFailure(Throwable e) {
                    Log.e(TAG, "onError: saveInterestingPoint started.");
                    Log.e(TAG, e.getLocalizedMessage());
                    Handler.HandleError(e, mPubblicaItinerarioActivity);
                }
            });
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    public void inserisciInterestingPoint(Intent data) {
        Log.i(TAG, "Inserimento Interesting Point.");
        MapView map = mPubblicaItinerarioActivity.getMap();
        List<InterestingPoint> interestingPoints = mPubblicaItinerarioActivity.getInterestingPoints();
        ArrayList<Marker> markersInterestingPoints = mPubblicaItinerarioActivity.getMarkersInterestingPoints();
        AlertDialog.Builder dialogBuilder;
        AlertDialog dialog;

        dialogBuilder = new AlertDialog.Builder(mPubblicaItinerarioActivity);
        final View popupView = mPubblicaItinerarioActivity.getLayoutInflater().inflate(R.layout.popup_aggiungi_interesting_point_registra_itinerario, null);

        EditText nomeInterestingPoint = (EditText) popupView.findViewById(R.id.editTextTitoloPopupRegistraItinerario);
        EditText descrizioneInterestingPoint = (EditText) popupView.findViewById(R.id.editTextDescrizionePopupPubblicaItinerario);
        ImageView fotoInterestinPoint = (ImageView) popupView.findViewById(R.id.imageViewPopupRegistraItinerario);
        Button confermaButton = (Button) popupView.findViewById(R.id.buttonConfermaFotoPopupRegistraItinerario);
        Button annullaButton = (Button) popupView.findViewById(R.id.buttonAnnullaFotoPopupRegistraItinerario);

        Float latitudine = null;
        Float longitudine = null;

        dialogBuilder.setView(popupView);
        dialog = dialogBuilder.create();
        dialog.show();

        try (InputStream inputStream = mPubblicaItinerarioActivity.getContentResolver().openInputStream(data.getData())) {
            ExifInterface exif = new ExifInterface(inputStream);
            float[] latlong = new float[2];
            if (exif.getLatLong(latlong)) {
                latitudine = latlong[0];
                longitudine = latlong[1];
            }
        } catch (IOException e) {
            Log.i("LatLong", "Latitudine e longitudine non presenti.");
            Toasty.error(mPubblicaItinerarioActivity, "Posizione non presente nella foto.",
                    Toasty.LENGTH_SHORT, true).show();
            e.printStackTrace();
            dialog.dismiss();
        }

        fotoInterestinPoint.setImageURI(data.getData());

        Float finalLatitudine = latitudine;
        Float finalLongitudine = longitudine;

        confermaButton.setOnClickListener(v -> {
            fotoInterestinPoint.setImageURI(data.getData());
            InterestingPoint newInterestingPoint = new InterestingPoint();
            if (finalLatitudine != null && finalLongitudine != null) {
                newInterestingPoint.setLatitudine(Double.valueOf(finalLatitudine));
                newInterestingPoint.setLongitudine(Double.valueOf(finalLongitudine));
            } else {
                Log.i("LatLong", "Latitudine e longitudine non presenti.");
                Toasty.error(mPubblicaItinerarioActivity, "Posizione non presente nella foto.",
                        Toasty.LENGTH_SHORT, true).show();
                dialog.dismiss();
            }

            newInterestingPoint.setDescrizione(descrizioneInterestingPoint.getText().toString());
            newInterestingPoint.setTitolo(nomeInterestingPoint.getText().toString());
            newInterestingPoint.setUrlfoto(data.getData().toString());

            interestingPoints.add(newInterestingPoint);

            if (finalLatitudine != null && finalLongitudine != null) {
                Marker poiMarker = new Marker(map);
                poiMarker.setTitle(nomeInterestingPoint.getText().toString());
                poiMarker.setSnippet(descrizioneInterestingPoint.getText().toString());
                poiMarker.setPosition(new GeoPoint(finalLatitudine, finalLongitudine));

                Log.i("LatLong", Double.valueOf(finalLatitudine) + "-" + Double.valueOf(finalLongitudine));

                poiMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                poiMarker.setIcon(mPubblicaItinerarioActivity.getResources().getDrawable(R.mipmap.placeholder_interestingpoint));
                Drawable poiPhoto;
                try {
                    InputStream inputStream = mPubblicaItinerarioActivity.getContentResolver().openInputStream(data.getData());
                    poiPhoto = Drawable.createFromStream(inputStream, data.getData().toString());
                    Bitmap b = ((BitmapDrawable)poiPhoto).getBitmap();
                    Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 200, 200, false);
                    poiPhoto = new BitmapDrawable(mPubblicaItinerarioActivity.getResources(), bitmapResized);
                } catch (FileNotFoundException e) {
                    poiPhoto = mPubblicaItinerarioActivity.getResources().getDrawable(R.drawable.app_logo);
                }
                poiMarker.setImage(poiPhoto);
                markersInterestingPoints.add(poiMarker);
                map.getOverlays().add(poiMarker);
                map.invalidate();
            } else {
                interestingPoints.remove(newInterestingPoint);
                Toasty.error(mPubblicaItinerarioActivity, "Posizione non presente nella foto.",
                        Toasty.LENGTH_SHORT, true).show();
            }
            dialog.dismiss();
        });

        annullaButton.setOnClickListener(v -> dialog.dismiss());
    }

    public void uploadDettagliFromRegistrazione(List<GeoPoint> geoPointList,
                                                List<InterestingPoint> listaInterestingPoints,
                                                Road[] road, Polyline[] roadOverlay) {
        Log.i(TAG, "Upload Dettagli registrazione.");
        MapView map = mPubblicaItinerarioActivity.getMap();
        ArrayList<Marker> markersInterestingPoints = mPubblicaItinerarioActivity.getMarkersInterestingPoints();
        for (GeoPoint g : geoPointList) {
            aggiungiTappa(g, road, roadOverlay);
        }
        mPubblicaItinerarioActivity.setInterestingPoints(listaInterestingPoints);
        for(InterestingPoint i : listaInterestingPoints) {
            Marker poiMarker = new Marker(map);
            poiMarker.setTitle(i.getTitolo());
            poiMarker.setSnippet(i.getDescrizione());
            poiMarker.setPosition(new GeoPoint(i.getLatitudine(), i.getLongitudine()));

            poiMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            poiMarker.setIcon(mPubblicaItinerarioActivity.getResources().getDrawable(R.mipmap.placeholder_interestingpoint));
            poiMarker.setImage(mPubblicaItinerarioActivity.getResources().getDrawable(R.drawable.app_logo));
            Drawable poiPhoto;
            try {
                Uri uri = Uri.parse(i.getUrlfoto());
                InputStream inputStream = mPubblicaItinerarioActivity.getContentResolver().openInputStream(uri);
                poiPhoto = Drawable.createFromStream(inputStream, uri.toString());
            } catch (FileNotFoundException e) {
                poiPhoto = mPubblicaItinerarioActivity.getResources().getDrawable(R.drawable.app_logo);
            }
            poiMarker.setImage(poiPhoto);
            markersInterestingPoints.add(poiMarker);
            map.getOverlays().add(poiMarker);
            map.invalidate();
        }
        mPubblicaItinerarioActivity.disableAll();
    }

    public void uploadGPXFile(Intent data, Road[] road, Polyline[] roadOverlay) throws FileNotFoundException, IOException, XmlPullParserException {
        Log.i(TAG, "Upload GPX file.");
        GPXParser parser = new GPXParser();
        Gpx parsedGpx;
        InputStream inputStream = mPubblicaItinerarioActivity.getContentResolver().openInputStream(data.getData());
        parsedGpx = parser.parse(inputStream);

        int hours = parsedGpx.getMetadata().getTime().getHourOfDay();
        List<WayPoint> wayPoints = parsedGpx.getWayPoints();

        if (hours < 24 && wayPoints.size() >= 2) {
            mPubblicaItinerarioActivity.clear();
            for (WayPoint w : wayPoints) {
                GeoPoint newGeoPoint = new GeoPoint(w.getLatitude(), w.getLongitude());
                aggiungiTappa(newGeoPoint, road, roadOverlay);
            }
        } else {
            Toasty.error(mPubblicaItinerarioActivity, "File non valido.",
                    Toasty.LENGTH_SHORT, true).show();
        }
    }

    private void uploadInterestingPoits(Map<String, String> poiUri) {
        Log.i(TAG, "Upload POI");
        for (String key : poiUri.keySet()) {
            Uri uri = Uri.parse(poiUri.get(key));
            UploadS3.upload(uri, key, mPubblicaItinerarioActivity, new Callback() {
                @Override
                public void onSuccess(Object o) {
                    Log.i(TAG, "onSuccess: uploadInterestingPoits started.");
                }
                @Override
                public void onFailure(Throwable e) {
                    Log.e(TAG, "onError: uploadInterestingPoits started.");
                    Log.e(TAG, e.getLocalizedMessage());
                    HandlerStorage.HandleStore(e, mPubblicaItinerarioActivity);
                }
            });
        }
    }
}