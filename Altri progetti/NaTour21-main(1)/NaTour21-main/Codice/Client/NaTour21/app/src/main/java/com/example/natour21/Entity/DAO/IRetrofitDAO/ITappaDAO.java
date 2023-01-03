package com.example.natour21.Entity.DAO.IRetrofitDAO;

import com.example.natour21.Entity.Tappa;
import com.example.natour21.Utils.Handler.Callback;

import java.util.List;

public interface ITappaDAO {

    void listTappe(Callback callback);

    void getTappaByID(Long idTappa, Callback callback);

    void getTappaByItinerario(Long idItinerario, Callback callback);

    void createTappa(Tappa tappa, Callback callback);

    void createTappe(List<Tappa> tappe, Callback callback);

}
