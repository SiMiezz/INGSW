package com.ingsw.frontend.Retrofit;

import com.ingsw.frontend.Model.Client;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ClientApi {

    @POST("/client/create")
    Completable create(@Body Client client);

    @DELETE("/client/delete/{id}")
    Completable deleteById(@Path("id") Integer id);
}
