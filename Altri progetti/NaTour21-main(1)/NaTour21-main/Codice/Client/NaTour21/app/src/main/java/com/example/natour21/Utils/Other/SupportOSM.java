package com.example.natour21.Utils.Other;

import org.osmdroid.api.IMapController;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;


public class SupportOSM {

    MapView map;
    IMapController mapController;

    public SupportOSM(MapView map, IMapController mapController) {
        map.setMultiTouchControls(true);
        mapController.setZoom(10);
    }

    public void setStartPoint(IMapController mapController, double latitudine, double longitudine) {
        GeoPoint startPoint = new GeoPoint(latitudine, longitudine);
    }

}
