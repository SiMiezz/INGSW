package com.example.natour21.Entity.DAO.IRetrofitDAO;

import com.example.natour21.Entity.FotoItinerario;
import com.example.natour21.Utils.Handler.Callback;

public interface IFotoItinerarioDAO {

    void listFotoItinerario(Callback callback);

    void getFotoItinerarioByID(Long idFoto, Callback callback);

    void getFotoItinerarioByItinerario(Long idItinerario, Callback callback);

    void getCountFotoItinerario(Long idItinerario, Callback callback);

    void publshFoto(FotoItinerario foto, Callback callback);

    void deleteFoto(Long idFoto, Callback callback);

}
