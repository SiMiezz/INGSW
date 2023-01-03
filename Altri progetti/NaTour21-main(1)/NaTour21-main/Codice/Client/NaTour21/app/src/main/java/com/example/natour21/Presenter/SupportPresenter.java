package com.example.natour21.Presenter;

import android.content.Intent;
import android.util.Log;

import com.example.natour21.R;
import com.example.natour21.View.Fragment.SupportFragment;

import es.dmoral.toasty.Toasty;

public class SupportPresenter {

    private final String TAG = "AssistenzaPresenter";
    private final SupportFragment mSupportFragment;

    public SupportPresenter(SupportFragment mSupportFragment) {
        this.mSupportFragment = mSupportFragment;
    }

    public void sendEmail(String subject, String message) {
        Log.i(TAG,"Invio dell'email in corso.");
        if(verifica(subject,message)) {
            final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setType("text/plain");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"support.natour21@libero.it"});
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);

            emailIntent.setType("message/rfc822");

            try {
                mSupportFragment.startActivity(Intent.createChooser(emailIntent, "Invia email con..."));
                mSupportFragment.clear();
            } catch (android.content.ActivityNotFoundException ex) {
                Toasty.error(mSupportFragment.getActivity(), R.string.erroreClientEmail,
                        Toasty.LENGTH_SHORT, true).show();
            }
        }
    }

    private boolean verifica(String subject, String message) {
        Log.i(TAG,"Verificia campi support.");
        boolean verifica = true;
        if (subject.isEmpty()) {
            mSupportFragment.setSubjectError("L'oggetto non può essere vuoto.");
            verifica = false;
        }
        if (message.isEmpty()) {
            mSupportFragment.setMessaggioError("Il messaggio non può essere vuoto.");
            verifica = false;
        }
        if(subject.length()>25) {
            mSupportFragment.setSubjectError("Lunghezza massima: 25.");
            verifica = false;
        }
        if(message.length()>250) {
            mSupportFragment.setMessaggioError("Lunghezza massima: 250.");
            verifica = false;
        }
        return verifica;
    }
}


