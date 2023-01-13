package com.ingsw.frontend.Retrofit;

import com.ingsw.frontend.Model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {

    @POST("/user/create")
    Single<Void> create(@Body User user);

    @DELETE("/user/delete/{email}")
    Single<Void> deleteByEmail(@Path("email") String email);

    @GET("/user/get/restaurant/{name}")
    Single<List<User>> getByRestaurantName(@Path("name") String name);

    @GET("/user/get/{email}/{pwd}")
    Single<User> getByEmailAndPassword(@Path("email") String email, @Path("pwd") String pwd);
}
