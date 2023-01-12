package com.ingsw.frontend.Retrofit;

import com.ingsw.frontend.Model.Client;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ClientApi {

    @POST("/client/create")
    Single<Void> create(@Body Client client);

    @DELETE("/client/delete/{id}")
    Single<Void> deleteById(@Path("id") Integer id);
}
