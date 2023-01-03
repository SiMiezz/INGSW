package com.example.natour21.Utils.Handler;

import android.app.Activity;
import android.util.Log;

import com.amplifyframework.storage.StorageException;

import es.dmoral.toasty.Toasty;

public class HandlerStorage {

    //Constructor private
    public HandlerStorage() {}

    public static void HandleStore(Throwable e, Activity a) {
        Log.e("HandlerAmplify","Called the error handler storage.");
        if(a==null)
            return;

        if (e instanceof StorageException) {
            Log.e("HandlerAmplify","StorageException");
            Log.e("HandlerAmplify",e.getMessage());
            a.runOnUiThread(() -> Toasty.error(a,"Caricamento media fallito, riprovare.",
                    Toasty.LENGTH_SHORT,true).show());
        }else {
            Log.e("HandlerAmplify","StorageException");
            Log.e("HandlerAmplify",e.getMessage());
            a.runOnUiThread(() -> Toasty.error(a,"Errore generico, riprovare.",
                    Toasty.LENGTH_SHORT,true).show());
        }
    }
}
