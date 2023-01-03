package com.example.natour21.Utils.Other;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.natour21.Entity.Utente;
import com.example.natour21.Utils.Handler.Handler;
import com.example.natour21.Utils.Throwable.NoAuthorizedGuestException;

public class LocalUser {

    public static Utente getLocalUser(Context c) {
        Log.i("LocalUser","Getting local user.");
        Utente utente = new Utente();
        SharedPreferences sharedPreferences = c.getSharedPreferences("local_user",MODE_PRIVATE);
        utente.setUsername(sharedPreferences.getString("username","Guest"));
        utente.setEmail(sharedPreferences.getString("email","guest.email@natour21.com"));
        utente.setNome(sharedPreferences.getString("nome","Guest"));
        utente.setCognome(sharedPreferences.getString("cognome","Guest"));
        utente.setPhotolnk(sharedPreferences.getString("photolnk",""));
        return utente;
    }

    public static String getLocalEmail(Context c) {
        SharedPreferences sharedPreferences = c.getSharedPreferences("local_user",MODE_PRIVATE);
        return sharedPreferences.getString("email","guest.email@natour21.com");
    }

    public static String getLocalUsername(Context c) {
        SharedPreferences sharedPreferences = c.getSharedPreferences("local_user",MODE_PRIVATE);
        return sharedPreferences.getString("username","Guest");
    }

    public static String getLocalNameSurname(Context c) {
        SharedPreferences sharedPreferences = c.getSharedPreferences("local_user",MODE_PRIVATE);
        String result = sharedPreferences.getString("nome","Guest");
        result = result + " " +sharedPreferences.getString("cognome","Guest");
        return result;
    }

    public static boolean isGuest(Context c) {
        SharedPreferences sharedPreferences = c.getSharedPreferences("local_user", MODE_PRIVATE);
        if (sharedPreferences.getString("username", "Guest").equals("Guest")) {
            Handler.HandleError(new NoAuthorizedGuestException(), (Activity) c);
            return true;
        }else
            return false;
    }

    public static void setLocalUser(Context c, Utente utente) {
        Log.i("LocalUser","Setting local user.");
        SharedPreferences sharedPreferences = c.getSharedPreferences("local_user",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", utente.getUsername());
        editor.putString("email", utente.getEmail());
        editor.putString("nome", utente.getNome());
        editor.putString("cognome", utente.getCognome());
        editor.putString("photolnk", utente.getPhotolnk());
        editor.apply();
    }

    public static void deleteLocalUser(Context c) {
        Log.i("LocalUser","Deleteing local user.");
        SharedPreferences sharedPreferences = c.getSharedPreferences("local_user",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

}
