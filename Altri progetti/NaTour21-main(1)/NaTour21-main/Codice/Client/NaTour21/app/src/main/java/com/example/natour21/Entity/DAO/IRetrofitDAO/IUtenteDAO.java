package com.example.natour21.Entity.DAO.IRetrofitDAO;

import android.telecom.Call;

import com.example.natour21.Entity.Utente;
import com.example.natour21.Utils.Handler.Callback;

import java.util.List;

public interface IUtenteDAO {

    void listUser(Callback callback);

    void getUser(String username, Callback callback);

    void getUserSocial(Callback callback);

    void postUser(Utente user, Callback callback);

    void putUser(Utente user, Callback callback);

    void deleteUser(String username, Callback callback);

}
