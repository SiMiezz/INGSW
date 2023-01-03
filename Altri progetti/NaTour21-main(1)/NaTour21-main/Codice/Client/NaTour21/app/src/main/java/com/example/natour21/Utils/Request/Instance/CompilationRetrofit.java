package com.example.natour21.Utils.Request.Instance;

import com.example.natour21.Entity.Compilation;
import com.example.natour21.Entity.Itinerario;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CompilationRetrofit {

    @GET("listaCompilation")
    Single<List<Compilation>> listCompilation();

    @GET("getCompilation/byID/{idCollection}")
    Single<Compilation> getCompilationByID(@Path("idCollection") long username);

    @GET("getCompilation/byUsername/{username}")
    Single<List<Compilation>> getCompilationByUsername(@Path("username") String username);

    @GET("getCompilation/itinerari/{idCompilation}")
    Single<List<Itinerario>> getItinerariInCompilation(@Path("idCompilation") long idCompilation);

    @GET("getCompilation/validsave/{username}/{idItinerario}")
    Single<List<String>> getCompilationValidSave(@Path("username") String username,
                                                 @Path("idItinerario") long idItinerario);

    @POST("createCompilation")
    Single<Response<Void>> postCompilation(@Body Compilation compilation);

    @POST("addItinerario/{idCompilation}/{idItinerario}")
    Single<Response<Void>> postItinerarioInCompilation(@Path("idCompilation") long idCompilation,
                                                       @Path("idItinerario") long idItinerario);

    @PUT("modifyCompilation")
    Single<Response<Void>> putCompilation(@Body Compilation compilation);

    @DELETE("deleteCompilation/{idCompilation}")
    Single<Response<Void>> deleteCompilation(@Path("idCompilation") long idCompilation);

    @DELETE("removeItinerario/{idCompilation}/{idItinerario}")
    Single<Response<Void>> deleteItinerarioInCompilation(@Path("idCompilation") long idCompilation,
                                                         @Path("idItinerario") long idItinerario);

}
