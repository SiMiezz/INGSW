package com.ingsw.frontend.Retrofit;

import com.ingsw.frontend.Model.TableRestaurant;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TableRestaurantApi {

    @GET("tablerestaurant/get/{name}")
    Single<List<TableRestaurant>> getByRestaurantName(@Path("name") String name);
}
