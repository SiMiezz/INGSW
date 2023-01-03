package com.example.natour21.Utils.Request.Instance;

import com.example.natour21.Entity.Utente;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserRetrofit {

    @GET("listaUtenti")
    Single<List<Utente>> listUser();

    @GET("getUtente/{username}")
    Single<Utente> getUser(@Path("username") String username);

    @POST("getUtente/social")
    Single<Response<Void>> getUserSocial(@Body Utente user);

    @POST("createUtente")
    Single<Response<Void>> postUser(@Body Utente user);

    @PUT("modificaUtente")
    Single<Response<Void>> putUser(@Body Utente user);

    @DELETE("deleteUtente/{username}")
    Single<Response<Void>> deleteUser(@Path("username") String username);

}
