package com.ingsw.frontend.Retrofit;

import com.ingsw.frontend.Model.Allergen;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AllergenApi {

    @POST("/allergen/create")
    Single<Void> create(@Body Allergen allergen);

    @GET("/get")
    Single<List<Allergen>> getAll();
}
