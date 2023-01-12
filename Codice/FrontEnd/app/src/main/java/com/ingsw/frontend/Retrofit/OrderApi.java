package com.ingsw.frontend.Retrofit;

import com.ingsw.frontend.Model.Order;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderApi {

    @POST("/order/create")
    Single<Void> create(@Body Order order);

    @DELETE("order/delete/{id}")
    Single<Void> deleteById(@Path("id") Integer id);

    @GET("/order/get/{id}")
    Single<List<Order>> getByTableRestaurantId(@Path("id") Integer id);

}
