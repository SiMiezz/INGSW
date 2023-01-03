package com.example.natour21.Utils.Handler;

import android.app.Activity;
import android.util.Log;

import com.example.natour21.Utils.Throwable.NoAuthorizedGuestException;
import com.example.natour21.Utils.Throwable.NoGpsConnectionException;
import com.example.natour21.Utils.Throwable.NoInternetConnectionException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import es.dmoral.toasty.Toasty;
import retrofit2.HttpException;

public class Handler {

    //Constructor private
    private Handler() {}

    public static void HandleError(Throwable t, Activity a) {
        Log.e("Handler","Called the error handler.");
        if(a==null)
            return;

        if(t instanceof HttpException) {
            HttpException http = (HttpException) t;
            String informationService;
            switch (http.code()) {
                case 400:
                    Log.e("Handler", "ERRORE 400");
                    informationService = http.response().headers().get("Information-Service").toString();
                    Log.e("Handler", informationService);
                    a.runOnUiThread(() -> Toasty.info(a,informationService,
                            Toasty.LENGTH_SHORT,true).show());
                    break;
                case 404:
                    Log.e("Handler", "ERRORE 404");
                    informationService = http.response().headers().get("Information-Service").toString();
                    Log.e("Handler", informationService);
                    a.runOnUiThread(() -> Toasty.info(a,informationService,
                            Toasty.LENGTH_SHORT,true).show());
                    break;
                case 500:
                    Log.e("Handler", "ERRORE 500");
                    Log.e("Handler", "Errore interno del server.");
                    a.runOnUiThread(() -> Toasty.error(a,"Errore interno del server.",
                            Toasty.LENGTH_SHORT,true).show());
                    break;
                default:
                    Log.e("Handler", "Errore generico del server.");
                    a.runOnUiThread(() -> Toasty.error(a,"Errore generico del server, riprovare.",
                            Toasty.LENGTH_SHORT,true).show());
                    break;
            }

        }else if(t instanceof ConnectException) {
            Log.e("Handler", "Errore Connessione connessione scarsa o server irraggiungibile.");
            a.runOnUiThread(() -> Toasty.warning(a, "Problemi di rete, riprova.",
                    Toasty.LENGTH_SHORT, true).show());

        }else if(t instanceof NoAuthorizedGuestException) {
            Log.e("Handler", "Utente guest non autenticato.");
            a.runOnUiThread(() -> Toasty.warning(a, "Utente non autenticato.",
                    Toasty.LENGTH_SHORT, true).show());

        }else if(t instanceof NoInternetConnectionException) {
            Log.e("Handler", "Errore Network non attivo.");
            a.runOnUiThread(() -> Toasty.info(a, "Nessuna connessione internet attiva.",
                    Toasty.LENGTH_SHORT, true).show());

        }else if(t instanceof NoGpsConnectionException) {
            Log.e("Handler", "Errore GPS non attivo.");
            a.runOnUiThread(() -> Toasty.info(a, "Provider GPS disattivato.",
                    Toasty.LENGTH_SHORT, true).show());

        }else if(t instanceof SocketTimeoutException) {
            Log.e("Handler", "SocketTimeoutException");
            a.runOnUiThread(() -> Toasty.error(a, "Problema di connessione al server.",
                    Toasty.LENGTH_SHORT, true).show());

        }else {
            Log.e("Handler", "Errore sconosciuto.");
            a.runOnUiThread(() -> Toasty.error(a,"Errore imprevisto :(.",
                    Toasty.LENGTH_SHORT,true).show());

        }
    }
}
