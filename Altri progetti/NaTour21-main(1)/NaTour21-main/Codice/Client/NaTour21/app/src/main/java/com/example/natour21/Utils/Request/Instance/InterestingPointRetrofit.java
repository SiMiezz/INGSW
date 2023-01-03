package com.example.natour21.Utils.Request.Instance;

import com.example.natour21.Entity.InterestingPoint;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface InterestingPointRetrofit {

    @GET("listaInterestingPoint")
    Single<List<InterestingPoint>> listInterestingPoint();

    @GET("getInterestingPoint/byID/{idInterestingPoint}")
    Single<InterestingPoint> getCompilationByID(@Path("idInterestingPoint") long idInterestingPoint);

    @GET("getInterestingPoint/byItinerario/{idItinerario}")
    Single<List<InterestingPoint>> getInterestingPointByItinerario(@Path("idItinerario") long idItinerario);

    @GET("getInterestingPoint/getSingleFoto/{idInterestingPoint}")
    Single<String> getFotoItinerarioSingle(@Path("idInterestingPoint") long idInterestingPoint);

    @GET("getInterestingPoint/getMultipleFoto/{idInterestingPoint}")
    Single<List<String>> getFotoItinerarioMultiple(@Path("idItinerario") long idItinerario);

    @POST("createInterestingPoint")
    Single<Response<Void>> createInterestingPoint(@Body InterestingPoint interestingPoint);

    @POST("createInterestingPoints")
    Single<Response<Void>> createInterestingPoints(@Body List<InterestingPoint> interestingPoints);

    @PUT("modifyInterestingPoint")
    Single<Response<Void>> modifyInterestingPoint(@Body InterestingPoint interestingPoint);

    @DELETE("deleteInterestingPoint/{idInterestingPoint}")
    Single<Response<Void>> deleteInterestingPoint(@Path("idInterestingPoint") long idInterestingPoint);

    @DELETE("deleteInterestingPoint/foto/{idInterestingPoint}")
    Single<Response<Void>> deleteFotoInterestingPoint(@Path("idInterestingPoint") long idInterestingPoint);

}
