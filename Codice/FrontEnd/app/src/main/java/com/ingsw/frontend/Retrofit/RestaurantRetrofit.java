package com.ingsw.frontend.Retrofit;

import com.ingsw.frontend.Model.Restaurant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestaurantRetrofit {

    @GET("/restaurant/get/{name}")
    Call<Restaurant> getByName(@Path("name") String name);
}
