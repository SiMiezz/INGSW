package com.example.natour21.Utils.Request.Instance;

import com.example.natour21.Entity.Itinerario;

import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface ItinerarioRetrofit {

    @GET("listaItinerari")
    Single<List<Itinerario>> listItinerari();

    @GET("listaItinerari/orderByRecent")
    Single<List<Itinerario>> getAllRecent();

    @GET("listaItinerari/ByName/{nomeItinerario}")
    Single<List<Itinerario>> getItinerariByName(@Path("nomeItinerario") String nomeItinerario);

    @GET("getItinerario/byID/{idItinerario}")
    Single<Itinerario> getItinerarioByID(@Path("idItinerario") Long idItinerario);

    @GET("getItinerario/byUsername/{username}")
    Single<List<Itinerario>> getItinerarioByUsername(@Path("username") String username);

    @GET("getItinerario/byFilter")
    Single<List<Itinerario>> getItinerarioByFilter(@QueryMap Map<String,String> filtro);

    @POST("createItinerario")
    Single<Response<Void>> createItinerario(@Body Itinerario itinerario);

    @PUT("modifyItinerario")
    Single<Response<Void>> modifyItinerario(@Body Itinerario itinerario);

    @DELETE("deleteItinerario/{id_itinerario}")
    Single<Response<Void>> removeItinerario(@Path("id_itinerario") long id_itinerario);

}
