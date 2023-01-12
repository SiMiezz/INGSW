package com.ingsw.frontend.Retrofit;

import com.ingsw.frontend.Model.Allergen;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AllergenApi {

    @POST("/allergen/create")
    Observable<Void> create(@Body Allergen allergen);

    @GET("/get")
    Observable<List<Allergen>> getAll();
}
