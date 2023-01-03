package com.example.natour21.Utils.Request.Instance;

import com.example.natour21.Entity.FotoItinerario;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FotoItinerarioRetrofit {

    @GET("listaFotoItinerario")
    Single<List<FotoItinerario>> listFotoItinerario();

    @GET("getFoto/{idFoto}")
    Single<FotoItinerario> getFotoItinerarioByID(@Path("idFoto") Long idFoto);

    @GET("listaFotoItinerario/{idItinerario}")
    Single<List<FotoItinerario>> getFotoItinerarioByItinerario(@Path("idItinerario") Long idItinerario);

    @GET("listaFotoItinerario/count/{idItinerario}")
    Single<Long> getCountFotoItinerario(@Path("idItinerario") Long idItinerario);

    @POST("pubblicaFoto")
    Single<Response<Void>> publshFoto(@Body FotoItinerario foto);

    @DELETE("eliminaFoto/{idFoto}")
    Single<Response<Void>> deleteFoto(@Path("idFoto") Long idFoto);

}
