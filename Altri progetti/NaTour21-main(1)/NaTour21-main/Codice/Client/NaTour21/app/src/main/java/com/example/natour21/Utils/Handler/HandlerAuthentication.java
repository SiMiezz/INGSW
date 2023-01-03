package com.example.natour21.Utils.Handler;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.amplifyframework.auth.AuthException;
import com.amplifyframework.rx.RxAmplify;

import es.dmoral.toasty.Toasty;

public class HandlerAuthentication {

    //Constructor private
    public HandlerAuthentication() {}

    public static void HandleAuth(Throwable e, Activity a) {
        Log.e("HandlerAmplify","Called the error handler auth.");
        if(a==null)
            return;

        if (e instanceof AuthException.UserNotFoundException) {
            Log.e("HandlerAmplify","UserNotFoundException");
            Log.e("HandlerAmplify",e.getMessage());
            a.runOnUiThread(() -> Toasty.error(a,"Utente non trovato.",
                    Toasty.LENGTH_SHORT,true).show());

        }else if (e instanceof AuthException.UserNotConfirmedException) {
            Log.e("HandlerAmplify","UserNotConfirmedException");
            Log.e("HandlerAmplify",e.getMessage());
            a.runOnUiThread(() -> Toasty.error(a,"Utente non confermato.",
                    Toasty.LENGTH_SHORT,true).show());

        }else if (e instanceof AuthException.UsernameExistsException) {
            Log.e("HandlerAmplify","UsernameExistsException");
            Log.e("HandlerAmplify",e.getMessage());
            a.runOnUiThread(() -> Toasty.error(a,"Username già presente.",
                    Toasty.LENGTH_SHORT,true).show());

        }else if (e instanceof AuthException.AliasExistsException) {
            Log.e("HandlerAmplify","AliasExistsException");
            Log.e("HandlerAmplify",e.getMessage());
            a.runOnUiThread(() -> Toasty.error(a,"Alias inesistente.",
                    Toasty.LENGTH_SHORT,true).show());

        }else if (e instanceof AuthException.InvalidPasswordException) {
            Log.e("HandlerAmplify","InvalidPasswordException");
            Log.e("HandlerAmplify",e.getMessage());
            a.runOnUiThread(() -> Toasty.error(a,"Password non valida.",
                    Toasty.LENGTH_SHORT,true).show());

        }else if (e instanceof AuthException.InvalidParameterException) {
            Log.e("HandlerAmplify","InvalidParameterException");
            Log.e("HandlerAmplify",e.getMessage());
            a.runOnUiThread(() -> Toasty.error(a,"Parametri non validi.",
                    Toasty.LENGTH_SHORT,true).show());;

        }else if (e instanceof AuthException.CodeExpiredException) {
            Log.e("HandlerAmplify","CodeExpiredException");
            Log.e("HandlerAmplify",e.getMessage());
            a.runOnUiThread(() -> Toasty.error(a,"Codice scaduto.",
                    Toasty.LENGTH_SHORT,true).show());

        }else if (e instanceof AuthException.CodeMismatchException) {
            Log.e("HandlerAmplify","CodeMismatchException");
            Log.e("HandlerAmplify",e.getMessage());
            a.runOnUiThread(() -> Toasty.error(a,"Codice non valido.",
                    Toasty.LENGTH_SHORT,true).show());

        }else if (e instanceof AuthException.CodeDeliveryFailureException) {
            Log.e("HandlerAmplify","CodeDeliveryFailureException");
            Log.e("HandlerAmplify",e.getMessage());
            a.runOnUiThread(() -> Toasty.error(a,"Impossibile inivare il codice, riprovare.",
                    Toasty.LENGTH_SHORT,true).show());

        }else if (e instanceof AuthException.LimitExceededException) {
            Log.e("HandlerAmplify","LimitExceededException");
            Log.e("HandlerAmplify",e.getMessage());
            a.runOnUiThread(() -> Toasty.error(a,"Limiti superati.",
                    Toasty.LENGTH_SHORT,true).show());

        }else if (e instanceof AuthException.MFAMethodNotFoundException) {
            Log.e("HandlerAmplify","MFAMethodNotFoundException");
            Log.e("HandlerAmplify",e.getMessage());
            a.runOnUiThread(() -> Toasty.error(a,"MFA non trovato.",
                    Toasty.LENGTH_SHORT,true).show());

        }else if (e instanceof AuthException.NotAuthorizedException) {
            Log.e("HandlerAmplify","NotAuthorizedException");
            Log.e("HandlerAmplify",e.getMessage());
            a.runOnUiThread(() -> Toasty.error(a,"Username o password errata.",
                    Toasty.LENGTH_SHORT,true).show());

        }else if (e instanceof AuthException.ResourceNotFoundException) {
            Log.e("HandlerAmplify","ResourceNotFoundException");
            Log.e("HandlerAmplify",e.getMessage());
            a.runOnUiThread(() -> Toasty.error(a,"Risorsa non trovata.",
                    Toasty.LENGTH_SHORT,true).show());

        }else if (e instanceof AuthException.SoftwareTokenMFANotFoundException) {
            Log.e("HandlerAmplify","SoftwareTokenMFANotFoundException");
            Log.e("HandlerAmplify",e.getMessage());
            a.runOnUiThread(() -> Toasty.error(a,"MFA Token non trovato.",
                    Toasty.LENGTH_SHORT,true).show());

        }else if (e instanceof AuthException.FailedAttemptsLimitExceededException) {
            Log.e("HandlerAmplify","FailedAttemptsLimitExceededException");
            Log.e("HandlerAmplify",e.getMessage());
            a.runOnUiThread(() -> Toasty.error(a,"Tentativi di accesso superati, riporvare più tardi.",
                    Toasty.LENGTH_SHORT,true).show());

        }else if (e instanceof AuthException.TooManyRequestsException) {
            Log.e("HandlerAmplify","TooManyRequestsException");
            Log.e("HandlerAmplify",e.getMessage());
            a.runOnUiThread(() -> Toasty.error(a,"Troppe richieste, attendere.",
                    Toasty.LENGTH_SHORT,true).show());

        }else if (e instanceof AuthException.PasswordResetRequiredException) {
            Log.e("HandlerAmplify","PasswordResetRequiredException.");
            Log.e("HandlerAmplify",e.getMessage());
            a.runOnUiThread(() -> Toasty.error(a,"Richiesto reset della password.",
                    Toasty.LENGTH_SHORT,true).show());

        }else{
            Log.e("HandlerAmplify","Generic error.");
            Log.e("HandlerAmplify",e.getMessage());
            a.runOnUiThread(() -> Toasty.error(a,"Errore generico, riprovare.",
                    Toasty.LENGTH_SHORT,true).show());
        }
    }
}
