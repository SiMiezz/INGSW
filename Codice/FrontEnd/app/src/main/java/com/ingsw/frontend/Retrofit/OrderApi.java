package com.ingsw.frontend.Retrofit;

import com.ingsw.frontend.Model.Order;

import java.sql.Date;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderApi {

    @POST("/order/create")
    Completable create(@Body Order order);

    @DELETE("order/delete/{id}")
    Completable deleteById(@Path("id") Integer id);

    @GET("/order/get/table/{id}")
    Single<List<Order>> getByTableRestaurantId(@Path("id") Integer id);

    @GET("/order/sum/{id}")
    Single<Double> sumPriceByTableId(@Path("id") Integer id);

    @HTTP(method = "DELETE", path = "/order/delete", hasBody = true)
    Completable delete(@Body Order order);

    @GET("/order/count/element/{id}/{from}/{to}")
    Single<Integer> getCountElementOrderedStats(@Path("id") Integer id, @Path("from") Date fromDate, @Path("to") Date toDate);
}
