package com.example.natour21.Entity.DAO.IRetrofitDAO;

import com.example.natour21.Entity.Itinerario;
import com.example.natour21.Utils.Handler.Callback;

import java.util.Map;

public interface IItinerarioDAO {

    void listItinerari(Callback callback);

    void getAllRecent(Callback callback);

    void getItinerariByName(String nomeItinerario, Callback callback);

    void getItinerarioByID(Long idItinerario, Callback callback);

    void getItinerarioByUsername(String username, Callback callback);

    void getItinerarioByFilter(Map<String,String> filtro, Callback callback);

    void createItinerario(Itinerario itinerario, Callback callback);

    void modifyItinerario(Itinerario itinerario, Callback callback);

    void removeItinerario(long id_itinerario, Callback callback);

}
