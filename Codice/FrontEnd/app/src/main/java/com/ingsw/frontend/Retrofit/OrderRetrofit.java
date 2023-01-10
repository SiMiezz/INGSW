package com.ingsw.frontend.Retrofit;

import com.ingsw.frontend.Model.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderRetrofit {

    @POST("/order/create")
    Call<Order> create(@Body Order order);

    @DELETE("order/delete/{id}")
    Call<Void> deleteById(@Path("id") Integer id);

    @GET("/order/get/{id}")
    Call<List<Order>> getByTableRestaurantId(@Path("id") Integer id);

}
