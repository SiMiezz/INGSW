package com.ingsw.frontend.Retrofit;

import com.ingsw.frontend.Model.TableRestaurant;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TableRestaurantApi {

    @GET("tablerestaurant/get/{name}")
    Single<List<TableRestaurant>> getByRestaurantName(@Path("name") String name);

    @GET("tablerestaurant/count/total/{name}")
    Single<Integer> countTotalTableByRestaurantName(@Path("name") String name);

    @GET("tablerestaurant/get/one/{id}")
    Single<TableRestaurant> getById(@Path("id") Integer id);
}
