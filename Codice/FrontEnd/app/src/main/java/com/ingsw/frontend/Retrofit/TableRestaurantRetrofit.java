package com.ingsw.frontend.Retrofit;

import com.ingsw.frontend.Model.TableRestaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TableRestaurantRetrofit {

    @GET("tablerestaurant/get/{name}")
    Call<List<TableRestaurant>> getByRestaurantName(@Path("name") String name);
}
