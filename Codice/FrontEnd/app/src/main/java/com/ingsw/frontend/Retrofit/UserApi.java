package com.ingsw.frontend.Retrofit;

import com.ingsw.frontend.Model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {

    @POST("/create")
    Observable<User> create(@Body User user);

    @DELETE("/delete/{id}")
    Observable<Void> deleteById(@Path("id") String id);

    @GET("/get/{name}")
    Observable<List<User>> getByRestaurantName(@Path("name") String name);

    @GET("/get/{id}")
    Observable<User> getById(@Path("id") String id);
}
