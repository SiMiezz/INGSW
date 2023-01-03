package com.example.natour21.Utils.Request.Instance;

import com.example.natour21.Entity.Tappa;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TappaRetrofit {

    @GET("listaTappe")
    Single<List<Tappa>> listTappe();

    @GET("getTappa/ByID/{idTappa}")
    Single<Tappa> getTappaByID(@Path("idTappa") Long idTappa);

    @GET("getComplation/byItinerario/{idItinerario}")
    Single<List<Tappa>> getTappaByItinerario(@Path("idItinerario") Long idItinerario);

    @POST("createTappa")
    Single<Response<Void>> createTappa(@Body Tappa tappa);

    @POST("createTappe")
    Single<Response<Void>> createTappe(@Body List<Tappa> tappe);

}
