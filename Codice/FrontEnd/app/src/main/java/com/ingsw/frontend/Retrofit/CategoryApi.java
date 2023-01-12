package com.ingsw.frontend.Retrofit;

import com.ingsw.frontend.Model.Category;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CategoryApi {

    @POST("/category/create")
    Single<Void> create(@Body Category category);

    @DELETE("/category/delete/{id}")
    Single<Void> deleteById(@Path("id") Integer id);

    @GET("/category/get/{id}")
    Single<List<Category>> getByMenuId(@Path("id") Integer id);

}
