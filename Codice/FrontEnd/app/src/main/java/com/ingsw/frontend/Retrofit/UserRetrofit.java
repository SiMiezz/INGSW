package com.ingsw.frontend.Retrofit;

import com.ingsw.frontend.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserRetrofit {

    @POST("/create")
    Call<User> create(@Body User user);

    @DELETE("/delete/{id}")
    Call<Void> deleteById(@Path("id") String id);

    @GET("/get/{name}")
    Call<List<User>> getByRestaurantName(@Path("name") String name);

    @GET("/get/{id}")
    Call<User> getById(@Path("id") String id);
}
