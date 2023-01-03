package com.example.natour21.Entity.DAO.IRetrofitDAO;

import android.telecom.Call;

import com.example.natour21.Entity.Compilation;
import com.example.natour21.Utils.Handler.Callback;

public interface ICompilationDAO {

    void listCompilation(Callback callback);

    void getCompilationById(long idCompilation, Callback callback);

    void getCompilationByUsername(String username, Callback callback);

    void getItinerariInCompilation(long idCompilation, Callback callback);

    void getCompilationValidSave(String username, long idItinerario, Callback callback);

    void postCompilation(Compilation compilation, Callback callback);

    void postItinerarioInCompilation(long idCompilation, long idItinerario, Callback callback);

    void putCompilation(Compilation compilation, Callback callback);

    void deleteCompilation(long idCompilation, Callback callback);

    void deleteItinerarioInCompilation(long idCompilation, long idItinerario, Callback callback);


}
