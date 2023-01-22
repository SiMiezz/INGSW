package com.ingsw.frontend.Retrofit;

import com.ingsw.frontend.Model.Enumerations.User_Type;
import com.ingsw.frontend.Model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserApi {

    @PUT("/user/update")
    Completable update(@Body User user);

    @POST("/user/create")
    Completable create(@Body User user);

    @DELETE("/user/delete/{email}")
    Completable deleteByEmail(@Path("email") String email);

    @GET("/user/get/restaurant/{name}")
    Single<List<User>> getByRestaurantName(@Path("name") String name);

    @GET("/user/get/{email}/{pwd}")
    Single<User> getByEmailAndPassword(@Path("email") String email, @Path("pwd") String pwd);

    @GET("/user/get/restaurant/{restaurant_name}/{job}")
    Single<List<User>> getByRestaurantNameAndJob(@Path("restaurant_name") String restaurant_name, @Path("job") User_Type job);
}
