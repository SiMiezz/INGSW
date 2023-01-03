package com.example.natour21.Utils.Other;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.natour21.R;
import com.example.natour21.Utils.Enumeration.API;
import com.example.natour21.Utils.Handler.Callback;
import com.example.natour21.Utils.Handler.Handler;
import com.example.natour21.Utils.Request.Instance.ServerUtils;
import com.example.natour21.Utils.Request.RequestGenerator;
import com.example.natour21.Utils.Throwable.NoGpsConnectionException;
import com.example.natour21.Utils.Throwable.NoInternetConnectionException;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;

import es.dmoral.toasty.Toasty;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class Service {

    private static final String TAG = "CHECK_SERVICE";

    public static final int CAMERA_PERMISSION_CODE = 111;
    public static int STORAGE_PERMISSION_CODE = 222;
    public static int GPS_PERMISSION_CODE = 333;
    public static final int NETWORK_PERMISSION_CODE = 444;
    public static final int READ_GPX_FILE_REQUEST_CODE = 555;

    private static final ServerUtils serverUtils = RequestGenerator
            .retrofitInstance(API.SERVER_API)
            .create(ServerUtils.class);

    //Constructor private
    private Service() {}

    //Check is permission is granted
    public static boolean checkCameraEnabled(Activity context, int requestCode) {
        Log.i(TAG, "checkCameraEnabled: started.");
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            requestCamera(context, requestCode);
            return false;
        }
    }

    public static boolean checkStorageEnabled(Activity context, int requestCode) {
        Log.i(TAG, "checkStorageEnabled: started.");
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            requestStorage(context, requestCode);
            return false;
        }
    }

    public static boolean checkGpsEnabled(Activity context, int requestCode) {
        Log.i(TAG, "checkGpsEnabled: started.");
        if (checkFineLocation(context) && checkCoarseLocation(context)) {
            return true;
        } else {
            requestGPS(context, requestCode);
            return false;
        }
    }

    private static boolean checkFineLocation(Context context) {
        Log.i(TAG, "checkFineLocation: started.");
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private static boolean checkCoarseLocation(Context context) {
        Log.i(TAG, "checkCoarseLocation: started.");
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean checkNetworkEnabled(Activity context, int requestCode) {
        Log.i(TAG, "checkNetworkEnabled: started.");
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            requestNetwork(context, requestCode);
            return false;
        }
    }

    private static boolean checkChangeNetworkLocation(Context context) {
        Log.i(TAG, "checkChangeNetworkLocation: started.");
        return ContextCompat.checkSelfPermission(context, Manifest.permission.CHANGE_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED;
    }

    private static boolean checkAccessNetworkLocation(Context context) {
        Log.i(TAG, "checkAccessNetworkLocation: started.");
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED;
    }

    //Check if activated
    public static boolean isGpsOnline(Context context) {
        Log.i(TAG, "isGpsOnline: started.");
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return (locationManager != null) && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static void isServerOnline(Callback call) {
        Log.i(TAG, "isServerOnline: started.");
        
        serverUtils.getServiceStatus()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {}
                    @Override
                    public void onComplete() {
                        call.onSuccess(null);
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        call.onFailure(e);
                    }
                });
    }

    public static boolean pingServer() {
        Log.i(TAG, "isServerOnline: started.");

        String hostName = "192.168.1.53";
        int port = 8080;
        int timeout = 3000;

        SocketAddress socketAddress = new InetSocketAddress(hostName, port);
        Socket socket = new Socket();

        try {
            socket.connect(socketAddress, timeout);
            socket.close();
            Log.i(TAG, "isServerOnline: online.");
            return true;
        } catch (SocketTimeoutException exception) {
            Log.e(TAG, "SocketTimeoutException " + hostName + ":" + port + ". " + exception.getMessage());
            return false;
        } catch (IOException exception) {
            Log.e(TAG, "IOException - Unable to connect to " + hostName + ":" + port + ". " + exception.getMessage());
            return false;
        }
    }

    public static boolean isOnline(Context context) {
        Log.i(TAG, "isOnline: started.");
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return (ni != null && ni.isConnected());
    }

    //Request permissions
    private static void requestCamera(Activity context, int requestCode) {
        Log.i(TAG, "requestCamera: started.");
            new MaterialAlertDialogBuilder(context, R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog)
                    .setTitle("Utilizzo fotocamera Necessario.")
                    .setMessage("Impostazioni->App->NaTour21->Permessi e Autorizzazioni.")
                    .setPositiveButton("Va bene", (dialog, which) -> ActivityCompat.requestPermissions(context,
                            new String[] {Manifest.permission.CAMERA}, requestCode))
//                    .setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    })
                    .create().show();
        }

    private static void requestStorage(Activity context, int requestCode) {
        Log.i(TAG, "requestStorage: started.");
        new MaterialAlertDialogBuilder(context, R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog)
                .setTitle("Utilizzo accesso memoria interna necessario.")
                .setMessage("Impostazioni->App->NaTour21->Permessi e Autorizzazioni.")
                .setPositiveButton("Va bene", (dialog, which) -> ActivityCompat.requestPermissions(context,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, requestCode))
//                    .setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    })
                .create().show();
    }

    private static void requestGPS(Activity context, int requestCode) {
        Log.i(TAG, "requestGPS: started.");
        new MaterialAlertDialogBuilder(context, R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog)
                .setTitle("Utilizzo accesso posizionale necessario.")
                .setMessage("Impostazioni->App->NaTour21->Permessi e Autorizzazioni.")
                .setPositiveButton("Va bene", (dialog, which) -> {
                    ActivityCompat.requestPermissions(context,
                            new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, requestCode);
                    ActivityCompat.requestPermissions(context,
                            new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, requestCode);
                })
//                    .setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    })
                .create().show();
    }

    private static void requestNetwork(Activity context, int requestCode) {
        Log.i(TAG, "requestNetwork: started.");
        new MaterialAlertDialogBuilder(context, R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog)
                .setTitle("Utilizzo network necessario.")
                .setMessage("Impostazioni->App->NaTour21->Permessi e Autorizzazioni.")
                .setPositiveButton("Va bene", (dialog, which) -> {
//                        ActivityCompat.requestPermissions((Activity) context,
//                                new String[] {Manifest.permission.CHANGE_NETWORK_STATE}, requestCode);
//                        ActivityCompat.requestPermissions((Activity) context,
//                                    new String[] {Manifest.permission.ACCESS_NETWORK_STATE}, requestCode);
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[] {Manifest.permission.INTERNET}, requestCode);
                })
//                    .setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    })
                .create().show();
    }
}
