package com.ingsw.frontend.Retrofit;

import com.ingsw.frontend.Model.Client;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ClientRetrofit {

    @POST("/client/create")
    Call<Client> create(@Body Client client);

    @DELETE("/client/delete/{id}")
    Call<Void> deleteById(@Path("id") Integer id);
}
