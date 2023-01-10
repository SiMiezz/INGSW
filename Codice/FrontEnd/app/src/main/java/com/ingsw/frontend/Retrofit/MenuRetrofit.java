package com.ingsw.frontend.Retrofit;

import com.ingsw.frontend.Model.Menu;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MenuRetrofit {

    @GET("/menu/get/{name}")
    Call<Menu> getByRestaurantName(@Path("name") String name);
}
