package com.ingsw.frontend.Retrofit;

import com.ingsw.frontend.Model.Allergen;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AllergenRetrofit {

    @POST("/allergen/create")
    Call<Allergen> create(@Body Allergen allergen);

    @GET("/get")
    Call<List<Allergen>> getAll();
}
